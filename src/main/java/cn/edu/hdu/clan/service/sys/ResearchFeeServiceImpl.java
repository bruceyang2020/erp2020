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
    public void add(ResearchFee ResearchFee) {

        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();
        //每一期都有复制，取出原始的数据
        Example example = new Example(ResearchFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", ResearchFee.getPeriod());
        criteria.andEqualTo("productId", ResearchFee.getProductId());
        List<ResearchFee> updateRow = ResearchFeeMapper.selectByExample(example);
        if(updateRow.get(0).getPeriodLeft()>0) {


            updateRow.get(0).setPeriodLeft(updateRow.get(0).getPeriodLeft() - 1);
            updateRow.get(0).setState(updateRow.get(0).getPeriodLeft() == 0 ? 1 : 0);//剩余期为0，则开发完成

            //提交新增记录，自动生成GUID主键及新增的createuser ,createtime
            BaseBeanHelper.edit(updateRow.get(0));
            ResearchFeeMapper.updateByPrimaryKey(updateRow.get(0));


            String productId = ResearchFee.getProductId();

            switch (productId) {
                case "P1":
                    //自动生成市场开拓会计凭证
                    accountingVoucherService.voucherMaker(userTeam, ResearchFee.getPeriod(), new BigDecimal("20"), "CPYF", "P1");
                    break;

                case "P2":
                    //自动生成市场开拓会计凭证
                    accountingVoucherService.voucherMaker(userTeam, ResearchFee.getPeriod(), new BigDecimal("20"), "CPYF", "P2");
                    break;
                case "P3":
                    //自动生成市场开拓会计凭证
                    accountingVoucherService.voucherMaker(userTeam, ResearchFee.getPeriod(), new BigDecimal("20"), "CPYF", "P3");
                    break;

                case "P4":
                    //自动生成市场开拓会计凭证
                    accountingVoucherService.voucherMaker(userTeam, ResearchFee.getPeriod(), new BigDecimal("20"), "CPYF", "P4");
                    break;


            }
        }
    }

    @Override
    /**更新原始数据完成删除
     * @param
     * @author Junhao Huang
     */
    public void deleteByPeriod(String userTeam,Integer period,String productId) {
        //删除产品研发的记录
        //H 消除已经研发完成还能删退的bug
        List<ResearchFee> oldRow= researchFeeService.listByperiod(userTeam,period-1,productId);

        if(oldRow.get(0).getState()==0) {
            List<ResearchFee> updateRow = researchFeeService.listByperiod(userTeam,period,productId);
            //补充相关字段的取值
            updateRow.get(0).setPeriodLeft(updateRow.get(0).getPeriodLeft() + 1);//剩余时间回撤
            updateRow.get(0).setState(updateRow.get(0).getPeriodLeft() == 0 ? 1 : 0);//这期开发过了

            //删除新增记录，自动生成GUID主键及新增的createuser ,createtime
            BaseBeanHelper.edit(updateRow.get(0));
            ResearchFeeMapper.updateByPrimaryKey(updateRow.get(0));
            //删除会计凭证
            accountingVoucherService.deleteByPeriodAndContent(userTeam, period, productId);
        }
    }




    @Override
    public void delete(String id) {
    ResearchFeeMapper.deleteByPrimaryKey(id);
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
