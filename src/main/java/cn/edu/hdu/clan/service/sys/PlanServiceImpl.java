package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Plan;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.PlanMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanMapper PlanMapper;

    @Transactional
    @Override
    public void add(Plan Plan) {
        BaseBeanHelper.insert(Plan);
        PlanMapper.insert(Plan);
    }

    @Override
    public void delete(String id) {
    PlanMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Plan Plan) {
        BaseBeanHelper.edit(Plan);
        Example example = new Example(Plan.class);
        example.createCriteria().andEqualTo("id", Plan.getId());
        PlanMapper.updateByExampleSelective(Plan, example);
    }

    @Override
    public PageInfo<Plan> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(PlanMapper.selectAll());
    }

    @Override
    public Plan getById(String id) {
        Example example = new Example(Plan.class);
        example.createCriteria().andEqualTo("id", id);
        return PlanMapper.selectOneByExample(example);
    }
}
