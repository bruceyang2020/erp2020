package cn.edu.hdu.clan.mapper.sys;

import cn.edu.hdu.clan.entity.sys.LongTermLoans;
import tk.mybatis.mapper.common.Mapper;

public interface LongTermLoansMapper extends Mapper<LongTermLoans> {
    public void save();
    public LongTermLoans query();
}