package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.${beanName};
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.${beanName}Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class ${beanName}ServiceImpl implements ${beanName}Service {

    @Autowired
    private ${beanName}Mapper ${name}Mapper;

    @Transactional
    @Override
    public void add(${beanName} ${name}) {
        BaseBeanHelper.insert(${name});
        ${name}Mapper.insert(${name});
    }

    @Override
    public void delete(String id) {
    ${name}Mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(${beanName} ${name}) {
        BaseBeanHelper.edit(${name});
        Example example = new Example(${beanName}.class);
        example.createCriteria().andEqualTo("id", ${name}.getId());
        ${name}Mapper.updateByExampleSelective(${name}, example);
    }

    @Override
    public PageInfo<${beanName}> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(${name}Mapper.selectAll());
    }

    @Override
    public ${beanName} getById(String id) {
        Example example = new Example(${beanName}.class);
        example.createCriteria().andEqualTo("id", id);
        return ${name}Mapper.selectOneByExample(example);
    }
}
