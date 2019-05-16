package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.${beanName};
import com.github.pagehelper.PageInfo;

public interface ${beanName}Service {
    void add(${beanName} ${name});

    void delete(String id);

    void update(${beanName} ${name});

    PageInfo<${beanName}> list(int pageNum, int pageSize);

    ${beanName} getById(String id);
}
