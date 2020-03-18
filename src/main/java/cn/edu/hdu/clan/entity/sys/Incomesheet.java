package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "incomesheet")
public class Incomesheet extends BaseBean {

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "team_count")
    private String teamCount;

    /**
     * 当前期数
     */
    private Integer period;


    /**
     * 销售收入
     */
    @Column(name = "income_sale")
    private BigDecimal incomeSale;

    /**
     * 直接成本
     */
    @Column(name = "money_cost")
    private BigDecimal moneyCost;

    /**
     * 折旧
     */
    @Column(name = "money_depr")
    private BigDecimal moneyDepr;

    /**
     * 综合费用
     */
    @Column(name = "money_fee")
    private BigDecimal moneyFee;

    /**
     * 财务支出
     */
    @Column(name = "money_interest")
    private BigDecimal moneyInterest;

    /**
     * 税前利润
     */
    @Column(name = "money_other")
    private BigDecimal moneyOther;

    /**
     * 所得税
     */
    @Column(name = "money_tax")
    private BigDecimal moneyTax;

    /**
     * 净利润
     */
    @Column(name = "net_income")
    private BigDecimal netIncome;

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
     * 获取当前期数
     *
     * @return period - 当前期数
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置当前期数
     *
     * @param period 当前期数
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * 获取销售收入
     *
     * @return income_sale - 销售收入
     */
    public BigDecimal getIncomeSale() {
        return incomeSale;
    }

    /**
     * 设置销售收入
     *
     * @param incomeSale 销售收入
     */
    public void setIncomeSale(BigDecimal incomeSale) {
        this.incomeSale = incomeSale;
    }

    /**
     * 获取毛利
     *
     * @return income_l - 毛利
     */
    public BigDecimal getMoneyCost() {
        return moneyCost;
    }

    /**
     * 设置毛利
     *
     * @param moneyCost 主营业务成本销售成本
     */
    public void setMoneyCost(BigDecimal moneyCost) { this.moneyCost = moneyCost;    }

    /**
     * 获取折旧前利润
     *
     * @return money_new - 折旧
     */
    public BigDecimal getMoneyDepr() {
        return moneyDepr;
    }

    /**
     * 设置折旧前利润
     *
     * @param moneyDepr 折旧
     */
    public void setMoneyDepr(BigDecimal moneyDepr) {
        this.moneyDepr = moneyDepr;
    }

    /**
     * 获取综合费用
     *
     * @return money_fee - 综合费用
     */
    public BigDecimal getMoneyFee() {
        return moneyFee;
    }

    /**
     * 设置综合费用
     *
     * @param moneyFee 综合费用
     */
    public void setMoneyFee(BigDecimal moneyFee) {
        this.moneyFee= moneyFee;
    }


    /**
     * 获取财务费用
     *
     * @return money_interest 财务费用
     */
    public BigDecimal getMoneyInterest(){
        return moneyInterest;
    }

    /**
     * 设置财务费用
     *
     * @param moneyInterest 财务费用
     */
    public void setMoneyInterest(BigDecimal moneyInterest) {
        this.moneyInterest= moneyInterest;
    }

    /**
     * 获取其他费用支出
     *
     * @return money_Other - 其他费用支出
     */
    public BigDecimal getMoneyOther() {
        return moneyOther;
    }

    /**
     * 设置其他费用支出
     *
     * @param moneyOther 其他费用支出
     */
    public void setMoneyOther(BigDecimal moneyOther) {
        this.moneyOther = moneyOther;
    }

    /**
     * 获取所得税
     *
     * @return money_Tax - 净利润
     */
    public BigDecimal getMoneyTax() {
        return moneyTax;
    }

    /**
     * 设置所得税
     *
     * @param moneyTax 净利润
     */
    public void setMoneyTax(BigDecimal moneyTax) {
        this.moneyTax = moneyTax;
    }


    /**
     * 获取净利润
     *
     * @return net_Income - 净利润
     */
    public BigDecimal getNetIncome() {
        return netIncome;
    }

    /**
     * 设置综合费用
     *
     * @param netIncome 综合费用
     */
    public void setNetIncome(BigDecimal netIncome) {
        this.moneyFee= netIncome;
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