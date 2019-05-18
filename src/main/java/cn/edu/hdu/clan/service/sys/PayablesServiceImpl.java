package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Payables;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.PayablesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class PayablesServiceImpl implements PayablesService {

    @Autowired
    private PayablesMapper PayablesMapper;

    @Transactional
    @Override
    public void add(Payables Payables) {
        BaseBeanHelper.insert(Payables);
        PayablesMapper.insert(Payables);
    }

    @Override
    public void delete(String id) {
    PayablesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Payables Payables) {
        BaseBeanHelper.edit(Payables);
        Example example = new Example(Payables.class);
        example.createCriteria().andEqualTo("id", Payables.getId());
        PayablesMapper.updateByExampleSelective(Payables, example);
    }

    @Override
    public PageInfo<Payables> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(PayablesMapper.selectAll());
    }

    @Override
    public Payables getById(String id) {
        Example example = new Example(Payables.class);
        example.createCriteria().andEqualTo("id", id);
        return PayablesMapper.selectOneByExample(example);
    }
}
