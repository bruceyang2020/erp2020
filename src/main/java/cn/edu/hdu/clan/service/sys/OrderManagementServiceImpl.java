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

    @Transactional
    @Override
    public void add(OrderGroup  orderGroup) {
        //根据当前的用户组（公司）、当前会计期间查询
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());

        OrderManagement orderManagement = new OrderManagement();
        orderManagement.setGroupId("1000");
        orderManagement.setTeamId(userTeam);
        orderManagement.setPeriod(period);
        orderManagement.setOrderId(orderGroup.getOrderId());
        orderManagement.setProductId(orderGroup.getProductId());
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


    @Override
    public void stockOut(String orderId) {

        Example example = new Example(OrderManagement.class);
        example.createCriteria().andEqualTo("orderId", orderId);

        OrderManagement myOrderManagement =  OrderManagementMapper.selectOneByExample(example);
        myOrderManagement.setState(1);//将订单状态设置为0,表示已交付
        OrderManagementMapper.updateByExampleSelective(myOrderManagement,example);

        salepaymentService.addByOrderManagement(myOrderManagement);

        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        BigDecimal myMoney = myOrderManagement.getMoney();


        //自动生成交货的会计凭证
        accountingVoucherService.voucherMaker(userTeam,period,myMoney,"JH",orderId);



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
        int period =  Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        Example example = new Example(OrderManagement.class);
        Example.Criteria criteria = example.createCriteria();
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
        String userTeam = Jurisdiction.getUserTeam();
        Example example = new Example(OrderManagement.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamId", userTeam);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("state", 0); //列出未交付的订单

        return OrderManagementMapper.selectByExample(example);
    }

    @Override
    public OrderManagement getById(String id) {
        Example example = new Example(OrderManagement.class);
        example.createCriteria().andEqualTo("id", id);
        return OrderManagementMapper.selectOneByExample(example);
    }
    public List<OrderManagement> list() {
        return OrderManagementMapper.selectAll();}
}
