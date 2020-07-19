package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.IsoFee;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IsoFeeService {
    String add(IsoFee IsoFee);

   void adds(List<IsoFee>  isoFees);

    void delete(String id);

    void deleteByTeamCount(String userTeam);

    void deleteByTeamCountAndPeriod(String userTeam ,int period);

    void update(IsoFee IsoFee);

    PageInfo<IsoFee> list(int pageNum, int pageSize);

    IsoFee getById(String id);

    List<IsoFee> list(String userTeam , int period);

    String deleteByPeriod(String userTeam,Integer period,String number);

    void copyDataToNextPeriod(String userTeam, int period, int nextPeriod);

    List<IsoFee> listByperiod(String userTeam ,int period,String number);
}
