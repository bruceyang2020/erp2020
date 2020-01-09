package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.OrderGroup;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.OrderGroupMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class OrderGroupServiceImpl implements OrderGroupService {

    @Autowired
    private OrderGroupMapper OrderGroupMapper;

    @Transactional
    @Override
    public void add(OrderGroup OrderGroup) {
        BaseBeanHelper.insert(OrderGroup);
        OrderGroupMapper.insert(OrderGroup);
    }

    @Override
    public void delete(String id) {
    OrderGroupMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(OrderGroup OrderGroup) {
        BaseBeanHelper.edit(OrderGroup);
        Example example = new Example(OrderGroup.class);
        example.createCriteria().andEqualTo("id", OrderGroup.getId());
        OrderGroupMapper.updateByExampleSelective(OrderGroup, example);
    }

    @Override
    public PageInfo<OrderGroup> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(OrderGroupMapper.selectAll());
    }

    @Override
    public OrderGroup getById(String id) {
        Example example = new Example(OrderGroup.class);
        example.createCriteria().andEqualTo("id", id);
        return OrderGroupMapper.selectOneByExample(example);
    }
}
