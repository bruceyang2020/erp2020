package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "factory")
public class Factory extends BaseBean {


    @Column(name = "group_id")
    private String groupId;

    @Column(name = "team_count")
    private String teamCount;

    /**
     * 厂房编号
     */
    private String number;

    /**
     * 厂房名称
     */
    private String name;

    /**
     * 当前会计期间
     */
    private Integer period;

    /**
     * 剩余容量
     */
    @Column(name = "left_capacity")
    private Integer leftCapacity;


    /**
     * 总金额
     */
    @Column(name = "money_total")
    private BigDecimal moneyTotal;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 租金
     */
    @Column(name = "rent")
    private Integer rent;

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
     * 获取厂房编号
     *
     * @return number - 厂房编号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置厂房编号
     *
     * @param number 厂房编号
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 获取厂房名称
     *
     * @return name - 厂房名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置厂房名称
     *
     * @param name 厂房名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取厂房租金
     *
     * @return rent - 厂房租金
     */
    public Integer getRent() {
        return rent;
    }

    /**
     * 设置厂房名称
     *
     * @param rent 厂房名称
     */
    public void setRent(Integer rent) {
        this.rent = rent;
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
     * 获取剩余容量
     *
     * @return left_capacity - 剩余容量
     */
    public Integer getLeftCapacity() {
        return leftCapacity;
    }

    /**
     * 设置剩余容量
     *
     * @param leftCapacity 剩余容量
     */
    public void setLeftCapacity(Integer leftCapacity) {
        this.leftCapacity = leftCapacity;
    }

    /**
     * 获取总金额
     *
     * @return money_total - 总金额
     */
    public BigDecimal getMoneyTotal() {
        return moneyTotal;
    }

    /**
     * 设置总金额
     *
     * @param moneyTotal 总金额
     */
    public void setMoneyTotal(BigDecimal moneyTotal) {
        this.moneyTotal = moneyTotal;
    }


    /**
     * 获取状态
     *
     * @return state - 状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态
     *
     * @param state 状态
     */
    public void setState(Integer state) {
        this.state = state;
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
     * @return create_time - 创建时间
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