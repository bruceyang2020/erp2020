package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "incomesheet")
public class Incomesheet extends BaseBean {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

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
     * 毛利
     */
    @Column(name = "income_l")
    private BigDecimal incomeL;

    /**
     * 折旧前利润
     */
    @Column(name = "money_new")
    private BigDecimal moneyNew;

    /**
     * 支付利息前利润
     */
    @Column(name = "money_norate")
    private BigDecimal moneyNorate;

    /**
     * 税前利润
     */
    @Column(name = "money_notax")
    private BigDecimal moneyNotax;

    /**
     * 净利润
     */
    @Column(name = "money_income")
    private BigDecimal moneyIncome;

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
    public BigDecimal getIncomeL() {
        return incomeL;
    }

    /**
     * 设置毛利
     *
     * @param incomeL 毛利
     */
    public void setIncomeL(BigDecimal incomeL) {
        this.incomeL = incomeL;
    }

    /**
     * 获取折旧前利润
     *
     * @return money_new - 折旧前利润
     */
    public BigDecimal getMoneyNew() {
        return moneyNew;
    }

    /**
     * 设置折旧前利润
     *
     * @param moneyNew 折旧前利润
     */
    public void setMoneyNew(BigDecimal moneyNew) {
        this.moneyNew = moneyNew;
    }

    /**
     * 获取支付利息前利润
     *
     * @return money_norate - 支付利息前利润
     */
    public BigDecimal getMoneyNorate() {
        return moneyNorate;
    }

    /**
     * 设置支付利息前利润
     *
     * @param moneyNorate 支付利息前利润
     */
    public void setMoneyNorate(BigDecimal moneyNorate) {
        this.moneyNorate = moneyNorate;
    }

    /**
     * 获取税前利润
     *
     * @return money_notax - 税前利润
     */
    public BigDecimal getMoneyNotax() {
        return moneyNotax;
    }

    /**
     * 设置税前利润
     *
     * @param moneyNotax 税前利润
     */
    public void setMoneyNotax(BigDecimal moneyNotax) {
        this.moneyNotax = moneyNotax;
    }

    /**
     * 获取净利润
     *
     * @return money_income - 净利润
     */
    public BigDecimal getMoneyIncome() {
        return moneyIncome;
    }

    /**
     * 设置净利润
     *
     * @param moneyIncome 净利润
     */
    public void setMoneyIncome(BigDecimal moneyIncome) {
        this.moneyIncome = moneyIncome;
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