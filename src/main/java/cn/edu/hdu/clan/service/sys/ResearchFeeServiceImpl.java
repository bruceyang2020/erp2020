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

    @Transactional
    @Override
    public void add(ResearchFee ResearchFee) {

        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();
        //补充相关字段的取值
        ResearchFee.setTeamCount(userTeam);
        ResearchFee.setGroupId("1000");
        ResearchFee.setPeriodLeft(5);

        //删除当前市场开发的记录
        Example example = new Example(ResearchFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", ResearchFee.getTeamCount());
        criteria.andEqualTo("period", ResearchFee.getPeriod());
        criteria.andEqualTo("productId", ResearchFee.getProductId());
        List<ResearchFee> oldRow = ResearchFeeMapper.selectByExample(example);
        if(oldRow.size() > 0)
        {
            ResearchFeeMapper.deleteByExample(example);
        }

        //提交新增记录，自动生成GUID主键及新增的createuser ,createtime
        BaseBeanHelper.insert(ResearchFee);
        ResearchFeeMapper.insert(ResearchFee);

        String productId = ResearchFee.getProductId();

        switch (productId)
        {
            case "P1":
                //自动生成市场开拓会计凭证
                accountingVoucherService.voucherMaker(userTeam,ResearchFee.getPeriod(),new BigDecimal("20"),"CPYF","P1");
                break;

            case "P2":
                //自动生成市场开拓会计凭证
                accountingVoucherService.voucherMaker(userTeam,ResearchFee.getPeriod(),new BigDecimal("20"),"CPYF","P2");
                break;
            case "P3":
                //自动生成市场开拓会计凭证
                accountingVoucherService.voucherMaker(userTeam,ResearchFee.getPeriod(),new BigDecimal("20"),"CPYF","P3");
                break;

            case "P4":
                //自动生成市场开拓会计凭证
                accountingVoucherService.voucherMaker(userTeam,ResearchFee.getPeriod(),new BigDecimal("20"),"CPYF","P4");
                break;


        }

    }

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
    public void delete(String id) {
    ResearchFeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByTeamCount(String userTeam) {
        Example example = new Example(ResearchFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        ResearchFeeMapper.deleteByExample(example);
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
}
