package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.ResearchFee;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface ResearchFeeService {
    void add(ResearchFee ResearchFee);

    void  adds(List<ResearchFee>  researchFees);

    void delete(String id);

    void deleteByTeamCount(String userTeam);

    void update(ResearchFee ResearchFee);

    PageInfo<ResearchFee> list(int pageNum, int pageSize);

    ResearchFee getById(String id);

    List<ResearchFee> list(String userTeam ,int period);

    void deleteByPeriod(String userTeam,Integer period,String productId);

    void copyDataToNextPeriod(String userTeam, int period, int nextPeriod);

    List<ResearchFee> listByperiod(String userTeam ,int period,String productId);
}
