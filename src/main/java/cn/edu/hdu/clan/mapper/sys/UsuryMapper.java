package cn.edu.hdu.clan.mapper.sys;

import cn.edu.hdu.clan.entity.sys.Usury;
import tk.mybatis.mapper.common.Mapper;

public interface UsuryMapper extends Mapper<Usury> {
    public void save(int period);
    public Usury query();
}