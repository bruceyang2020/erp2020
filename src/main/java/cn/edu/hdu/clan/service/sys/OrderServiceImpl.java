package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Order;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.OrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper OrderMapper;

    @Transactional
    @Override
    public void add(Order Order) {
        BaseBeanHelper.insert(Order);
        OrderMapper.insert(Order);
    }

    @Override
    public void delete(String id) {
    OrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order Order) {
        BaseBeanHelper.edit(Order);
        Example example = new Example(Order.class);
        example.createCriteria().andEqualTo("id", Order.getId());
        OrderMapper.updateByExampleSelective(Order, example);
    }

    @Override
    public PageInfo<Order> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(OrderMapper.selectAll());
    }

    @Override
    public Order getById(String id) {
        Example example = new Example(Order.class);
        example.createCriteria().andEqualTo("id", id);
        return OrderMapper.selectOneByExample(example);
    }
}
