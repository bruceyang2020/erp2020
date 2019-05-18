package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Material;
import com.github.pagehelper.PageInfo;

public interface MaterialService {
    void add(Material Material);

    void delete(String id);

    void update(Material Material);

    PageInfo<Material> list(int pageNum, int pageSize);

    Material getById(String id);
}
