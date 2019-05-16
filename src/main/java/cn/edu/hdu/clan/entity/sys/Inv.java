package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "inv")
public class Inv extends BaseBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "team_count")
    private String teamCount;

    private Integer period;

    private String number;

    @Column(name = "amount_b")
    private BigDecimal amountB;

    @Column(name = "money_b")
    private BigDecimal moneyB;

    @Column(name = "amount_l")
    private BigDecimal amountL;

    @Column(name = "money_l")
    private BigDecimal moneyL;

    @Column(name = "amount_o")
    private BigDecimal amountO;

    @Column(name = "money_o")
    private BigDecimal moneyO;

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
     * @return number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return amount_b
     */
    public BigDecimal getAmountB() {
        return amountB;
    }

    /**
     * @param amountB
     */
    public void setAmountB(BigDecimal amountB) {
        this.amountB = amountB;
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
     * @return amount_l
     */
    public BigDecimal getAmountL() {
        return amountL;
    }

    /**
     * @param amountL
     */
    public void setAmountL(BigDecimal amountL) {
        this.amountL = amountL;
    }

    /**
     * @return money_l
     */
    public BigDecimal getMoneyL() {
        return moneyL;
    }

    /**
     * @param moneyL
     */
    public void setMoneyL(BigDecimal moneyL) {
        this.moneyL = moneyL;
    }

    /**
     * @return amount_o
     */
    public BigDecimal getAmountO() {
        return amountO;
    }

    /**
     * @param amountO
     */
    public void setAmountO(BigDecimal amountO) {
        this.amountO = amountO;
    }

    /**
     * @return money_o
     */
    public BigDecimal getMoneyO() {
        return moneyO;
    }

    /**
     * @param moneyO
     */
    public void setMoneyO(BigDecimal moneyO) {
        this.moneyO = moneyO;
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