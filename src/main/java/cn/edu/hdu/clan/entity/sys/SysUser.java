package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;

import javax.persistence.*;

@Table(name = "sys_user")
public class SysUser extends BaseBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "team_id")
    private String teamId;

    private String username;

    private String password;

    private String telephome;

    @Column(name = "registration_time")
    private String registrationTime;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return team_id
     */
    public String getTeamId() {
        return teamId;
    }

    /**
     * @param teamId
     */
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return telephome
     */
    public String getTelephome() {
        return telephome;
    }

    /**
     * @param telephome
     */
    public void setTelephome(String telephome) {
        this.telephome = telephome;
    }

    /**
     * @return registration_time
     */
    public String getRegistrationTime() {
        return registrationTime;
    }

    /**
     * @param registrationTime
     */
    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }
}