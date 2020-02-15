package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "accounting_voucher")
public class AccountingVoucher extends BaseBean {

    /**
     * 摘要
     */
    private String substract;

    /**
     * 时间
     */
    private Integer period;

    /**
     * 凭证号
     */
    private Integer vnumber;

    /**
     * 科目编号
     */
    private String acode;

    /**
     * 借方金额
     */
    @Column(name = "money_d")
    private BigDecimal moneyD;

    /**
     * 贷方金额
     */
    @Column(name = "money_c")
    private BigDecimal moneyC;

    /**
     * 群组编号
     */
    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "team_count")
    private String teamCount;

    /**
     * 科目名称
     */
    private String aname;

    /**
     * 数量
     */
    private BigDecimal amount;

    /**
     * 物料编码
     */
    @Column(name = "material_id")
    private Integer materialId;

    /**
     * 物料订单编码
     */
    @Column(name = "morder_id")
    private Integer morderId;

    /**
     * 产品订单编码
     */
    @Column(name = "porder_id")
    private Integer porderId;

    /**
     * 创建人
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
     * 获取摘要
     *
     * @return substract - 摘要
     */
    public String getSubstract() {
        return substract;
    }

    /**
     * 设置摘要
     *
     * @param substract 摘要
     */
    public void setSubstract(String substract) {
        this.substract = substract;
    }

    /**
     * 获取时间
     *
     * @return period - 时间
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置时间
     *
     * @param period 时间
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * 获取凭证号
     *
     * @return vnumber - 凭证号
     */
    public Integer getVnumber() {
        return vnumber;
    }

    /**
     * 设置凭证号
     *
     * @param vnumber 凭证号
     */
    public void setVnumber(Integer vnumber) {
        this.vnumber = vnumber;
    }

    /**
     * 获取科目编号
     *
     * @return acode - 科目编号
     */
    public String getAcode() {
        return acode;
    }

    /**
     * 设置科目编号
     *
     * @param acode 科目编号
     */
    public void setAcode(String acode) {
        this.acode = acode;
    }

    /**
     * 获取借方金额
     *
     * @return money_d - 借方金额
     */
    public BigDecimal getMoneyD() {
        return moneyD;
    }

    /**
     * 设置借方金额
     *
     * @param moneyD 借方金额
     */
    public void setMoneyD(BigDecimal moneyD) {
        this.moneyD = moneyD;
    }

    /**
     * 获取贷方金额
     *
     * @return money_c - 贷方金额
     */
    public BigDecimal getMoneyC() {
        return moneyC;
    }

    /**
     * 设置贷方金额
     *
     * @param moneyC 贷方金额
     */
    public void setMoneyC(BigDecimal moneyC) {
        this.moneyC = moneyC;
    }

    /**
     * 获取群组编号
     *
     * @return group_id - 群组编号
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 设置群组编号
     *
     * @param groupId 群组编号
     */
    public void setGroupId(Integer groupId) {
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
     * 获取科目名称
     *
     * @return aname - 科目名称
     */
    public String getAname() {
        return aname;
    }

    /**
     * 设置科目名称
     *
     * @param aname 科目名称
     */
    public void setAname(String aname) {
        this.aname = aname;
    }

    /**
     * 获取数量
     *
     * @return amount - 数量
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置数量
     *
     * @param amount 数量
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取物料编码
     *
     * @return material_id - 物料编码
     */
    public Integer getMaterialId() {
        return materialId;
    }

    /**
     * 设置物料编码
     *
     * @param materialId 物料编码
     */
    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    /**
     * 获取物料订单编码
     *
     * @return morder_id - 物料订单编码
     */
    public Integer getMorderId() {
        return morderId;
    }

    /**
     * 设置物料订单编码
     *
     * @param morderId 物料订单编码
     */
    public void setMorderId(Integer morderId) {
        this.morderId = morderId;
    }

    /**
     * 获取产品订单编码
     *
     * @return porder_id - 产品订单编码
     */
    public Integer getPorderId() {
        return porderId;
    }

    /**
     * 设置产品订单编码
     *
     * @param porderId 产品订单编码
     */
    public void setPorderId(Integer porderId) {
        this.porderId = porderId;
    }

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
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