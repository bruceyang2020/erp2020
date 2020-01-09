package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Advertise;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.AdvertiseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class AdvertiseServiceImpl implements AdvertiseService {

    @Autowired
    private AdvertiseMapper AdvertiseMapper;

    @Transactional
    @Override
    public void add(Advertise Advertise) {
        BaseBeanHelper.insert(Advertise);
        AdvertiseMapper.insert(Advertise);
    }

    @Override
    public void delete(String id) {
    AdvertiseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Advertise Advertise) {
        BaseBeanHelper.edit(Advertise);
        Example example = new Example(Advertise.class);
        example.createCriteria().andEqualTo("id", Advertise.getId());
        AdvertiseMapper.updateByExampleSelective(Advertise, example);
    }

    @Override
    public PageInfo<Advertise> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(AdvertiseMapper.selectAll());
    }

    @Override
    public Advertise getById(String id) {
        Example example = new Example(Advertise.class);
        example.createCriteria().andEqualTo("id", id);
        return AdvertiseMapper.selectOneByExample(example);
    }
}
