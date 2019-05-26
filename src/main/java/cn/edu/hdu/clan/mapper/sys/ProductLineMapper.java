package cn.edu.hdu.clan.mapper.sys;

import cn.edu.hdu.clan.entity.sys.ProductLine;
import tk.mybatis.mapper.common.Mapper;

public interface ProductLineMapper extends Mapper<ProductLine> {
    public void save();
    public ProductLine query();
}