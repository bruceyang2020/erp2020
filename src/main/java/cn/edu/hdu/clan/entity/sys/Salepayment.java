package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "salepayment")
public class Salepayment extends BaseBean {
    /**
     * 主键
     */
    @Id
    private String id;

    @Column(name = "group-id")
    private Integer groupId;

    @Column(name = "team_count")
    private Integer teamCount;

    /**
     * 应收账款编码
     */
    private Integer number;

    /**
     * 应收账款生成的会计期间
     */
    private Integer period;

    /**
     * 剩余还款期
     */
    @Column(name = "surplus_period")
    private Integer surplusPeriod;

    /**
     * 订单编码
     */
    @Column(name = "sale_order_id")
    private Integer saleOrderId;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 累计账期
     */
    @Column(name = "pay_period")
    private Integer payPeriod;

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
    @Column(name = "eidt_time")
    private Date eidtTime;

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
     * @return group-id
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * @param groupId
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * @return team_count
     */
    public Integer getTeamCount() {
        return teamCount;
    }

    /**
     * @param teamCount
     */
    public void setTeamCount(Integer teamCount) {
        this.teamCount = teamCount;
    }

    /**
     * 获取应收账款编码
     *
     * @return number - 应收账款编码
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置应收账款编码
     *
     * @param number 应收账款编码
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取应收账款生成的会计期间
     *
     * @return period - 应收账款生成的会计期间
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置应收账款生成的会计期间
     *
     * @param period 应收账款生成的会计期间
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * 获取剩余还款期
     *
     * @return surplus_period - 剩余还款期
     */
    public Integer getSurplusPeriod() {
        return surplusPeriod;
    }

    /**
     * 设置剩余还款期
     *
     * @param surplusPeriod 剩余还款期
     */
    public void setSurplusPeriod(Integer surplusPeriod) {
        this.surplusPeriod = surplusPeriod;
    }

    /**
     * 获取订单编码
     *
     * @return sale_order_id - 订单编码
     */
    public Integer getSaleOrderId() {
        return saleOrderId;
    }

    /**
     * 设置订单编码
     *
     * @param saleOrderId 订单编码
     */
    public void setSaleOrderId(Integer saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    /**
     * 获取金额
     *
     * @return money - 金额
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置金额
     *
     * @param money 金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 获取累计账期
     *
     * @return pay_period - 累计账期
     */
    public Integer getPayPeriod() {
        return payPeriod;
    }

    /**
     * 设置累计账期
     *
     * @param payPeriod 累计账期
     */
    public void setPayPeriod(Integer payPeriod) {
        this.payPeriod = payPeriod;
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
     * @return eidt_time - 编辑时间
     */
    public Date getEidtTime() {
        return eidtTime;
    }

    /**
     * 设置编辑时间
     *
     * @param eidtTime 编辑时间
     */
    public void setEidtTime(Date eidtTime) {
        this.eidtTime = eidtTime;
    }
}