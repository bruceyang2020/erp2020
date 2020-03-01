package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.AccountBalance;
import cn.edu.hdu.clan.entity.sys.Incomesheet;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IncomesheetService {
    void add(Incomesheet Incomesheet);

    void adds(List<Incomesheet>  incomesheets);

    void delete(String id);

    void deleteByTeamCount(String userTeam);

    void update(Incomesheet Incomesheet);

    PageInfo<Incomesheet> list(int pageNum, int pageSize);
    List<Incomesheet> list();

    Incomesheet getById(String id);

    Incomesheet getincomesheet(Incomesheet incomesheet);

    Incomesheet getByUserTeamAndPeriod(String userTeam,int period);

    void createIncomeSheet(List<AccountBalance> accountBalances, String userTeam , int period);

}
