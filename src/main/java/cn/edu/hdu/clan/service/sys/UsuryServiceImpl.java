package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.LongTermLoans;
import cn.edu.hdu.clan.entity.sys.Usury;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.UsuryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UsuryServiceImpl implements UsuryService {

    @Autowired
    private UsuryMapper UsuryMapper;

    @Transactional
    @Override
    public void add(Usury Usury) {
        BaseBeanHelper.insert(Usury);
        UsuryMapper.insert(Usury);
    }

    @Override
    public void delete(String id) {
    UsuryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Usury Usury) {
        BaseBeanHelper.edit(Usury);
        Example example = new Example(Usury.class);
        example.createCriteria().andEqualTo("id", Usury.getId());
        UsuryMapper.updateByExampleSelective(Usury, example);
    }

    @Override
    public PageInfo<Usury> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(UsuryMapper.selectAll());
    }

    @Override
    public Usury getById(String id) {
        Example example = new Example(Usury.class);
        example.createCriteria().andEqualTo("id", id);
        return UsuryMapper.selectOneByExample(example);
    }
    @Override
    public List<Usury> list() {
        return UsuryMapper.selectAll();
    }
}
