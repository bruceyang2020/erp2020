package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.util.Date;
import javax.persistence.*;

@Table(name = "incomesheet")
public class Incomesheet extends BaseBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String period;

    @Column(name = "income_sale")
    private String incomeSale;

    @Column(name = "income_l")
    private String incomeL;

    @Column(name = "money_new")
    private String moneyNew;

    @Column(name = "money_norate")
    private String moneyNorate;

    @Column(name = "money_notax")
    private String moneyNotax;

    @Column(name = "money_income")
    private String moneyIncome;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "edit_user")
    private String editUser;

    @Column(name = "edit_time")
    private Date editTime;

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
     * @return period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param period
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * @return income_sale
     */
    public String getIncomeSale() {
        return incomeSale;
    }

    /**
     * @param incomeSale
     */
    public void setIncomeSale(String incomeSale) {
        this.incomeSale = incomeSale;
    }

    /**
     * @return income_l
     */
    public String getIncomeL() {
        return incomeL;
    }

    /**
     * @param incomeL
     */
    public void setIncomeL(String incomeL) {
        this.incomeL = incomeL;
    }

    /**
     * @return money_new
     */
    public String getMoneyNew() {
        return moneyNew;
    }

    /**
     * @param moneyNew
     */
    public void setMoneyNew(String moneyNew) {
        this.moneyNew = moneyNew;
    }

    /**
     * @return money_norate
     */
    public String getMoneyNorate() {
        return moneyNorate;
    }

    /**
     * @param moneyNorate
     */
    public void setMoneyNorate(String moneyNorate) {
        this.moneyNorate = moneyNorate;
    }

    /**
     * @return money_notax
     */
    public String getMoneyNotax() {
        return moneyNotax;
    }

    /**
     * @param moneyNotax
     */
    public void setMoneyNotax(String moneyNotax) {
        this.moneyNotax = moneyNotax;
    }

    /**
     * @return money_income
     */
    public String getMoneyIncome() {
        return moneyIncome;
    }

    /**
     * @param moneyIncome
     */
    public void setMoneyIncome(String moneyIncome) {
        this.moneyIncome = moneyIncome;
    }

    /**
     * @return create_user
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return edit_user
     */
    public String getEditUser() {
        return editUser;
    }

    /**
     * @param editUser
     */
    public void setEditUser(String editUser) {
        this.editUser = editUser;
    }

    /**
     * @return edit_time
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * @param editTime
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}