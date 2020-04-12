package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "inv")
public class Inv extends BaseBean {


    @Column(name = "group_id")
    private String groupId;

    @Column(name = "team_count")
    private String teamCount;

    /**
     * 当前会计期间
     */
    private Integer period;

    /**
     * 物料编码
     */
    private String number;

    /**
     * 期初数量
     */
    @Column(name = "amount_b")
    private BigDecimal amountB;

    /**
     * 期初金额
     */
    @Column(name = "money_b")
    private BigDecimal moneyB;

    /**
     * 本期入库数量
     */
    @Column(name = "amount_i")
    private BigDecimal amountI;

    /**
     * 本期入库金额
     */
    @Column(name = "money_i")
    private BigDecimal moneyI;

    /**
     * 本期出库数量
     */
    @Column(name = "amount_o")
    private BigDecimal amountO;

    /**
     * 本期出库金额
     */
    @Column(name = "money_o")
    private BigDecimal moneyO;

    /**
     * 成本
     */
    @Column(name = "cost")
    private BigDecimal cost;


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
     * 编辑用户
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
     * 获取物料编码
     *
     * @return number - 物料编码
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置物料编码
     *
     * @param number 物料编码
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 获取期初数量
     *
     * @return amount_b - 期初数量
     */
    public BigDecimal getAmountB() {
        return amountB;
    }

    /**
     * 设置期初数量
     *
     * @param amountB 期初数量
     */
    public void setAmountB(BigDecimal amountB) {
        this.amountB = amountB;
    }

    /**
     * 获取期初金额
     *
     * @return money_b - 期初金额
     */
    public BigDecimal getMoneyB() {
        return moneyB;
    }

    /**
     * 设置期初金额
     *
     * @param moneyB 期初金额
     */
    public void setMoneyB(BigDecimal moneyB) {
        this.moneyB = moneyB;
    }

    /**
     * 获取本期入库数量
     *
     * @return amount_l - 本期入库数量
     */
    public BigDecimal getAmountI() {
        return amountI;
    }

    /**
     * 设置本期入库数量
     *
     * @param amountI 本期入库数量
     */
    public void setAmountI(BigDecimal amountI) {
        this.amountI = amountI;
    }

    /**
     * 获取本期入库金额
     *
     * @return money_I - 本期入库金额
     */
    public BigDecimal getMoneyI() {
        return moneyI;
    }

    /**
     * 设置本期入库金额
     *
     * @param moneyI 本期入库金额
     */
    public void setMoneyI(BigDecimal moneyI) {
        this.moneyI = moneyI;
    }

    /**
     * 获取本期出库数量
     *
     * @return amount_o - 本期出库数量
     */
    public BigDecimal getAmountO() {
        return amountO;
    }

    /**
     * 设置本期出库数量
     *
     * @param amountO 本期出库数量
     */
    public void setAmountO(BigDecimal amountO) {
        this.amountO = amountO;
    }

    /**
     * 获取本期出库金额
     *
     * @return money_o - 本期出库金额
     */
    public BigDecimal getMoneyO() {
        return moneyO;
    }

    /**
     * 设置本期出库金额
     *
     * @param moneyO 本期出库金额
     */
    public void setMoneyO(BigDecimal moneyO) {
        this.moneyO = moneyO;
    }

    /**
     * 获取成本
     *
     * @return cost - 成本
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * 设置期初数量
     *
     * @param cost
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
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
     * 获取编辑用户
     *
     * @return edit_user - 编辑用户
     */
    public String getEditUser() {
        return editUser;
    }

    /**
     * 设置编辑用户
     *
     * @param editUser 编辑用户
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