package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.*;
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
import java.util.*;
import java.util.ArrayList;

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

    @Resource
    private  MarketFeeService marketFeeService;

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
        //Y 按照广告费所在的市场，输出备选的订单列表

        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        List<Advertise> advertises = advertiseService.getByUserTeamAndPeriod(userTeam,period);

        //Y 定义一个字符串数组，把已投放广告的市场，逐一写入。
        List<String> values=new ArrayList<String>();
        for(int i =1 ;i< advertises.size(); i++)
        {
           values.add(advertises.get(i).getMarketId());
        }
        //Y 本地市场已开拓完成。这样的硬编码实在不可以取。要考虑在数据库中初始化设计来解决。
        values.add("本地");


        //全局变量 写入当前公司或小组ID

        Example example = new Example(OrderGroup.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", productId);
        criteria.andEqualTo("period", period);  //当前会计期间
        criteria.andIn("marketId",values); //使用in方法，当前已完成开拓的市场

        return OrderGroupMapper.selectByExample(example);
    }


    @Override
    public List<OrderGroup> listByUserTeamAndPeriod() {
        //Y 按照广告费所在的市场，输出备选的订单列表

        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        List<Advertise> advertises = advertiseService.getByUserTeamAndPeriod(userTeam,period);


        //全局变量 写入当前公司或小组ID

        List<OrderGroup> myOrderList = new ArrayList<OrderGroup>();

        for(int i =0 ;i< advertises.size(); i++)
        {
            Example example = new Example(OrderGroup.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("period", period);  //当前会计期间
            criteria.andEqualTo("productId", advertises.get(i).getProductId());
            criteria.andEqualTo("marketId",advertises.get(i).getMarketId());
            example.orderBy("orderId");
            myOrderList.addAll(OrderGroupMapper.selectByExample(example));
        }

        for(int i =0 ;i< myOrderList.size(); i++)
        {

           OrderManagement myRow =   orderManagementService.getByTeamPeriodOrderId(myOrderList.get(i).getOrderId());

           if(myRow != null )
           {
               myOrderList.get(i).setIsSelected(1);
           }else
           {   myOrderList.get(i).setIsSelected(0);}

        }




        return myOrderList;
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
        myIso = iso9k+iso14k;  //获取这个订单ISO要求。0无要求  1 iso9k  2 iso14k  3两者都要

        if(iso != myIso)
        {
            infoString = "公司达不到ISO标准";
        }






        //判断当前这个订单，是否有iso的要求。

        return infoString;
    }


    /**
     * p判断当前订单是否可以选择。
     * @param orderId
     * @return
     */
    @Override
    public String checkIsoRight(String orderId) {
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
        myIso = iso9k+iso14k;  //获取这个订单ISO要求。0无要求  1 iso9k  2 iso14k  3两者都要

        if(iso != myIso)
        {
            infoString = "公司达不到ISO标准";
        }






        //判断当前这个订单，是否有iso的要求。

        return infoString;
    }
}
