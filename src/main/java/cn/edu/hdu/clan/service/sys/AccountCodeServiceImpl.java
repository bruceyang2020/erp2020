package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.AccountCode;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.AccountCodeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class AccountCodeServiceImpl implements AccountCodeService {

    @Autowired
    private AccountCodeMapper AccountCodeMapper;

    @Transactional
    @Override
    public void add(AccountCode AccountCode) {
        BaseBeanHelper.insert(AccountCode);
        AccountCodeMapper.insert(AccountCode);
    }

    @Override
    public void delete(String id) {
    AccountCodeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(AccountCode AccountCode) {
        BaseBeanHelper.edit(AccountCode);
        Example example = new Example(AccountCode.class);
        example.createCriteria().andEqualTo("id", AccountCode.getId());
        AccountCodeMapper.updateByExampleSelective(AccountCode, example);
    }

    @Override
    public PageInfo<AccountCode> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(AccountCodeMapper.selectAll());
    }

    @Override
    public AccountCode getById(String id) {
        Example example = new Example(AccountCode.class);
        example.createCriteria().andEqualTo("id", id);
        return AccountCodeMapper.selectOneByExample(example);
    }
}
