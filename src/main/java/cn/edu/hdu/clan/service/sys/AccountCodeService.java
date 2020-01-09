package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.AccountCode;
import com.github.pagehelper.PageInfo;

public interface AccountCodeService {
    void add(AccountCode AccountCode);

    void delete(String id);

    void update(AccountCode AccountCode);

    PageInfo<AccountCode> list(int pageNum, int pageSize);

    AccountCode getById(String id);
}
