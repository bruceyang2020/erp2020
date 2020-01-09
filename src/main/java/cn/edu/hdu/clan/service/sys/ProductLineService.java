package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.ProductLine;
import com.github.pagehelper.PageInfo;

public interface ProductLineService {
    void add(ProductLine ProductLine);

    void delete(String id);

    void update(ProductLine ProductLine);

    PageInfo<ProductLine> list(int pageNum, int pageSize);

    ProductLine getById(String id);
}
