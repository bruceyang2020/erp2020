package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "inv")
public class Inv extends BaseBean {
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
     * 当前会计期间
     */
    private Integer period;

    /**
     * 物料编码
     */
    private Integer number;

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
    @Column(name = "amount_l")
    private BigDecimal amountL;

    /**
     * 本期入库金额
     */
    @Column(name = "money_l")
    private BigDecimal moneyL;

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
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置物料编码
     *
     * @param number 物料编码
     */
    public void setNumber(Integer number) {
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
    public BigDecimal getAmountL() {
        return amountL;
    }

    /**
     * 设置本期入库数量
     *
     * @param amountL 本期入库数量
     */
    public void setAmountL(BigDecimal amountL) {
        this.amountL = amountL;
    }

    /**
     * 获取本期入库金额
     *
     * @return money_l - 本期入库金额
     */
    public BigDecimal getMoneyL() {
        return moneyL;
    }

    /**
     * 设置本期入库金额
     *
     * @param moneyL 本期入库金额
     */
    public void setMoneyL(BigDecimal moneyL) {
        this.moneyL = moneyL;
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