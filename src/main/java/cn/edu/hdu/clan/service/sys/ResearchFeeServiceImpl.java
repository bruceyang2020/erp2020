package cn.edu.hdu.clan.service.sys;


import cn.edu.hdu.clan.entity.sys.ResearchFee;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.ResearchFeeMapper;
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
public class ResearchFeeServiceImpl implements ResearchFeeService {

    @Autowired
    private ResearchFeeMapper ResearchFeeMapper;

    @Resource
    private AccountingVoucherService accountingVoucherService;

    @Resource
    private ResearchFeeService researchFeeService;

    @Transactional
    @Override
    // 初始化
    public void adds(List<ResearchFee>  researchFees) {
        if(researchFees.size() > 0) {
            for (int i = 0; i < researchFees.size(); i++) {
                String userTeam = Jurisdiction.getUserTeam();
                int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
                researchFees.get(i).setPeriod(period);
                researchFees.get(i).setTeamCount(userTeam);
                researchFees.get(i).setGroupId("1000");
                BaseBeanHelper.insert(researchFees.get(i));
                ResearchFeeMapper.insert(researchFees.get(i));
            }
        }
    }

    @Override
    /**更新原始数据完成添加
     * @param
     * @author Junhao Huang
     */
    public String add(ResearchFee ResearchFee) {
        String myMsg= "OK";

        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();
        //每一期都有复制，取出原始的数据
        Example example = new Example(ResearchFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", ResearchFee.getPeriod());
        criteria.andEqualTo("productId", ResearchFee.getProductId());
        List<ResearchFee> updateRow = ResearchFeeMapper.selectByExample(example);
        if(updateRow.get(0).getPeriodLeft()>0&&updateRow.get(0).getTakeRight()==0) {


            updateRow.get(0).setPeriodLeft(updateRow.get(0).getPeriodLeft() - 1);
            updateRow.get(0).setTakeRight(1);
            updateRow.get(0).setState(updateRow.get(0).getPeriodLeft() == 0 ? 1 : 0);//剩余期为0，则开发完成

            //提交新增记录，自动生成GUID主键及新增的createuser ,createtime
            BaseBeanHelper.edit(updateRow.get(0));
            ResearchFeeMapper.updateByPrimaryKey(updateRow.get(0));


            String productId = ResearchFee.getProductId();

            switch (productId) {
                case "P1":
                    //自动生成市场开拓会计凭证
                    accountingVoucherService.voucherMaker(userTeam, ResearchFee.getPeriod(), new BigDecimal("1"), "CPYF", "P1研发");
                    break;

                case "P2":
                    //自动生成市场开拓会计凭证
                    accountingVoucherService.voucherMaker(userTeam, ResearchFee.getPeriod(), new BigDecimal("1"), "CPYF", "P2研发");
                    break;
                case "P3":
                    //自动生成市场开拓会计凭证
                    accountingVoucherService.voucherMaker(userTeam, ResearchFee.getPeriod(), new BigDecimal("2"), "CPYF", "P3研发");
                    break;

                case "P4":
                    //自动生成市场开拓会计凭证
                    accountingVoucherService.voucherMaker(userTeam, ResearchFee.getPeriod(), new BigDecimal("3"), "CPYF", "P4研发");
                    break;

            }


        }
        else{
            myMsg="False";
        }
    return myMsg;
    }

    @Override
    /**更新原始数据完成删除
     * @param
     * @author Junhao Huang
     */
    public String deleteByPeriod(String userTeam,Integer period,String productId) {
        //删除产品研发的记录
        //H 消除已经研发完成还能删退的bug
        String myMsg="OK";
        if(period >1) {
            List<ResearchFee> oldRow = researchFeeService.listByperiod(userTeam, period - 1, productId);
            List<ResearchFee> updateRow = researchFeeService.listByperiod(userTeam, period, productId);
            if (oldRow.get(0).getState() == 0&&updateRow.get(0).getTakeRight()==1) {

                //补充相关字段的取值
                updateRow.get(0).setPeriodLeft(updateRow.get(0).getPeriodLeft() + 1);//剩余时间回撤
                updateRow.get(0).setTakeRight(0);
                updateRow.get(0).setState(updateRow.get(0).getPeriodLeft() == 0 ? 1 : 0);//这期开发过了

                //删除新增记录，自动生成GUID主键及新增的createuser ,createtime
                BaseBeanHelper.edit(updateRow.get(0));
                ResearchFeeMapper.updateByPrimaryKey(updateRow.get(0));
                //删除会计凭证
                accountingVoucherService.deleteByPeriodAndContent(userTeam, period, productId+"研发");
            }
            else{
                myMsg="False";
            }
        }

        else if (period==1){                 //H period==1 的时候，不需要追溯上一周期是否已完成某项的开发
                List<ResearchFee> updateRow = researchFeeService.listByperiod(userTeam, period, productId);
                //补充相关字段的取值
                updateRow.get(0).setPeriodLeft(updateRow.get(0).getPeriodLeft() + 1);//剩余时间回撤
                updateRow.get(0).setState(updateRow.get(0).getPeriodLeft() == 0 ? 1 : 0);//这期开发过了
                updateRow.get(0).setTakeRight(0);

                //删除新增记录，自动生成GUID主键及新增的createuser ,createtime
                BaseBeanHelper.edit(updateRow.get(0));
                ResearchFeeMapper.updateByPrimaryKey(updateRow.get(0));
                //删除会计凭证
                accountingVoucherService.deleteByPeriodAndContent(userTeam, period, productId+"研发");
        }
 return myMsg;
    }




    @Override
    public void delete(String id) {
    ResearchFeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByTeamCountAndPeriod(String userTeam ,int period) {
        Example example = new Example(ResearchFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        ResearchFeeMapper.deleteByExample(example);

    }

//H
    @Override
    public List<ResearchFee> listByperiod(String userTeam ,int period,String productId) {
        Example example = new Example(ResearchFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("productId", productId);
        return ResearchFeeMapper.selectByExample(example);
    }




    @Override
    public void update(ResearchFee ResearchFee) {
        BaseBeanHelper.edit(ResearchFee);
        Example example = new Example(ResearchFee.class);
        example.createCriteria().andEqualTo("id", ResearchFee.getId());
        ResearchFeeMapper.updateByExampleSelective(ResearchFee, example);
    }

    @Override
    public PageInfo<ResearchFee> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(ResearchFeeMapper.selectAll());
    }

    @Override
    public ResearchFee getById(String id) {
        Example example = new Example(ResearchFee.class);
        example.createCriteria().andEqualTo("id", id);
        return ResearchFeeMapper.selectOneByExample(example);
    }

    @Override
    public List<ResearchFee> list(String userTeam ,int period) {
        Example example = new Example(ResearchFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        return ResearchFeeMapper.selectByExample(example);
    }

    /**
     * Y 列表已完成研发的产品.判断条件是state = 1
     * @param userTeam
     * @param period
     * @return
     */
    @Override
    public List<ResearchFee> listFinish(String userTeam ,int period) {
        Example example = new Example(ResearchFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("state", 1);
        return ResearchFeeMapper.selectByExample(example);
    }

    @Override
    public void copyDataToNextPeriod(String userTeam, int period, int nextPeriod) {
        Example example = new Example(ResearchFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        List<ResearchFee> factorys = ResearchFeeMapper.selectByExample(example);

        if (factorys.size() > 0) {
            for (int i = 0; i < factorys.size(); i++) {
                ResearchFee myRow = factorys.get(i);
                myRow.setPeriod(nextPeriod);
                myRow.setTakeRight(0);
                BaseBeanHelper.insert(myRow);
                ResearchFeeMapper.insert(myRow);

            }
        }

    }

    public void deleteByTeamCount(String userTeam){
        Example example = new Example(ResearchFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        ResearchFeeMapper.deleteByExample(example);

    }

}
