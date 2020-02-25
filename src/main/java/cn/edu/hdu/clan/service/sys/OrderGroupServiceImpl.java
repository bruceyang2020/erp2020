package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.OrderGroup;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.OrderGroupMapper;
import cn.edu.hdu.clan.util.Jurisdiction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

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
    public List<OrderGroup> list(String productId) {

        //全局变量 写入当前公司或小组ID
        int period =  Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        Example example = new Example(OrderGroup.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", productId);
        criteria.andEqualTo("period", period);

        return OrderGroupMapper.selectByExample(example);
    }

    @Override
    public OrderGroup getById(String id) {
        Example example = new Example(OrderGroup.class);
        example.createCriteria().andEqualTo("id", id);
        return OrderGroupMapper.selectOneByExample(example);
    }


    @Override
    public OrderGroup getByOrderId(String orderId) {
        Example example = new Example(OrderGroup.class);
        example.createCriteria().andEqualTo("orderId", orderId);
        return OrderGroupMapper.selectOneByExample(example);
    }
}
