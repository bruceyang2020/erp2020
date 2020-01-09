package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Plan;
import com.github.pagehelper.PageInfo;

public interface PlanService {
    void add(Plan Plan);

    void delete(String id);

    void update(Plan Plan);

    PageInfo<Plan> list(int pageNum, int pageSize);

    Plan getById(String id);
}
