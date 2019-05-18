package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.ShortTermLoan;
import com.github.pagehelper.PageInfo;

public interface ShortTermLoanService {
    void add(ShortTermLoan ShortTermLoan);

    void delete(String id);

    void update(ShortTermLoan ShortTermLoan);

    PageInfo<ShortTermLoan> list(int pageNum, int pageSize);

    ShortTermLoan getById(String id);
}
