package cn.edu.hdu.clan.mapper.sys;

import cn.edu.hdu.clan.entity.sys.Salepayment;
import tk.mybatis.mapper.common.Mapper;

public interface SalepaymentMapper extends Mapper<Salepayment> {
    public void save();
    public Salepayment query();
}