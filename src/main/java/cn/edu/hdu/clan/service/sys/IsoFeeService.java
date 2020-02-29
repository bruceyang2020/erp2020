package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.IsoFee;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IsoFeeService {
    void add(IsoFee IsoFee);

    void delete(String id);

    void update(IsoFee IsoFee);

    PageInfo<IsoFee> list(int pageNum, int pageSize);

    IsoFee getById(String id);

    List<IsoFee> list(String userTeam , int period);

    void deleteByPeriod(String userTeam,Integer period,String number);
}
