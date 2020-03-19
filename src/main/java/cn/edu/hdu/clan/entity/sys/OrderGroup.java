package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "order_group")
public class OrderGroup extends BaseBean {

    /**
     * 群组编码
     */
    @Column(name = "group_id")
    private String groupId;

    /**
     * 订单编号
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 订单名称
     */
    @Column(name = "order_name")
    private String orderName;

    /**
     * 市场编码
     */
    @Column(name = "market_id")
    private String marketId;

    /**
     * 产品编码
     */
    @Column(name = "product_id")
    private String productId;

    /**
     * 会计期间
     */
    @Column(name = "period")
    private Integer period;

    @Column(name = "price_total")
    private BigDecimal priceTotal;

    private BigDecimal amount;

    /**
     * 应收周期
     */
    @Column(name = "period_pay")
    private Integer periodPay;

    private Integer isurgent;

    private Integer iso;

    private Integer isvalid;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "edit_user")
    private String editUser;

    @Column(name = "edit_time")
    private Date editTime;



    /**
     * 获取群组编码
     *
     * @return group-id - 群组编码
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置群组编码
     *
     * @param groupId 群组编码
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取订单编号
     *
     * @return order-id - 订单编号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单编号
     *
     * @param orderId 订单编号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取订单名称
     *
     * @return order_name - 订单名称
     */
    public String getOrderName() {
        return orderName;
    }

    /**
     * 设置订单名称
     *
     * @param orderName 订单名称
     */
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    /**
     * 获取市场编码
     *
     * @return market-id - 市场编码
     */
    public String getMarketId() {
        return marketId;
    }

    /**
     * 设置市场编码
     *
     * @param marketId 市场编码
     */
    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    /**
     * 获取产品编码
     *
     * @return product_id - 产品编码
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置产品编码
     *
     * @param productId 产品编码
     */
    public void setProductId(String productId) {
        this.productId = productId;
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
     * @return price_total
     */
    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    /**
     * @param priceTotal
     */
    public void setPriceTotal(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
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
     * 获取应收周期
     *
     * @return period_pay - 应收周期
     */
    public Integer getPeriodPay() {
        return periodPay;
    }

    /**
     * 设置应收周期
     *
     * @param periodPay 应收周期
     */
    public void setPeriodPay(Integer periodPay) {
        this.periodPay = periodPay;
    }

    /**
     * @return isurgent
     */
    public Integer getIsurgent() {
        return isurgent;
    }

    /**
     * @param isurgent
     */
    public void setIsurgent(Integer isurgent) {
        this.isurgent = isurgent;
    }

    /**
     * @return iso
     */
    public Integer getIso() {
        return iso;
    }

    /**
     * @param iso
     */
    public void setIso(Integer iso) {
        this.iso = iso;
    }

    /**
     * @return isvalid
     */
    public Integer getIsvalid() {
        return isvalid;
    }

    /**
     * @param isvalid
     */
    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
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