package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.util.Date;
import javax.persistence.*;

@Table(name = "iso_fee")
public class IsoFee extends BaseBean {
    /**
     * 主键
     */
    @Id
    private String id;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "team_count")
    private Integer teamCount;

    /**
     * iso编号
     */
    private Integer number;

    /**
     * 状态 1完成0未完成
     */
    private Integer state;

    /**
     * 剩余认证期数
     */
    @Column(name = "period_left")
    private Integer periodLeft;

    /**
     * 当前会计期间
     */
    private Integer period;

    /**
     * 创建人外键用户表
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @Column(name = "creae_time")
    private Date creaeTime;

    /**
     * 编辑人
     */
    @Column(name = "edit-user")
    private String editUser;

    /**
     * 编辑时间
     */
    @Column(name = "edit_time")
    private Date editTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return group_id
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * @param groupId
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * @return team_count
     */
    public Integer getTeamCount() {
        return teamCount;
    }

    /**
     * @param teamCount
     */
    public void setTeamCount(Integer teamCount) {
        this.teamCount = teamCount;
    }

    /**
     * 获取iso编号
     *
     * @return number - iso编号
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置iso编号
     *
     * @param number iso编号
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取状态 1完成0未完成
     *
     * @return state - 状态 1完成0未完成
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态 1完成0未完成
     *
     * @param state 状态 1完成0未完成
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取剩余认证期数
     *
     * @return period_left - 剩余认证期数
     */
    public Integer getPeriodLeft() {
        return periodLeft;
    }

    /**
     * 设置剩余认证期数
     *
     * @param periodLeft 剩余认证期数
     */
    public void setPeriodLeft(Integer periodLeft) {
        this.periodLeft = periodLeft;
    }

    /**
     * 获取当前会计期间
     *
     * @return period - 当前会计期间
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置当前会计期间
     *
     * @param period 当前会计期间
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * 获取创建人外键用户表
     *
     * @return create_user - 创建人外键用户表
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人外键用户表
     *
     * @param createUser 创建人外键用户表
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取创建时间
     *
     * @return creae_time - 创建时间
     */
    public Date getCreaeTime() {
        return creaeTime;
    }

    /**
     * 设置创建时间
     *
     * @param creaeTime 创建时间
     */
    public void setCreaeTime(Date creaeTime) {
        this.creaeTime = creaeTime;
    }

    /**
     * 获取编辑人
     *
     * @return edit-user - 编辑人
     */
    public String getEditUser() {
        return editUser;
    }

    /**
     * 设置编辑人
     *
     * @param editUser 编辑人
     */
    public void setEditUser(String editUser) {
        this.editUser = editUser;
    }

    /**
     * 获取编辑时间
     *
     * @return edit_time - 编辑时间
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * 设置编辑时间
     *
     * @param editTime 编辑时间
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}