package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Incomesheet;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.IncomesheetMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class IncomesheetServiceImpl implements IncomesheetService {

    @Autowired
    private IncomesheetMapper IncomesheetMapper;

    @Transactional
    @Override
    public void add(Incomesheet Incomesheet) {
        BaseBeanHelper.insert(Incomesheet);
        IncomesheetMapper.insert(Incomesheet);
    }

    @Override
    public void delete(String id) {
    IncomesheetMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Incomesheet Incomesheet) {
        BaseBeanHelper.edit(Incomesheet);
        Example example = new Example(Incomesheet.class);
        example.createCriteria().andEqualTo("id", Incomesheet.getId());
        IncomesheetMapper.updateByExampleSelective(Incomesheet, example);
    }

       @Override
    public PageInfo<Incomesheet> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(IncomesheetMapper.selectAll());
    }
    @Override
    public List<Incomesheet> list( ) {
        return IncomesheetMapper.selectAll();
    }

    @Override
    public Incomesheet getById(String id) {
        Example example = new Example(Incomesheet.class);
        example.createCriteria().andEqualTo("id", id);
        return IncomesheetMapper.selectOneByExample(example);
    }
    @Override
    public Incomesheet getincomesheet(Incomesheet incomesheet){
        return IncomesheetMapper.query();
    }
}
