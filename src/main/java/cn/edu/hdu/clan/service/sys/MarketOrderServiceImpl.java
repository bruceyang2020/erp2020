package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.MarketOrder;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.MarketOrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class MarketOrderServiceImpl implements MarketOrderService {

    @Autowired
    private MarketOrderMapper MarketOrderMapper;

    @Transactional
    @Override
    public void add(MarketOrder MarketOrder) {
        BaseBeanHelper.insert(MarketOrder);
        MarketOrderMapper.insert(MarketOrder);
    }

    @Override
    public void delete(String id) {
    MarketOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(MarketOrder MarketOrder) {
        BaseBeanHelper.edit(MarketOrder);
        Example example = new Example(MarketOrder.class);
        example.createCriteria().andEqualTo("id", MarketOrder.getId());
        MarketOrderMapper.updateByExampleSelective(MarketOrder, example);
    }

    @Override
    public PageInfo<MarketOrder> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(MarketOrderMapper.selectAll());
    }

    @Override
    public MarketOrder getById(String id) {
        Example example = new Example(MarketOrder.class);
        example.createCriteria().andEqualTo("id", id);
        return MarketOrderMapper.selectOneByExample(example);
    }
}
