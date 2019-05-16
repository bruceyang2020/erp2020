package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.util.Date;
import javax.persistence.*;

@Table(name = "balancesheet")
public class Balancesheet extends BaseBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String period;

    @Column(name = "money_now")
    private String moneyNow;

    @Column(name = "money_get")
    private String moneyGet;

    @Column(name = "money_making")
    private String moneyMaking;

    @Column(name = "money_sale")
    private String moneySale;

    @Column(name = "money_buy")
    private String moneyBuy;

    @Column(name = "money_flow")
    private String moneyFlow;

    @Column(name = "longterm_loan")
    private String longtermLoan;

    @Column(name = "shortterm_loan")
    private String shorttermLoan;

    @Column(name = "money_orderget")
    private String moneyOrderget;

    @Column(name = "money_tax")
    private String moneyTax;

    @Column(name = "longterm_loanyear")
    private String longtermLoanyear;

    @Column(name = "money_loan")
    private String moneyLoan;

    @Column(name = "money_j")
    private String moneyJ;

    @Column(name = "money_p")
    private String moneyP;

    @Column(name = "money_m")
    private String moneyM;

    @Column(name = "money_static")
    private String moneyStatic;

    @Column(name = "money_g")
    private String moneyG;

    @Column(name = "money_stay")
    private String moneyStay;

    @Column(name = "money_year")
    private String moneyYear;

    @Column(name = "money_user")
    private String moneyUser;

    @Column(name = "money_all")
    private String moneyAll;

    @Column(name = "money_alls")
    private String moneyAlls;

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
     * @return money_now
     */
    public String getMoneyNow() {
        return moneyNow;
    }

    /**
     * @param moneyNow
     */
    public void setMoneyNow(String moneyNow) {
        this.moneyNow = moneyNow;
    }

    /**
     * @return money_get
     */
    public String getMoneyGet() {
        return moneyGet;
    }

    /**
     * @param moneyGet
     */
    public void setMoneyGet(String moneyGet) {
        this.moneyGet = moneyGet;
    }

    /**
     * @return money_making
     */
    public String getMoneyMaking() {
        return moneyMaking;
    }

    /**
     * @param moneyMaking
     */
    public void setMoneyMaking(String moneyMaking) {
        this.moneyMaking = moneyMaking;
    }

    /**
     * @return money_sale
     */
    public String getMoneySale() {
        return moneySale;
    }

    /**
     * @param moneySale
     */
    public void setMoneySale(String moneySale) {
        this.moneySale = moneySale;
    }

    /**
     * @return money_buy
     */
    public String getMoneyBuy() {
        return moneyBuy;
    }

    /**
     * @param moneyBuy
     */
    public void setMoneyBuy(String moneyBuy) {
        this.moneyBuy = moneyBuy;
    }

    /**
     * @return money_flow
     */
    public String getMoneyFlow() {
        return moneyFlow;
    }

    /**
     * @param moneyFlow
     */
    public void setMoneyFlow(String moneyFlow) {
        this.moneyFlow = moneyFlow;
    }

    /**
     * @return longterm_loan
     */
    public String getLongtermLoan() {
        return longtermLoan;
    }

    /**
     * @param longtermLoan
     */
    public void setLongtermLoan(String longtermLoan) {
        this.longtermLoan = longtermLoan;
    }

    /**
     * @return shortterm_loan
     */
    public String getShorttermLoan() {
        return shorttermLoan;
    }

    /**
     * @param shorttermLoan
     */
    public void setShorttermLoan(String shorttermLoan) {
        this.shorttermLoan = shorttermLoan;
    }

    /**
     * @return money_orderget
     */
    public String getMoneyOrderget() {
        return moneyOrderget;
    }

    /**
     * @param moneyOrderget
     */
    public void setMoneyOrderget(String moneyOrderget) {
        this.moneyOrderget = moneyOrderget;
    }

    /**
     * @return money_tax
     */
    public String getMoneyTax() {
        return moneyTax;
    }

    /**
     * @param moneyTax
     */
    public void setMoneyTax(String moneyTax) {
        this.moneyTax = moneyTax;
    }

    /**
     * @return longterm_loanyear
     */
    public String getLongtermLoanyear() {
        return longtermLoanyear;
    }

    /**
     * @param longtermLoanyear
     */
    public void setLongtermLoanyear(String longtermLoanyear) {
        this.longtermLoanyear = longtermLoanyear;
    }

    /**
     * @return money_loan
     */
    public String getMoneyLoan() {
        return moneyLoan;
    }

    /**
     * @param moneyLoan
     */
    public void setMoneyLoan(String moneyLoan) {
        this.moneyLoan = moneyLoan;
    }

    /**
     * @return money_j
     */
    public String getMoneyJ() {
        return moneyJ;
    }

    /**
     * @param moneyJ
     */
    public void setMoneyJ(String moneyJ) {
        this.moneyJ = moneyJ;
    }

    /**
     * @return money_p
     */
    public String getMoneyP() {
        return moneyP;
    }

    /**
     * @param moneyP
     */
    public void setMoneyP(String moneyP) {
        this.moneyP = moneyP;
    }

    /**
     * @return money_m
     */
    public String getMoneyM() {
        return moneyM;
    }

    /**
     * @param moneyM
     */
    public void setMoneyM(String moneyM) {
        this.moneyM = moneyM;
    }

    /**
     * @return money_static
     */
    public String getMoneyStatic() {
        return moneyStatic;
    }

    /**
     * @param moneyStatic
     */
    public void setMoneyStatic(String moneyStatic) {
        this.moneyStatic = moneyStatic;
    }

    /**
     * @return money_g
     */
    public String getMoneyG() {
        return moneyG;
    }

    /**
     * @param moneyG
     */
    public void setMoneyG(String moneyG) {
        this.moneyG = moneyG;
    }

    /**
     * @return money_stay
     */
    public String getMoneyStay() {
        return moneyStay;
    }

    /**
     * @param moneyStay
     */
    public void setMoneyStay(String moneyStay) {
        this.moneyStay = moneyStay;
    }

    /**
     * @return money_year
     */
    public String getMoneyYear() {
        return moneyYear;
    }

    /**
     * @param moneyYear
     */
    public void setMoneyYear(String moneyYear) {
        this.moneyYear = moneyYear;
    }

    /**
     * @return money_user
     */
    public String getMoneyUser() {
        return moneyUser;
    }

    /**
     * @param moneyUser
     */
    public void setMoneyUser(String moneyUser) {
        this.moneyUser = moneyUser;
    }

    /**
     * @return money_all
     */
    public String getMoneyAll() {
        return moneyAll;
    }

    /**
     * @param moneyAll
     */
    public void setMoneyAll(String moneyAll) {
        this.moneyAll = moneyAll;
    }

    /**
     * @return money_alls
     */
    public String getMoneyAlls() {
        return moneyAlls;
    }

    /**
     * @param moneyAlls
     */
    public void setMoneyAlls(String moneyAlls) {
        this.moneyAlls = moneyAlls;
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