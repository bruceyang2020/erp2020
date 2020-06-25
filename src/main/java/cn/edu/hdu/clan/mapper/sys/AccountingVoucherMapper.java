package cn.edu.hdu.clan.mapper.sys;

import cn.edu.hdu.clan.entity.sys.AccountingVoucher;
import cn.edu.hdu.clan.entity.PageData;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

public interface AccountingVoucherMapper extends Mapper<AccountingVoucher> {

    @Select("select count(*) from  accounting_voucher where team_count = #{teamcount} and period =#{period} ")
    int mycount(PageData pd);

    @Select("SELECT   acode,SUM(IFNULL(money_d,0)) AS moneyD ,SUM(IFNULL(money_c,0)) AS moneyC FROM  accounting_voucher WHERE  team_count = #{teamcount} AND period =#{period} GROUP BY acode")
    List<PageData> sumDAndCByCode(PageData pd);
}