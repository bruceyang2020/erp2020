package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.AccountingVoucher;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.List;

public interface AccountingVoucherService {
    void add(AccountingVoucher AccountingVoucher);

    void delete(String id);

    void update(AccountingVoucher AccountingVoucher);

    PageInfo<AccountingVoucher> list(int pageNum, int pageSize);

    AccountingVoucher getById(String id);

    void  voucherMaker(String teamCount, int period , BigDecimal amount, String voucherType, String  content);

    BigDecimal sumMoney(String teamCount, int period,String acode,String aType);

 /*   void transferProfitAndLoss(String teamCount, int period);*/

    void deleteByPeriodAndContent(String userTeam,Integer period,String content);

    void deleteByTeamCount(String userTeam);

    void transferToProfitAndLoss(String teamCount, int period);//H

    List<AccountingVoucher> selectByPeriodAndUserTeam( String userTeam, Integer period);//H


}
