package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "account_balance")
public class AccountBalance extends BaseBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "team_count")
    private String teamCount;

    private String name;

    private String acode;

    private String aname;

    @Column(name = "money_b")
    private BigDecimal moneyB;

    @Column(name = "money_e")
    private BigDecimal moneyE;

    @Column(name = "money_d")
    private BigDecimal moneyD;

    @Column(name = "money_c")
    private BigDecimal moneyC;

    private Integer period;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return acode
     */
    public String getAcode() {
        return acode;
    }

    /**
     * @param acode
     */
    public void setAcode(String acode) {
        this.acode = acode;
    }

    /**
     * @return aname
     */
    public String getAname() {
        return aname;
    }

    /**
     * @param aname
     */
    public void setAname(String aname) {
        this.aname = aname;
    }

    /**
     * @return money_b
     */
    public BigDecimal getMoneyB() {
        return moneyB;
    }

    /**
     * @param moneyB
     */
    public void setMoneyB(BigDecimal moneyB) {
        this.moneyB = moneyB;
    }

    /**
     * @return money_e
     */
    public BigDecimal getMoneyE() {
        return moneyE;
    }

    /**
     * @param moneyE
     */
    public void setMoneyE(BigDecimal moneyE) {
        this.moneyE = moneyE;
    }

    /**
     * @return money_d
     */
    public BigDecimal getMoneyD() {
        return moneyD;
    }

    /**
     * @param moneyD
     */
    public void setMoneyD(BigDecimal moneyD) {
        this.moneyD = moneyD;
    }

    /**
     * @return money_c
     */
    public BigDecimal getMoneyC() {
        return moneyC;
    }

    /**
     * @param moneyC
     */
    public void setMoneyC(BigDecimal moneyC) {
        this.moneyC = moneyC;
    }

    /**
     * @return period
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * @param period
     */
    public void setPeriod(Integer period) {
        this.period = period;
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