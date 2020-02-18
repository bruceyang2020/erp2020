package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user")
public class SysUser extends BaseBean {


    /**
     * 公司id
     */
    @Column(name = "team_id")
    private String teamId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private Integer telephome;

    /**
     * 注册时间
     */
    @Column(name = "registration_time")
    private Date registrationTime;



    /**
     * 获取公司id
     *
     * @return team_id - 公司id
     */
    public String getTeamId() {
        return teamId;
    }

    /**
     * 设置公司id
     *
     * @param teamId 公司id
     */
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取电话
     *
     * @return telephome - 电话
     */
    public Integer getTelephome() {
        return telephome;
    }

    /**
     * 设置电话
     *
     * @param telephome 电话
     */
    public void setTelephome(Integer telephome) {
        this.telephome = telephome;
    }

    /**
     * 获取注册时间
     *
     * @return registration_time - 注册时间
     */
    public Date getRegistrationTime() {
        return registrationTime;
    }

    /**
     * 设置注册时间
     *
     * @param registrationTime 注册时间
     */
    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }
}