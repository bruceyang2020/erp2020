package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Advertise;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface AdvertiseService {
    void addList(List<Advertise> Advertise);

    void add(Advertise Advertise);

    void delete(String id);

    void update(Advertise Advertise);

    PageInfo<Advertise> list(int pageNum, int pageSize);

    Advertise getById(String id);
}
