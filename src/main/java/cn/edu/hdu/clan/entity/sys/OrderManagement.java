package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "order_management")
public class OrderManagement extends BaseBean {


    @Column(name = "group_id")
    private String groupId;

    @Column(name = "team_id")
    private String teamId;

    @Column(name = "order_id")
    private String orderId;

    /**
     * 当前会计期间
     */
    private Integer period;

    /**
     * 所需产品编号
     */
    @Column(name = "product_id")
    private String productId;


    /**
     * 所需产品对应市场编码号
     */
    @Column(name = "market_id")
    private String marketId;

    /**
     * 数据
     */
    @Column(name = "amount")
    private BigDecimal amount;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 订单类型
     */
    private Integer type;

    /**
     * 订单状态
     */
    @Column(name = "state")
    private Integer state;

    /**
     * 账期
     */
    @Column(name = "period_pay")
    private Integer periodPay;

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
     * @return team_id
     */
    public String getTeamId() {
        return teamId;
    }

    /**
     * @param teamId
     */
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    /**
     * @return order_id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取当前会计期间
     *
     * @return period - 当前会计期间
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置当前会计期间
     *
     * @param period 当前会计期间
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * 获取所需产品编号
     *
     * @return product_id - 所需产品编号
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置所需产品编号
     *
     * @param productId 所需产品编号
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }


    /**
     * 获取所需产品编号
     *
     * @return marketId - 所需产品编号
     */
    public String getMarketId() {
        return marketId;
    }

    /**
     * 设置所需产品编号
     *
     * @param marketId 所需产品编号
     */
    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    /**
     * 获取数据
     *
     * @return amount - 数据
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置数据
     *
     * @param amount 数据
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
     * 获取订单类型
     *
     * @return type - 订单类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置订单类型
     *
     * @param type 订单类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取订单状态
     *
     * @return state - 订单状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置订单状态
     *
     * @param state 订单状态
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取账期
     *
     * @return period_pay - 账期
     */
    public Integer getPeriodPay() {
        return periodPay;
    }

    /**
     * 设置账期
     *
     * @param periodPay 账期
     */
    public void setPeriodPay(Integer periodPay) {
        this.periodPay = periodPay;
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