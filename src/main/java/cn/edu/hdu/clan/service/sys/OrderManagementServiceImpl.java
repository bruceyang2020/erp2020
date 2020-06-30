package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.OrderGroup;
import cn.edu.hdu.clan.entity.sys.OrderManagement;
import cn.edu.hdu.clan.entity.sys.Usury;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.OrderManagementMapper;
import cn.edu.hdu.clan.util.Jurisdiction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderManagementServiceImpl implements OrderManagementService {

    @Autowired
    private OrderManagementMapper OrderManagementMapper;

    @Resource
    private SalepaymentService salepaymentService ;

    @Resource
    private AccountingVoucherService accountingVoucherService;

    @Resource
    private InvService invService;

    @Transactional
    @Override
    public void add(OrderGroup  orderGroup) {
        //根据当前的用户组（公司）、当前会计期间查询
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());

        //H 删除当前相同订单Id的记录
        Example example = new Example(OrderManagement.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamId",userTeam);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("orderId",orderGroup.getOrderId());
        List<OrderManagement> oldRow = OrderManagementMapper.selectByExample(example);
        if(oldRow.size() > 0)
        {
            OrderManagementMapper.deleteByExample(example);
        }


        OrderManagement orderManagement = new OrderManagement();
        orderManagement.setGroupId("1000");
        orderManagement.setTeamId(userTeam);
        orderManagement.setPeriod(period);
        orderManagement.setOrderId(orderGroup.getOrderId());
        orderManagement.setProductId(orderGroup.getProductId());
        orderManagement.setMarketId(orderGroup.getMarketId());
        orderManagement.setAmount(orderGroup.getAmount());
        orderManagement.setMoney(orderGroup.getPriceTotal());
        orderManagement.setPeriodPay(orderGroup.getPeriodPay());
        orderManagement.setState(0);  //将订单状态设置为0,表示未交付
        BaseBeanHelper.insert(orderManagement);
        OrderManagementMapper.insert(orderManagement);
    }

    @Override
    public void delete(String id) {
    OrderManagementMapper.deleteByPrimaryKey(id);
    }

    //H 出库
    @Override
    public String stockOut(String orderId) {

        String myMsg = "OK";

        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();

        Example example = new Example(OrderManagement.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", orderId);
        criteria.andEqualTo("teamId", userTeam);  //

        OrderManagement myOrderManagement =  OrderManagementMapper.selectOneByExample(example);


        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        BigDecimal myMoney = myOrderManagement.getMoney();

         BigDecimal myAmount = invService.amountByProductId(userTeam,period,myOrderManagement.getProductId());
         //H 判断数量
        if(myAmount.compareTo(myOrderManagement.getAmount())!=-1) {
            myOrderManagement.setState(1);//将订单状态设置为0,表示已交付
            OrderManagementMapper.updateByExampleSelective(myOrderManagement, example);

            //应收账款
            salepaymentService.addByOrderManagement(myOrderManagement);

            //产品出库
            invService.stockOutToSale(userTeam, period, myOrderManagement.getProductId(), myOrderManagement.getAmount().intValue(), orderId + "销售");

            //自动生成交货的会计凭证
            accountingVoucherService.voucherMaker(userTeam, period, myMoney, "JH", orderId);

            myMsg = orderId+"交货成功";

        }else
        {
            myMsg = "库存不足";
        }
        return  myMsg;

    }

    @Override
    public void update(OrderManagement OrderManagement) {
//        BaseBeanHelper.edit(OrderManagement);
        Example example = new Example(OrderManagement.class);
        example.createCriteria().andEqualTo("id", OrderManagement.getId());
        OrderManagementMapper.updateByExampleSelective(OrderManagement, example);
    }

    @Override
    public List<OrderManagement> list(String productId) {


        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();
        int period =  Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        Example example = new Example(OrderManagement.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamId", userTeam);  //
        criteria.andEqualTo("productId", productId);
        criteria.andEqualTo("state", 0);  //列出未交付的订单

        return OrderManagementMapper.selectByExample(example);
    }

    /**
     * Y  列表当前会计期间的已选择的订单。
     * @returnｒ
     */
    @Override
    public List<OrderManagement> listCurrentPeriodOrder() {
        //全局变量 写入当前公司或小组ID
        int period =  Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        int periodB = period%4==0?(period/4-1)*4+1:(period)/4*4+1;
        String userTeam = Jurisdiction.getUserTeam();
        Example example = new Example(OrderManagement.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamId", userTeam);
        criteria.andEqualTo("period", periodB);
        criteria.andEqualTo("state", 0); //列出未交付的订单

        return OrderManagementMapper.selectByExample(example);
    }

    @Override
    public OrderManagement getById(String id) {
        Example example = new Example(OrderManagement.class);
        example.createCriteria().andEqualTo("id", id);
        return OrderManagementMapper.selectOneByExample(example);
    }

    @Override
    public OrderManagement getByTeamPeriodOrderId(String OrderId) {
        //全局变量 写入当前公司或小组ID
        int period =  Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        String userTeam = Jurisdiction.getUserTeam();
        Example example = new Example(OrderManagement.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamId", userTeam);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("orderId", OrderId);
        return OrderManagementMapper.selectOneByExample(example);
    }




    public List<OrderManagement> list() {
        return OrderManagementMapper.selectAll();}

    @Override
    public void deleteByTeamCount(String userTeam) {
        Example example = new Example(OrderManagement.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamId", userTeam);
        OrderManagementMapper.deleteByExample(example);
    }

}
