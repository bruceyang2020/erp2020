package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.AccountBalance;
import com.github.pagehelper.PageInfo;

public interface AccountBalanceService {
    void add(AccountBalance AccountBalance);

    void delete(String id);

    void update(AccountBalance AccountBalance);

    PageInfo<AccountBalance> list(int pageNum, int pageSize);

    AccountBalance getById(String id);
}
