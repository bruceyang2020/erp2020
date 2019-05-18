package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "balancesheet")
public class Balancesheet extends BaseBean {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 当前会计期数1.5.9.13.17.21
     */
    private Integer period;

    /**
     * 现金
     */
    @Column(name = "money_now")
    private BigDecimal moneyNow;

    /**
     * 应收款
     */
    @Column(name = "money_get")
    private BigDecimal moneyGet;

    /**
     * 在制品
     */
    @Column(name = "money_making")
    private BigDecimal moneyMaking;

    /**
     * 成品
     */
    @Column(name = "money_sale")
    private BigDecimal moneySale;

    /**
     * 原材料
     */
    @Column(name = "money_buy")
    private BigDecimal moneyBuy;

    /**
     * 流动资产
     */
    @Column(name = "money_flow")
    private BigDecimal moneyFlow;

    /**
     * 长期贷款
     */
    @Column(name = "long_term_loan")
    private BigDecimal longTermLoan;

    /**
     * 短期贷款
     */
    @Column(name = "short_term_loan")
    private BigDecimal shortTermLoan;

    /**
     * 应收账款
     */
    @Column(name = "money_order_get")
    private BigDecimal moneyOrderGet;

    /**
     * 应交税金
     */
    @Column(name = "money_tax")
    private BigDecimal moneyTax;

    /**
     * 一年内到期长期贷款
     */
    @Column(name = "long_term_loan_year")
    private BigDecimal longTermLoanYear;

    /**
     * 负债合计
     */
    @Column(name = "money_loan")
    private BigDecimal moneyLoan;

    /**
     * 土地建筑
     */
    @Column(name = "money_j")
    private BigDecimal moneyJ;

    /**
     * 机器设备
     */
    @Column(name = "money_p")
    private BigDecimal moneyP;

    /**
     * 在建工程
     */
    @Column(name = "money_m")
    private BigDecimal moneyM;

    /**
     * 固定资产
     */
    @Column(name = "money_static")
    private BigDecimal moneyStatic;

    /**
     * 股东资本
     */
    @Column(name = "money_g")
    private BigDecimal moneyG;

    /**
     * 利润留存
     */
    @Column(name = "money_stay")
    private BigDecimal moneyStay;

    /**
     * 年度利润
     */
    @Column(name = "money_year")
    private BigDecimal moneyYear;

    /**
     * 所有者权益
     */
    @Column(name = "money_user")
    private BigDecimal moneyUser;

    /**
     * 资产总计
     */
    @Column(name = "money_all")
    private BigDecimal moneyAll;

    /**
     * 负债和所有者权益总计
     */
    @Column(name = "money_alls")
    private BigDecimal moneyAlls;

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
     * 获取当前会计期数1.5.9.13.17.21
     *
     * @return period - 当前会计期数1.5.9.13.17.21
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置当前会计期数1.5.9.13.17.21
     *
     * @param period 当前会计期数1.5.9.13.17.21
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * 获取现金
     *
     * @return money_now - 现金
     */
    public BigDecimal getMoneyNow() {
        return moneyNow;
    }

    /**
     * 设置现金
     *
     * @param moneyNow 现金
     */
    public void setMoneyNow(BigDecimal moneyNow) {
        this.moneyNow = moneyNow;
    }

    /**
     * 获取应收款
     *
     * @return money_get - 应收款
     */
    public BigDecimal getMoneyGet() {
        return moneyGet;
    }

    /**
     * 设置应收款
     *
     * @param moneyGet 应收款
     */
    public void setMoneyGet(BigDecimal moneyGet) {
        this.moneyGet = moneyGet;
    }

    /**
     * 获取在制品
     *
     * @return money_making - 在制品
     */
    public BigDecimal getMoneyMaking() {
        return moneyMaking;
    }

    /**
     * 设置在制品
     *
     * @param moneyMaking 在制品
     */
    public void setMoneyMaking(BigDecimal moneyMaking) {
        this.moneyMaking = moneyMaking;
    }

    /**
     * 获取成品
     *
     * @return money_sale - 成品
     */
    public BigDecimal getMoneySale() {
        return moneySale;
    }

    /**
     * 设置成品
     *
     * @param moneySale 成品
     */
    public void setMoneySale(BigDecimal moneySale) {
        this.moneySale = moneySale;
    }

    /**
     * 获取原材料
     *
     * @return money_buy - 原材料
     */
    public BigDecimal getMoneyBuy() {
        return moneyBuy;
    }

    /**
     * 设置原材料
     *
     * @param moneyBuy 原材料
     */
    public void setMoneyBuy(BigDecimal moneyBuy) {
        this.moneyBuy = moneyBuy;
    }

    /**
     * 获取流动资产
     *
     * @return money_flow - 流动资产
     */
    public BigDecimal getMoneyFlow() {
        return moneyFlow;
    }

    /**
     * 设置流动资产
     *
     * @param moneyFlow 流动资产
     */
    public void setMoneyFlow(BigDecimal moneyFlow) {
        this.moneyFlow = moneyFlow;
    }

    /**
     * 获取长期贷款
     *
     * @return long_term_loan - 长期贷款
     */
    public BigDecimal getLongTermLoan() {
        return longTermLoan;
    }

    /**
     * 设置长期贷款
     *
     * @param longTermLoan 长期贷款
     */
    public void setLongTermLoan(BigDecimal longTermLoan) {
        this.longTermLoan = longTermLoan;
    }

    /**
     * 获取短期贷款
     *
     * @return short_term_loan - 短期贷款
     */
    public BigDecimal getShortTermLoan() {
        return shortTermLoan;
    }

    /**
     * 设置短期贷款
     *
     * @param shortTermLoan 短期贷款
     */
    public void setShortTermLoan(BigDecimal shortTermLoan) {
        this.shortTermLoan = shortTermLoan;
    }

    /**
     * 获取应收账款
     *
     * @return money_order_get - 应收账款
     */
    public BigDecimal getMoneyOrderGet() {
        return moneyOrderGet;
    }

    /**
     * 设置应收账款
     *
     * @param moneyOrderGet 应收账款
     */
    public void setMoneyOrderGet(BigDecimal moneyOrderGet) {
        this.moneyOrderGet = moneyOrderGet;
    }

    /**
     * 获取应交税金
     *
     * @return money_tax - 应交税金
     */
    public BigDecimal getMoneyTax() {
        return moneyTax;
    }

    /**
     * 设置应交税金
     *
     * @param moneyTax 应交税金
     */
    public void setMoneyTax(BigDecimal moneyTax) {
        this.moneyTax = moneyTax;
    }

    /**
     * 获取一年内到期长期贷款
     *
     * @return long_term_loan_year - 一年内到期长期贷款
     */
    public BigDecimal getLongTermLoanYear() {
        return longTermLoanYear;
    }

    /**
     * 设置一年内到期长期贷款
     *
     * @param longTermLoanYear 一年内到期长期贷款
     */
    public void setLongTermLoanYear(BigDecimal longTermLoanYear) {
        this.longTermLoanYear = longTermLoanYear;
    }

    /**
     * 获取负债合计
     *
     * @return money_loan - 负债合计
     */
    public BigDecimal getMoneyLoan() {
        return moneyLoan;
    }

    /**
     * 设置负债合计
     *
     * @param moneyLoan 负债合计
     */
    public void setMoneyLoan(BigDecimal moneyLoan) {
        this.moneyLoan = moneyLoan;
    }

    /**
     * 获取土地建筑
     *
     * @return money_j - 土地建筑
     */
    public BigDecimal getMoneyJ() {
        return moneyJ;
    }

    /**
     * 设置土地建筑
     *
     * @param moneyJ 土地建筑
     */
    public void setMoneyJ(BigDecimal moneyJ) {
        this.moneyJ = moneyJ;
    }

    /**
     * 获取机器设备
     *
     * @return money_p - 机器设备
     */
    public BigDecimal getMoneyP() {
        return moneyP;
    }

    /**
     * 设置机器设备
     *
     * @param moneyP 机器设备
     */
    public void setMoneyP(BigDecimal moneyP) {
        this.moneyP = moneyP;
    }

    /**
     * 获取在建工程
     *
     * @return money_m - 在建工程
     */
    public BigDecimal getMoneyM() {
        return moneyM;
    }

    /**
     * 设置在建工程
     *
     * @param moneyM 在建工程
     */
    public void setMoneyM(BigDecimal moneyM) {
        this.moneyM = moneyM;
    }

    /**
     * 获取固定资产
     *
     * @return money_static - 固定资产
     */
    public BigDecimal getMoneyStatic() {
        return moneyStatic;
    }

    /**
     * 设置固定资产
     *
     * @param moneyStatic 固定资产
     */
    public void setMoneyStatic(BigDecimal moneyStatic) {
        this.moneyStatic = moneyStatic;
    }

    /**
     * 获取股东资本
     *
     * @return money_g - 股东资本
     */
    public BigDecimal getMoneyG() {
        return moneyG;
    }

    /**
     * 设置股东资本
     *
     * @param moneyG 股东资本
     */
    public void setMoneyG(BigDecimal moneyG) {
        this.moneyG = moneyG;
    }

    /**
     * 获取利润留存
     *
     * @return money_stay - 利润留存
     */
    public BigDecimal getMoneyStay() {
        return moneyStay;
    }

    /**
     * 设置利润留存
     *
     * @param moneyStay 利润留存
     */
    public void setMoneyStay(BigDecimal moneyStay) {
        this.moneyStay = moneyStay;
    }

    /**
     * 获取年度利润
     *
     * @return money_year - 年度利润
     */
    public BigDecimal getMoneyYear() {
        return moneyYear;
    }

    /**
     * 设置年度利润
     *
     * @param moneyYear 年度利润
     */
    public void setMoneyYear(BigDecimal moneyYear) {
        this.moneyYear = moneyYear;
    }

    /**
     * 获取所有者权益
     *
     * @return money_user - 所有者权益
     */
    public BigDecimal getMoneyUser() {
        return moneyUser;
    }

    /**
     * 设置所有者权益
     *
     * @param moneyUser 所有者权益
     */
    public void setMoneyUser(BigDecimal moneyUser) {
        this.moneyUser = moneyUser;
    }

    /**
     * 获取资产总计
     *
     * @return money_all - 资产总计
     */
    public BigDecimal getMoneyAll() {
        return moneyAll;
    }

    /**
     * 设置资产总计
     *
     * @param moneyAll 资产总计
     */
    public void setMoneyAll(BigDecimal moneyAll) {
        this.moneyAll = moneyAll;
    }

    /**
     * 获取负债和所有者权益总计
     *
     * @return money_alls - 负债和所有者权益总计
     */
    public BigDecimal getMoneyAlls() {
        return moneyAlls;
    }

    /**
     * 设置负债和所有者权益总计
     *
     * @param moneyAlls 负债和所有者权益总计
     */
    public void setMoneyAlls(BigDecimal moneyAlls) {
        this.moneyAlls = moneyAlls;
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