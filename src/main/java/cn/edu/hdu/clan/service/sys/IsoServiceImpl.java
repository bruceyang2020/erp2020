package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Iso;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.IsoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class IsoServiceImpl implements IsoService {

    @Autowired
    private IsoMapper IsoMapper;

    @Transactional
    @Override
    public void add(Iso Iso) {
        BaseBeanHelper.insert(Iso);
        IsoMapper.insert(Iso);
    }

    @Override
    public void delete(String id) {
    IsoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Iso Iso) {
        BaseBeanHelper.edit(Iso);
        Example example = new Example(Iso.class);
        example.createCriteria().andEqualTo("id", Iso.getId());
        IsoMapper.updateByExampleSelective(Iso, example);
    }

    @Override
    public PageInfo<Iso> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(IsoMapper.selectAll());
    }

    @Override
    public Iso getById(String id) {
        Example example = new Example(Iso.class);
        example.createCriteria().andEqualTo("id", id);
        return IsoMapper.selectOneByExample(example);
    }
}
