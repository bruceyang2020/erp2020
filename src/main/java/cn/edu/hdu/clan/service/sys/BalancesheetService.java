package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.AccountBalance;
import cn.edu.hdu.clan.entity.sys.Balancesheet;
import cn.edu.hdu.clan.entity.sys.Incomesheet;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.List;

public interface BalancesheetService {
    void add(Balancesheet Balancesheet);

    void adds(List<Balancesheet>  balancesheets);

    void delete(String id);

    void deleteByTeamCount(String userTeam);

    void deleteByTeamCountAndPeriod(String userTeam ,int period);

    void update(Balancesheet Balancesheet);

    PageInfo<Balancesheet> list(int pageNum, int pageSize);

    List<Balancesheet> list();

    Balancesheet getById(String id);

    List<Balancesheet>  getByUserIdAndPeriod(String create_user,int period);

    Balancesheet  getByUserTeamAndPeriod(String userTeam,int period);


    void createBalanceSheet(List<AccountBalance> accountBalances,String userTeam ,int period);

    void copyDataToNextPeriod(String userTeam, int period, int nextPeriod);

}
