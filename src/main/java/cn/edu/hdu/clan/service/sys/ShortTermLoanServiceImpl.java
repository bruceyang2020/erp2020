package cn.edu.hdu.clan.service.sys;


import cn.edu.hdu.clan.entity.sys.ShortTermLoan;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.ShortTermLoanMapper;
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
public class ShortTermLoanServiceImpl implements ShortTermLoanService {

    @Autowired
    private ShortTermLoanMapper ShortTermLoanMapper;

    @Resource
    private AccountingVoucherService accountingVoucherService;

    @Transactional
    @Override
    public void add(ShortTermLoan ShortTermLoan) {
        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();
        //补充相关字段的取值
        ShortTermLoan.setTeamCount(userTeam);
        ShortTermLoan.setGroupId("1000");
        ShortTermLoan.setSurplusPeriod(ShortTermLoan.getPeriod()+4);

        //删除当前长贷记录
        Example example = new Example(ShortTermLoan.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", ShortTermLoan.getTeamCount());
        criteria.andEqualTo("period", ShortTermLoan.getPeriod());
        List<ShortTermLoan> oldRow = ShortTermLoanMapper.selectByExample(example);
        if(oldRow.size() > 0)
        {
            ShortTermLoanMapper.deleteByExample(example);
        }

        //提交新增记录，自动生成GUID主键及新增的createuser ,createtime
        BaseBeanHelper.insert(ShortTermLoan);
        ShortTermLoanMapper.insert(ShortTermLoan);

        //自动生成长贷会计凭证
        accountingVoucherService.voucherMaker(userTeam,ShortTermLoan.getPeriod(),ShortTermLoan.getMoneyTotal(),"DUANDAI","新增短贷");

    }

    @Override
    public void delete(String id) {
    ShortTermLoanMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ShortTermLoan ShortTermLoan) {
//        BaseBeanHelper.edit(ShortTermLoan);
        Example example = new Example(ShortTermLoan.class);
        example.createCriteria().andEqualTo("id", ShortTermLoan.getId());
        ShortTermLoanMapper.updateByExampleSelective(ShortTermLoan, example);
    }

    @Override
    public PageInfo<ShortTermLoan> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(ShortTermLoanMapper.selectAll());
    }

    @Override
    public ShortTermLoan getById(String id) {
        Example example = new Example(ShortTermLoan.class);
        example.createCriteria().andEqualTo("id", id);
        return ShortTermLoanMapper.selectOneByExample(example);
    }

    @Override
    public List<ShortTermLoan> getByUserIdAndPeriod(String userTeam) {
        Example example = new Example(ShortTermLoan.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        return ShortTermLoanMapper.selectByExample(example);
    }
}
