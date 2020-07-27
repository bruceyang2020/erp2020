package cn.edu.hdu.clan.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * 说明：权限工具类
 * 作者：FH Admin Q313596790
 * 官网：www.fhadmin.org
 */
public class Jurisdiction {
	
	/**shiro管理的session
	 * @return
	 */
	public static Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}
	
	/**获取当前登录的用户名
	 * @return
	 */
	public static String getUsername(){
		return getSession().getAttribute(Const.SESSION_USERNAME).toString();
	}
	
	/**获取当前登录的姓名
	 * @return
	 */
	public static String getName(){
		return getSession().getAttribute(Const.SESSION_U_NAME).toString();
	}
	
	/**获取当前登录用户的角色编码
	 * @return
	 */
	public static String getRnumbers(){
		return getSession().getAttribute(Const.SESSION_RNUMBERS).toString();
	}

	/**获取当前登录的用户名
	 * @return
	 */
	public static String getUserId(){
		return getSession().getAttribute(Const.SESSION_USERID).toString();
	}

	/**获取当前登录的用户所在的分组或公司
	 * @return
	 */
	public static String getUserTeam(){
		return getSession().getAttribute(Const.SESSION_USERTEAM).toString();
	}

	/**获取当前登录的用户所在的分组或公司对应的会计期间
	 * @return
	 */
	public static String  getUserTeamintPeriod(){
		return getSession().getAttribute(Const.SESSION_USERPERIOD).toString();
	}


	/**获取当前登录的用户的EID
	 * @return
	 */
	public static String  getUserEID(){
		return getSession().getAttribute(Const.SESSION_EID).toString();
	}

	/**获取当前登录的用户的Ilab
	 * @return
	 */
	public static String  getUserIlabName(){
		return getSession().getAttribute(Const.SESSION_ILABNAME).toString();
	}


}
