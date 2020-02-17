package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Inv;
import cn.edu.hdu.clan.entity.sys.MaterialOrder;
import cn.edu.hdu.clan.entity.sys.OrderManagement;
import cn.edu.hdu.clan.entity.sys.ResearchFee;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.MaterialOrderMapper;
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
public class MaterialOrderServiceImpl implements MaterialOrderService {

    @Autowired
    private MaterialOrderMapper MaterialOrderMapper;

    @Resource
    private AccountingVoucherService accountingVoucherService;


    @Transactional
    @Override
    public void add(MaterialOrder MaterialOrder) {

        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();
        //补充相关字段的取值
        MaterialOrder.setTeamCount(userTeam);
        MaterialOrder.setGroupId("1000");
        MaterialOrder.setState(0); //设置新增订单的状态为0, [0]未入库，[1]入库

        //删除当前原材料订单的记录
        Example example = new Example(MaterialOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", MaterialOrder.getTeamCount());
        criteria.andEqualTo("period", MaterialOrder.getPeriod());
        criteria.andEqualTo("materialId", MaterialOrder.getMaterialId());
        List<MaterialOrder> oldRow = MaterialOrderMapper.selectByExample(example);
        if(oldRow.size() > 0)
        {
            MaterialOrderMapper.deleteByExample(example);
        }

        //提交新增记录，自动生成GUID主键及新增的createuser ,createtime
        BaseBeanHelper.insert(MaterialOrder);
        MaterialOrderMapper.insert(MaterialOrder);

    }

    public void adds(List<MaterialOrder>  materialOrders) {
        if(materialOrders.size() > 0) {
            for (int i = 0; i < materialOrders.size(); i++) {
                String userTeam = Jurisdiction.getUserTeam();
                int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
                materialOrders.get(i).setPeriod(period);
                materialOrders.get(i).setTeamCount(userTeam);
                materialOrders.get(i).setGroupId("1000");
                BaseBeanHelper.insert(materialOrders.get(i));
                MaterialOrderMapper.insert(materialOrders.get(i));
            }
        }
    }


    @Override
    public void delete(String id) {
    MaterialOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByTeamCount(String userTeam) {
        Example example = new Example(MaterialOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        MaterialOrderMapper.deleteByExample(example);
    }


    @Override
    public void payment(String userTeam ,int period) {

        Example example = new Example(MaterialOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andNotEqualTo("state", 0);
        List<MaterialOrder> oldRow = MaterialOrderMapper.selectByExample(example);
        if(oldRow.size() > 0)
        {
            String materialId = "";
            int amount = 0;
            int purchasePeriod = 0;
            int leadTime = 0;

            for(int i=1;i<oldRow.size();i++)
            {
                materialId = oldRow.get(i).getMaterialId();
                amount =  oldRow.get(i).getAmount();
                purchasePeriod = oldRow.get(i).getPeriod();
                leadTime = period-purchasePeriod; //当前会计期间减去采购订单的会计期间，得到采购提前期
                //R1、R2
                if("R1".equals(materialId)  || "R2".equals(materialId))
                {
                    if(leadTime ==1)
                    {
                        oldRow.get(i).setState(1);
                        MaterialOrderMapper.updateByPrimaryKey(oldRow.get(i));

                        //自动生成原材料入库的会计凭证
                        accountingVoucherService.voucherMaker(userTeam,period,BigDecimal.valueOf(amount*1.0),"CLRK",materialId+"入库");


                    }
                }
                if("R3".equals(materialId)  || "R4".equals(materialId))
                {
                    if(leadTime ==2)
                    {
                        oldRow.get(i).setState(1);
                        MaterialOrderMapper.updateByPrimaryKey(oldRow.get(i));
                        //自动生成原材料入库的会计凭证
                        accountingVoucherService.voucherMaker(userTeam,period,BigDecimal.valueOf(amount*1.0),"CLRK",materialId+"入库");

                    }
                }

            }

        }


    }

    @Override
    public void update(MaterialOrder MaterialOrder) {
//        BaseBeanHelper.edit(MaterialOrder);
        Example example = new Example(MaterialOrder.class);
        example.createCriteria().andEqualTo("id", MaterialOrder.getId());
        MaterialOrderMapper.updateByExampleSelective(MaterialOrder, example);
    }

    @Override
    public PageInfo<MaterialOrder> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(MaterialOrderMapper.selectAll());
    }

    @Override
    public MaterialOrder getById(String id) {
        Example example = new Example(MaterialOrder.class);
        example.createCriteria().andEqualTo("id", id);
        return MaterialOrderMapper.selectOneByExample(example);
    }
    public List<MaterialOrder> list() {
        //根据当前的用户组（公司）、当前会计期间查询
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        Example example = new Example(MaterialOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        return MaterialOrderMapper.selectByExample(example);}
}
