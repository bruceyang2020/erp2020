package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Balancesheet;
import cn.edu.hdu.clan.entity.sys.LongTermLoans;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface LongTermLoansService {
    void add(LongTermLoans LongTermLoans);

    void adds(List<LongTermLoans>  longTermLoans);

    void delete(String id);

    void deleteByTeamCount(String userTeam);

    void update(LongTermLoans LongTermLoans);

    PageInfo<LongTermLoans> list(int pageNum, int pageSize);

    LongTermLoans getById(String id);

    List<LongTermLoans> list();

    List<LongTermLoans> getByUserTeamIdAndPeriod(String userTeam);
}
