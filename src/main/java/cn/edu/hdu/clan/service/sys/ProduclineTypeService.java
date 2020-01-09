package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.ProduclineType;
import com.github.pagehelper.PageInfo;

public interface ProduclineTypeService {
    void add(ProduclineType ProduclineType);

    void delete(String id);

    void update(ProduclineType ProduclineType);

    PageInfo<ProduclineType> list(int pageNum, int pageSize);

    ProduclineType getById(String id);
}
