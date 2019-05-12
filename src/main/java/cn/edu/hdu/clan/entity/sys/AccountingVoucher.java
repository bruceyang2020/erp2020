package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "accounting_voucher")
public class AccountingVoucher extends BaseBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String substract;

    private Integer period;

    private Integer vnumber;

    private String acode;

    @Column(name = "money_d")
    private BigDecimal moneyD;

    @Column(name = "money_c")
    private BigDecimal moneyC;

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "team_count")
    private String teamCount;

    private String aname;

    private BigDecimal amount;

    @Column(name = "material_id")
    private String materialId;

    @Column(name = "morder_id")
    private String morderId;

    @Column(name = "porder_id")
    private String porderId;

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
     * @return substract
     */
    public String getSubstract() {
        return substract;
    }

    /**
     * @param substract
     */
    public void setSubstract(String substract) {
        this.substract = substract;
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
     * @return vnumber
     */
    public Integer getVnumber() {
        return vnumber;
    }

    /**
     * @param vnumber
     */
    public void setVnumber(Integer vnumber) {
        this.vnumber = vnumber;
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
     * @return amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return material_id
     */
    public String getMaterialId() {
        return materialId;
    }

    /**
     * @param materialId
     */
    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    /**
     * @return morder_id
     */
    public String getMorderId() {
        return morderId;
    }

    /**
     * @param morderId
     */
    public void setMorderId(String morderId) {
        this.morderId = morderId;
    }

    /**
     * @return porder_id
     */
    public String getPorderId() {
        return porderId;
    }

    /**
     * @param porderId
     */
    public void setPorderId(String porderId) {
        this.porderId = porderId;
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