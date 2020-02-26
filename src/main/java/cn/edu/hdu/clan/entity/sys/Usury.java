package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "usury")
public class Usury extends BaseBean {


    @Column(name = "group_id")
    private String groupId;

    @Column(name = "team_count")
    private String teamCount;

    /**
     * 高利贷编码
     */
    @Column(name = "usury_number")
    private Integer usuryNumber;

    /**
     * 高利贷产生期间
     */
    private Integer period;

    /**
     * 金额
     */
    @Column(name = "money_total")
    private BigDecimal moneyTotal;

    /**
     * 剩余还款期
     */
    @Column(name = "surplus_period")
    private Integer surplusPeriod;

    /**
     * 创建人外键用户表
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建人
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
     * 获取高利贷编码
     *
     * @return usury_number - 高利贷编码
     */
    public Integer getUsuryNumber() {
        return usuryNumber;
    }

    /**
     * 设置高利贷编码
     *
     * @param usuryNumber 高利贷编码
     */
    public void setUsuryNumber(Integer usuryNumber) {
        this.usuryNumber = usuryNumber;
    }

    /**
     * 获取高利贷产生期间
     *
     * @return period - 高利贷产生期间
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置高利贷产生期间
     *
     * @param period 高利贷产生期间
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * 获取金额
     *
     * @return money_total - 金额
     */
    public BigDecimal getMoneyTotal() {
        return moneyTotal;
    }

    /**
     * 设置金额
     *
     * @param moneyTotal 金额
     */
    public void setMoneyTotal(BigDecimal moneyTotal) {
        this.moneyTotal = moneyTotal;
    }

    /**
     * 获取剩余还款期
     *
     * @return surplus_period - 剩余还款期
     */
    public Integer getSurplusPeriod() {
        return surplusPeriod;
    }

    /**
     * 设置剩余还款期
     *
     * @param surplusPeriod 剩余还款期
     */
    public void setSurplusPeriod(Integer surplusPeriod) {
        this.surplusPeriod = surplusPeriod;
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
     * 获取创建人
     *
     * @return create_time - 创建人
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建人
     *
     * @param createTime 创建人
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