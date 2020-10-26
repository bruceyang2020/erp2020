package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "sandtable_order")
public class SandtableOrder extends BaseBean {

    /**
     * 主键  不知道为什么提示两个id所以被我注释掉了
     */
//    @Id
//    private String id;


    /**
     * 群组编码
     */
    @Column(name = "group_id")
    private String groupId;


    /**
     * 用户名
     */
    @Column(name = "user_id")
    private String userId;

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
     * 期间
     */
    @Column(name = "period")
    private Integer period;


    /**
     * 数量
     */
    @Column(name = "amount")
    private Integer amount;

    /**
     * 总金额
     */
    @Column(name = "price_total")
    private Integer priceTotal;

    /**
     * ISO
     */
    @Column(name = "iso")
    private Integer iso;

    /**
     * 加急
     */
    @Column(name = "isurgent")
    private Integer isurgent;

    /**
     * 账期
     */
    @Column(name = "period_pay")
    private Integer periodPay;

    /**
     * 订单选取
     */
    @Column(name = "state")
    private Integer state;

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




//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getMarketId(){return marketId;}

    public void setMarketId(String marketId){this.marketId = marketId;}

    public String getProductId(){return productId;}

    public void setProductId(String productId){this.productId = productId;}

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(Integer priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Integer getPeriodPay() {
        return periodPay;
    }

    public void setPeriodPay(Integer periodPay) {
        this.periodPay = periodPay;
    }
    public Integer getIso() {
        return iso;
    }

    public void setIso(Integer iso) {
        this.iso = iso;
    }

    public Integer getIsurgent() {
        return isurgent;
    }

    public void setIsurgent(Integer isurgent) {
        this.isurgent = isurgent;
    }

    public Integer getState(){
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getEditUser() {
        return editUser;
    }

    @Override
    public void setEditUser(String editUser) {
        this.editUser = editUser;
    }

    @Override
    public Date getEditTime() {
        return editTime;
    }

    @Override
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}
