package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "salepayment")
public class Salepayment extends BaseBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "group-id")
    private String groupId;

    @Column(name = "team_count")
    private String teamCount;

    private String number;

    private Integer period;

    @Column(name = "surplus_period")
    private Integer surplusPeriod;

    @Column(name = "sale_order_id")
    private String saleOrderId;

    private BigDecimal money;

    @Column(name = "pay_period")
    private Integer payPeriod;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "edit_user")
    private String editUser;

    @Column(name = "eidt_time")
    private Date eidtTime;

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
     * @return group-id
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
     * @return surplus_period
     */
    public Integer getSurplusPeriod() {
        return surplusPeriod;
    }

    /**
     * @param surplusPeriod
     */
    public void setSurplusPeriod(Integer surplusPeriod) {
        this.surplusPeriod = surplusPeriod;
    }

    /**
     * @return sale_order_id
     */
    public String getSaleOrderId() {
        return saleOrderId;
    }

    /**
     * @param saleOrderId
     */
    public void setSaleOrderId(String saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    /**
     * @return money
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * @param money
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * @return pay_period
     */
    public Integer getPayPeriod() {
        return payPeriod;
    }

    /**
     * @param payPeriod
     */
    public void setPayPeriod(Integer payPeriod) {
        this.payPeriod = payPeriod;
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
     * @return eidt_time
     */
    public Date getEidtTime() {
        return eidtTime;
    }

    /**
     * @param eidtTime
     */
    public void setEidtTime(Date eidtTime) {
        this.eidtTime = eidtTime;
    }
}