package cn.edu.hdu.clan.service.sys;
import cn.edu.hdu.clan.entity.BaseBean;
import cn.edu.hdu.clan.entity.sys.OrderGroup;
import cn.edu.hdu.clan.entity.sys.ProductLine;
import cn.edu.hdu.clan.entity.sys.SandtableOrder;
import cn.edu.hdu.clan.entity.sys.SysUser;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.helper.UUIDHelper;
import cn.edu.hdu.clan.mapper.sys.SandtableOrderMapper;
import cn.edu.hdu.clan.util.Jurisdiction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.jsqlparser.statement.delete.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.genid.GenId;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SandtableOderServiceImpl implements SandtableOrderService{
    @Resource
    private cn.edu.hdu.clan.mapper.sys.SandtableOrderMapper SandtableOrderMapper;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private OrderGroupService orderGroupService;
    @Transactional


    /**
     * H 用于生成订单
     **/
    public String adds(String groupId){
        List<OrderGroup> list = orderGroupService.listToSandtableOrder();

        JSONArray json1 = JSONArray.fromObject(list);
        JSONArray json2 = new JSONArray();
        for (int i = 0; i < json1.size(); i++) {
            JSONObject jsonObject = (JSONObject) json1.get(i);
            JSONObject jsonObject2 = jsonObject.discard("id");
            json2.add(jsonObject2);
        }

        List<SandtableOrder> mylist= (List<SandtableOrder>)JSONArray.toCollection(json2, SandtableOrder.class);


        if(mylist.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
               mylist.get(i).setState(0);
               mylist.get(i).setUserId(" ");
               mylist.get(i).setGroupId(groupId);

               BaseBeanHelper.insert(mylist.get(i));
               SandtableOrderMapper.insert(mylist.get(i));
            }
        }
        else {
            return "Null";
        }
        return "1";
    }


    /**
     * H 用于选取订单
     **/
    public String add(SandtableOrder sandtableOrder){

        String myMsg ="okadd";

        Example example = new Example(SandtableOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId",sandtableOrder.getOrderId());
        criteria.andEqualTo("groupId", sandtableOrder.getGroupId());
        criteria.andEqualTo("userId"," ");
        criteria.andEqualTo("period",sandtableOrder.getPeriod());

        List <SandtableOrder> mylist = SandtableOrderMapper.selectByExample(example);

        if (mylist.size()>0){
            mylist.get(0).setUserId(sandtableOrder.getUserId());
            mylist.get(0).setState(1);
            BaseBeanHelper.edit(sandtableOrder);
            SandtableOrderMapper.updateByExample(mylist.get(0),example);
        }
        else {
            myMsg = "Faile";
        }

        return myMsg;
    }


    /**
     * H 用于取消订单
     **/
    public String del(SandtableOrder sandtableOrder){

        String myMsg ="okdel";
        Example example = new Example(SandtableOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId",sandtableOrder.getOrderId());
        criteria.andEqualTo("groupId", sandtableOrder.getGroupId());
        criteria.andEqualTo("userId",sandtableOrder.getUserId());
        criteria.andEqualTo("period",sandtableOrder.getPeriod());
        List <SandtableOrder> mylist = SandtableOrderMapper.selectByExample(example);

        if (mylist.size()>0&&mylist.get(0).getUserId().compareTo(sandtableOrder.getUserId())==0){
            mylist.get(0).setUserId(" ");
            mylist.get(0).setState(0);
            SandtableOrderMapper.updateByExample(mylist.get(0),example);
        }
        else {
            myMsg = "fail";
        }

        return myMsg;
    }


    /**
     * H 用于显示订单
     **/
    public List<SandtableOrder> listByGroupId(String groupId, int period){
        Example example = new Example(SandtableOrder.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("'orderId' ASC");
        criteria.andEqualTo("groupId", groupId);
        criteria.andEqualTo("period",period);
        List<SandtableOrder> mylist = SandtableOrderMapper.selectByExample(example);
        return mylist;
    }

    /**
     * H 用于显示订单
     **/
    public List<SandtableOrder> listByGroupIdAndUserId(String groupId,String userId,int period){
        Example example = new Example(SandtableOrder.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("'userId' ASC, 'orderId' ASC" );
        criteria.andEqualTo("groupId", groupId);
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("period",period);
        List<SandtableOrder> mylist = SandtableOrderMapper.selectByExample(example);

        return mylist;
    }

}
