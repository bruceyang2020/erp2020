package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Market;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.MarketMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    private MarketMapper MarketMapper;

    @Transactional
    @Override
    public void add(Market Market) {
        BaseBeanHelper.insert(Market);
        MarketMapper.insert(Market);
    }

    @Override
    public void delete(String id) {
    MarketMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Market Market) {
        BaseBeanHelper.edit(Market);
        Example example = new Example(Market.class);
        example.createCriteria().andEqualTo("id", Market.getId());
        MarketMapper.updateByExampleSelective(Market, example);
    }

    @Override
    public PageInfo<Market> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(MarketMapper.selectAll());
    }

    @Override
    public Market getById(String id) {
        Example example = new Example(Market.class);
        example.createCriteria().andEqualTo("id", id);
        return MarketMapper.selectOneByExample(example);
    }
}
