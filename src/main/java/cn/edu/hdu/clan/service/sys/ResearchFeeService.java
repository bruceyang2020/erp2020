package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.ResearchFee;
import com.github.pagehelper.PageInfo;

public interface ResearchFeeService {
    void add(ResearchFee ResearchFee);

    void delete(String id);

    void update(ResearchFee ResearchFee);

    PageInfo<ResearchFee> list(int pageNum, int pageSize);

    ResearchFee getById(String id);
}
