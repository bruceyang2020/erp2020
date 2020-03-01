package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.util.Date;
import javax.persistence.*;

@Table(name = "iso_fee")
public class IsoFee extends BaseBean {


    @Column(name = "group_id")
    private String groupId;

    @Column(name = "team_count")
    private String teamCount;

    /**
     * iso编号
     */
    private String number;

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
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 编辑人
     */
    @Column(name = "edit_user")
    private String editUser;

    /**
     * 编辑时间
     */
    @Column(name = "edit_time")
    private Date editTime;



    /**
     * @return group_id
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @param groupId
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * @return team_count
     */
    public String getTeamCount() {
        return teamCount;
    }

    /**
     * @param teamCount
     */
    public void setTeamCount(String teamCount) {
        this.teamCount = teamCount;
    }

    /**
     * 获取iso编号
     *
     * @return number - iso编号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置iso编号
     *
     * @param number iso编号
     */
    public void setNumber(String number) {
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
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取编辑人
     *
     * @return edit_user - 编辑人
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