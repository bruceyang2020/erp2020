package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.AccountBalance;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.List;

public interface AccountBalanceService {
    void add(AccountBalance AccountBalance);

    void adds(List<AccountBalance>  accountBalanceList);

    void delete(String id);

    void deleteByTeamCount(String userTeam);

    void update(AccountBalance AccountBalance);

    void deleteByPeriod(String userTeam ,int period);

    void sumFromVoucher(String userTeam ,int period);

    PageInfo<AccountBalance> list(int pageNum, int pageSize);

    AccountBalance getById(String id);

    List<AccountBalance> getByTeamcountAndPeriod(String userTeam ,int period);

    void copyDataToNextPeriod(String userTeam, int period, int nextPeriod);

    BigDecimal moneyAsFar(String userTeam , int period);
}
