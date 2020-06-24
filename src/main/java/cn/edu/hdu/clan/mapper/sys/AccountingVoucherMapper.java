package cn.edu.hdu.clan.mapper.sys;

import cn.edu.hdu.clan.entity.sys.AccountingVoucher;
import cn.edu.hdu.clan.entity.PageData;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface AccountingVoucherMapper extends Mapper<AccountingVoucher> {

    @Select("select count(*) from  accounting_voucher where team_count = #{teamcount} and period =#{period} ")
    int mycount(PageData pd);
}