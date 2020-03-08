package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.LongTermLoans;
import cn.edu.hdu.clan.entity.sys.OrderManagement;
import cn.edu.hdu.clan.entity.sys.ShortTermLoan;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ShortTermLoanService {
    void add(ShortTermLoan ShortTermLoan);

    void delete(String id);

    void update(ShortTermLoan ShortTermLoan);

    PageInfo<ShortTermLoan> list(int pageNum, int pageSize);

    ShortTermLoan getById(String id);

    List<ShortTermLoan> getByUserIdAndPeriod(String userTeam);

    void voucherMakerOfInterestAndRepayment(String userTeam,int nextPeriod);

    void deleteByTeamCount(String userTeam);
}
