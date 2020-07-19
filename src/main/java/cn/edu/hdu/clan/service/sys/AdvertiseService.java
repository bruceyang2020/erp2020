package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Advertise;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface AdvertiseService {
    void addList(List<Advertise> Advertise);

    void add(Advertise Advertise);

    void delete(String id);

    void deleteByTeamCount(String userTeam);

    void deleteByTeamCountAndPeriod(String userTeam ,int period);

    void update(Advertise Advertise);

    PageInfo<Advertise> list(int pageNum, int pageSize);

    List<Advertise> getByUserTeamAndPeriod(String userTeam,int period);

    Advertise getById(String id);
}
