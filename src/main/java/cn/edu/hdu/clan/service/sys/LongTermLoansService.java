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

    void deleteByTeamCountAndPeriod(String userTeam ,int period);

    void update(LongTermLoans LongTermLoans);

    PageInfo<LongTermLoans> list(int pageNum, int pageSize);

    LongTermLoans getById(String id);

    List<LongTermLoans> list();

    List<LongTermLoans> getByUserTeamIdAndPeriod(String userTeam,int period);
    //H 长贷利息期初还息记入下一年度财务费用
    void voucherMakerOfInterestAndRepayment(String userTeam,int nextPeriod);

    void copyDataToNextPeriod(String userTeam, int period, int nextPeriod);//H 长贷复制到下一个会计期间

}
