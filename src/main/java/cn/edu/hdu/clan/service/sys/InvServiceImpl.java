package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Inv;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.InvMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class InvServiceImpl implements InvService {

    @Autowired
    private InvMapper InvMapper;

    @Transactional
    @Override
    public void add(Inv Inv) {
        BaseBeanHelper.insert(Inv);
        InvMapper.insert(Inv);
    }

    @Override
    public void delete(String id) {
    InvMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Inv Inv) {
        BaseBeanHelper.edit(Inv);
        Example example = new Example(Inv.class);
        example.createCriteria().andEqualTo("id", Inv.getId());
        InvMapper.updateByExampleSelective(Inv, example);
    }

    @Override
    public PageInfo<Inv> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(InvMapper.selectAll());
    }

    @Override
    public Inv getById(String id) {
        Example example = new Example(Inv.class);
        example.createCriteria().andEqualTo("id", id);
        return InvMapper.selectOneByExample(example);
    }
}
