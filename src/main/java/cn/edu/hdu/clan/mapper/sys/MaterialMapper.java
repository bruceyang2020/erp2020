package cn.edu.hdu.clan.mapper.sys;

import cn.edu.hdu.clan.entity.sys.Material;
import tk.mybatis.mapper.common.Mapper;

public interface MaterialMapper extends Mapper<Material> {
    public void save();
    public Material query();
}