package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "salepayment")
public class Salepayment extends BaseBean {


    @Column(name = "group_id")
    private String groupId;

    @Column(name = "team_count")
    private String teamCount;

    /**
     * 应收账款编码
     */
    private String number;

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
    private String saleOrderId;

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
     * 状态:0未结算  1已收款结算
     */
    @Column(name = "state")
    private Integer state;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "edit_user")
    private String editUser;

    @Column(name = "edit_time")
    private Date editTime;



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
     * 获取应收账款编码
     *
     * @return number - 应收账款编码
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置应收账款编码
     *
     * @param number 应收账款编码
     */
    public void setNumber(String number) {
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
    public String getSaleOrderId() {
        return saleOrderId;
    }

    /**
     * 设置订单编码
     *
     * @param saleOrderId 订单编码
     */
    public void setSaleOrderId(String saleOrderId) {
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
     * 获取状态 1入库0未入库
     *
     * @return state - 状态 1入库0未入库
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态 1入库0未入库
     *
     * @param state 状态 1入库0未入库
     */
    public void setState(Integer state) {
        this.state = state;
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