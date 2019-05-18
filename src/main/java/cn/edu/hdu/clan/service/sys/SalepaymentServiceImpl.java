package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Salepayment;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.SalepaymentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class SalepaymentServiceImpl implements SalepaymentService {

    @Autowired
    private SalepaymentMapper SalepaymentMapper;

    @Transactional
    @Override
    public void add(Salepayment Salepayment) {
        BaseBeanHelper.insert(Salepayment);
        SalepaymentMapper.insert(Salepayment);
    }

    @Override
    public void delete(String id) {
    SalepaymentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Salepayment Salepayment) {
        BaseBeanHelper.edit(Salepayment);
        Example example = new Example(Salepayment.class);
        example.createCriteria().andEqualTo("id", Salepayment.getId());
        SalepaymentMapper.updateByExampleSelective(Salepayment, example);
    }

    @Override
    public PageInfo<Salepayment> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(SalepaymentMapper.selectAll());
    }

    @Override
    public Salepayment getById(String id) {
        Example example = new Example(Salepayment.class);
        example.createCriteria().andEqualTo("id", id);
        return SalepaymentMapper.selectOneByExample(example);
    }
}
