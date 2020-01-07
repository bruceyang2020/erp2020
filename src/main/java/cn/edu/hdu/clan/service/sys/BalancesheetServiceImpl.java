package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Balancesheet;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.BalancesheetMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.*;

import java.util.List;

@Service
public class BalancesheetServiceImpl implements BalancesheetService {

    @Autowired
    private BalancesheetMapper BalancesheetMapper;

    @Transactional
    @Override
    public void add(Balancesheet Balancesheet) {
        BaseBeanHelper.insert(Balancesheet);
        BalancesheetMapper.insert(Balancesheet);
    }

    @Override
    public void delete(String id) {
    BalancesheetMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Balancesheet Balancesheet) {
//        BaseBeanHelper.edit(Balancesheet);
        Example example = new Example(Balancesheet.class);
        example.createCriteria().andEqualTo("id", Balancesheet.getId());
        BalancesheetMapper.updateByExampleSelective(Balancesheet, example);
    }

    @Override
    public PageInfo<Balancesheet> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(BalancesheetMapper.selectAll());
    }

    @Override
    public List<Balancesheet> list() {
        return BalancesheetMapper.selectAll();
    }

    @Override
    public Balancesheet getById(String id) {
        Example example = new Example(Balancesheet.class);
        example.createCriteria().andEqualTo("id", id);
        return BalancesheetMapper.selectOneByExample(example);
    }


    @Override
    public List<Balancesheet> getByUserIdAndPeriod(String create_user,int period) {
        Example example = new Example(Balancesheet.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("createUser", create_user);
        criteria.andEqualTo("period", period);
        return BalancesheetMapper.selectByExample(example);
    }
}
