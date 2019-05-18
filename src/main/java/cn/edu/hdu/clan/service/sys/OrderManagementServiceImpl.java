package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.OrderManagement;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.OrderManagementMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class OrderManagementServiceImpl implements OrderManagementService {

    @Autowired
    private OrderManagementMapper OrderManagementMapper;

    @Transactional
    @Override
    public void add(OrderManagement OrderManagement) {
        BaseBeanHelper.insert(OrderManagement);
        OrderManagementMapper.insert(OrderManagement);
    }

    @Override
    public void delete(String id) {
    OrderManagementMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(OrderManagement OrderManagement) {
        BaseBeanHelper.edit(OrderManagement);
        Example example = new Example(OrderManagement.class);
        example.createCriteria().andEqualTo("id", OrderManagement.getId());
        OrderManagementMapper.updateByExampleSelective(OrderManagement, example);
    }

    @Override
    public PageInfo<OrderManagement> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(OrderManagementMapper.selectAll());
    }

    @Override
    public OrderManagement getById(String id) {
        Example example = new Example(OrderManagement.class);
        example.createCriteria().andEqualTo("id", id);
        return OrderManagementMapper.selectOneByExample(example);
    }
}
