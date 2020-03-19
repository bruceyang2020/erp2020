package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Advertise;
import cn.edu.hdu.clan.entity.sys.IsoFee;
import cn.edu.hdu.clan.entity.sys.OrderGroup;
import cn.edu.hdu.clan.entity.sys.OrderManagement;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.OrderGroupMapper;
import cn.edu.hdu.clan.util.Jurisdiction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderGroupServiceImpl implements OrderGroupService {

    @Autowired
    private OrderGroupMapper OrderGroupMapper;

    @Resource
    private  AdvertiseService advertiseService;

    @Resource
    private  OrderManagementService orderManagementService;

    @Resource
    private  IsoFeeService isoFeeService;

    @Transactional
    @Override
    public void add(OrderGroup OrderGroup) {
        BaseBeanHelper.insert(OrderGroup);
        OrderGroupMapper.insert(OrderGroup);
    }

    @Override
    public void delete(String id) {
    OrderGroupMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(OrderGroup OrderGroup) {
        BaseBeanHelper.edit(OrderGroup);
        Example example = new Example(OrderGroup.class);
        example.createCriteria().andEqualTo("id", OrderGroup.getId());
        OrderGroupMapper.updateByExampleSelective(OrderGroup, example);
    }

    @Override
    public List<OrderGroup> list(String productId) {

        //全局变量 写入当前公司或小组ID
        int period =  Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        Example example = new Example(OrderGroup.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", productId);
        criteria.andEqualTo("period", period);

        return OrderGroupMapper.selectByExample(example);
    }

    @Override
    public List<OrderGroup> listAll() {

        //全局变量 写入当前公司或小组ID
        int period =  Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        Example example = new Example(OrderGroup.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("groupId", "1000");
        return OrderGroupMapper.selectByExample(example);
    }

    @Override
    public OrderGroup getById(String id) {
        Example example = new Example(OrderGroup.class);
        example.createCriteria().andEqualTo("id", id);
        return OrderGroupMapper.selectOneByExample(example);
    }


    @Override
    public OrderGroup getByOrderId(String orderId) {
        Example example = new Example(OrderGroup.class);
        example.createCriteria().andEqualTo("orderId", orderId);
        return OrderGroupMapper.selectOneByExample(example);
    }


    /**
     * p判断当前订单是否可以选择。
     * @param orderId
     * @return
     */
    @Override
    public String checkOrderRight(String orderId) {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        String marketId = "";
        String productId = "";
        int iso = 0;


        String infoString = "ok";

        Example example = new Example(OrderGroup.class);
        example.createCriteria().andEqualTo("orderId", orderId);
        OrderGroup  orderGroup = OrderGroupMapper.selectOneByExample(example);
        marketId = orderGroup.getMarketId();
        productId = orderGroup.getProductId();
        iso = orderGroup.getIso();  //获取这个订单ISO要求。0无要求  1 iso9k  2 iso14k  3两者都要


        int myOrderRightNumber = 0;
        // Y 判断当前产品+市场=的广告费能选几个单。
        List<Advertise>  myAdvertises = advertiseService.getByUserTeamAndPeriod(userTeam,period);
        for(int i=0;i<myAdvertises.size();i++)
        {
             if(productId.equals(myAdvertises.get(i).getProductId()) && marketId.equals(myAdvertises.get(i).getMarketId()))
             {
                 myOrderRightNumber =myAdvertises.get(i).getMoneyI().intValue()/2+1;   //1  、3、5、7、9、11、13  分别转化成是1、2、3、4、5、6、

              }
        }

        int myOrderManagementNumber = 0;
        // Y 判断当前产品+市场的已选订单有几个。
        List<OrderManagement> myorderManagements = orderManagementService.listCurrentPeriodOrder();
        for(int j=0;j<myorderManagements.size();j++) {
            if(productId.equals(myorderManagements.get(j).getProductId()) && marketId.equals(myorderManagements.get(j).getMarketId()))
            {
                myOrderRightNumber =myOrderRightNumber+1;   //1  、3、5、7、9、11、13  分别转化成是1、2、3、4、5、6、

            }

        }

        if(myOrderRightNumber <= myOrderManagementNumber)
        {
            infoString = "订单已选满";
        }


        // Y 检查企业的iso认证是否满足订单的要求。
        int myIso = 0;
        int iso9k = 0;
        int iso14k = 0;
        List<IsoFee> myIsoFees = isoFeeService.list(userTeam,period);
        for(int t=0;t<myIsoFees.size();t++)
        {
            if("ISO9K".equals(myIsoFees.get(t).getNumber()) && myIsoFees.get(t).getState() == 1)
            {
                iso9k =1;
            }
            if("ISO14K".equals(myIsoFees.get(t).getNumber()) && myIsoFees.get(t).getState() == 1)
            {
                iso14k =2;
            }
        }
        myIso = iso9k+iso14k;

        if(iso != myIso)
        {
            infoString = "公司达不到ISO标准";
        }






        //判断当前这个订单，是否有iso的要求。

        return infoString;
    }
}
