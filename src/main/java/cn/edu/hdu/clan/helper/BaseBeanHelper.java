package cn.edu.hdu.clan.helper;

import cn.edu.hdu.clan.entity.BaseBean;

import java.util.Date;

public class BaseBeanHelper {
    public static void insert(BaseBean baseBean) {
        baseBean.setId(UUIDHelper.getUUID());
        baseBean.setCreateTime(new Date());
        baseBean.setCreateUser(SessionHelper.getUserId());
    }

    public static void edit(BaseBean baseBean) {
        baseBean.setEditTime(new Date());
        baseBean.setEditUser(SessionHelper.getUserId());
    }
}
