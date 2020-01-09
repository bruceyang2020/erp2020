package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.MarketFee;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.MarketFeeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class MarketFeeServiceImpl implements MarketFeeService {

    @Autowired
    private MarketFeeMapper MarketFeeMapper;

    @Transactional
    @Override
    public void add(MarketFee MarketFee) {
        BaseBeanHelper.insert(MarketFee);
        MarketFeeMapper.insert(MarketFee);
    }

    @Override
    public void delete(String id) {
    MarketFeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(MarketFee MarketFee) {
        BaseBeanHelper.edit(MarketFee);
        Example example = new Example(MarketFee.class);
        example.createCriteria().andEqualTo("id", MarketFee.getId());
        MarketFeeMapper.updateByExampleSelective(MarketFee, example);
    }

    @Override
    public PageInfo<MarketFee> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(MarketFeeMapper.selectAll());
    }

    @Override
    public MarketFee getById(String id) {
        Example example = new Example(MarketFee.class);
        example.createCriteria().andEqualTo("id", id);
        return MarketFeeMapper.selectOneByExample(example);
    }
}
