package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.AccountingVoucher;
import com.github.pagehelper.PageInfo;

public interface AccountingVoucherService {
    void add(AccountingVoucher AccountingVoucher);

    void delete(String id);

    void update(AccountingVoucher AccountingVoucher);

    PageInfo<AccountingVoucher> list(int pageNum, int pageSize);

    AccountingVoucher getById(String id);
}
