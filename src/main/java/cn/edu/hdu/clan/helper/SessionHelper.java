package cn.edu.hdu.clan.helper;

import cn.edu.hdu.clan.entity.sys.SysGroup;
import cn.edu.hdu.clan.entity.sys.SysTeam;
import cn.edu.hdu.clan.entity.sys.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import static cn.edu.hdu.clan.constants.SessionConstants.*;

public class SessionHelper {
    public static void setUser(SysUser user) {
        getSession().setAttribute(USER, user);
    }

    public static String getUserId() {
        return ((SysUser) getSession().getAttribute(USER)).getId();
    }

    public static String getUsername() {
        return ((SysUser) getSession().getAttribute(USER)).getUsername();
    }

    public static SysUser getUser() {
        return (SysUser) getSession().getAttribute(USER);
    }

    public static void setTeam(SysTeam team) {
        getSession().setAttribute(TEAM, team);

    }

    public static SysTeam getTeam() {
        return (SysTeam) getSession().getAttribute(TEAM);
    }

    public static void setGroup(SysGroup group) {
        getSession().setAttribute(GROUP, group);

    }

    public static SysGroup getGroup() {
        return (SysGroup) getSession().getAttribute(GROUP);

    }


    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }
}
