package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Period;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.PeriodMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    private PeriodMapper PeriodMapper;

    @Transactional
    @Override
    public void add(Period Period) {
        BaseBeanHelper.insert(Period);
        PeriodMapper.insert(Period);
    }

    @Override
    public void delete(String id) {
    PeriodMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Period Period) {
        BaseBeanHelper.edit(Period);
        Example example = new Example(Period.class);
        example.createCriteria().andEqualTo("id", Period.getId());
        PeriodMapper.updateByExampleSelective(Period, example);
    }

    @Override
    public PageInfo<Period> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(PeriodMapper.selectAll());
    }

    @Override
    public Period getById(String id) {
        Example example = new Example(Period.class);
        example.createCriteria().andEqualTo("id", id);
        return PeriodMapper.selectOneByExample(example);
    }
}
