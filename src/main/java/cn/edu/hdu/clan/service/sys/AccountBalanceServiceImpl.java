package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.AccountBalance;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.AccountBalanceMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class AccountBalanceServiceImpl implements AccountBalanceService {

    @Autowired
    private AccountBalanceMapper AccountBalanceMapper;

    @Transactional
    @Override
    public void add(AccountBalance AccountBalance) {
        BaseBeanHelper.insert(AccountBalance);
        AccountBalanceMapper.insert(AccountBalance);
    }

    @Override
    public void delete(String id) {
    AccountBalanceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(AccountBalance AccountBalance) {
        BaseBeanHelper.edit(AccountBalance);
        Example example = new Example(AccountBalance.class);
        example.createCriteria().andEqualTo("id", AccountBalance.getId());
        AccountBalanceMapper.updateByExampleSelective(AccountBalance, example);
    }

    @Override
    public PageInfo<AccountBalance> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(AccountBalanceMapper.selectAll());
    }

    @Override
    public AccountBalance getById(String id) {
        Example example = new Example(AccountBalance.class);
        example.createCriteria().andEqualTo("id", id);
        return AccountBalanceMapper.selectOneByExample(example);
    }
}
