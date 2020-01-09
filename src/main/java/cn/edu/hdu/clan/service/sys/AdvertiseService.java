package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Advertise;
import com.github.pagehelper.PageInfo;

public interface AdvertiseService {
    void add(Advertise Advertise);

    void delete(String id);

    void update(Advertise Advertise);

    PageInfo<Advertise> list(int pageNum, int pageSize);

    Advertise getById(String id);
}
