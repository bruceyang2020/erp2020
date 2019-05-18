package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.LoanType;
import com.github.pagehelper.PageInfo;

public interface LoanTypeService {
    void add(LoanType LoanType);

    void delete(String id);

    void update(LoanType LoanType);

    PageInfo<LoanType> list(int pageNum, int pageSize);

    LoanType getById(String id);
}
