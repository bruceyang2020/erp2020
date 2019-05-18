package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "market_order")
public class MarketOrder extends BaseBean {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 订单编号
     */
    private Integer number;

    /**
     * 订单名称
     */
    private String name;

    /**
     * 市场编码
     */
    @Column(name = "market_id")
    private Integer marketId;

    /**
     * 产品编码
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 期数
     */
    private Integer period;

    /**
     * 总价
     */
    @Column(name = "price_total")
    private BigDecimal priceTotal;

    /**
     * 数量
     */
    private BigDecimal amount;

    /**
     * 应收周期
     */
    @Column(name = "period_pay")
    private Integer periodPay;

    /**
     * 是否加急
     */
    @Column(name = "is_urgent")
    private Integer isUrgent;

    /**
     * 质量要求
     */
    private Integer iso;

    /**
     * 是否可选
     */
    @Column(name = "is_valid")
    private Integer isValid;

    /**
     * 创建人，外键用户表
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
    @Column(name = "edit_timei")
    private Date editTimei;

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
     * 获取订单编号
     *
     * @return number - 订单编号
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置订单编号
     *
     * @param number 订单编号
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取订单名称
     *
     * @return name - 订单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置订单名称
     *
     * @param name 订单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取市场编码
     *
     * @return market_id - 市场编码
     */
    public Integer getMarketId() {
        return marketId;
    }

    /**
     * 设置市场编码
     *
     * @param marketId 市场编码
     */
    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    /**
     * 获取产品编码
     *
     * @return product_id - 产品编码
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置产品编码
     *
     * @param productId 产品编码
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取期数
     *
     * @return period - 期数
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置期数
     *
     * @param period 期数
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * 获取总价
     *
     * @return price_total - 总价
     */
    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    /**
     * 设置总价
     *
     * @param priceTotal 总价
     */
    public void setPriceTotal(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
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
     * 获取是否加急
     *
     * @return is_urgent - 是否加急
     */
    public Integer getIsUrgent() {
        return isUrgent;
    }

    /**
     * 设置是否加急
     *
     * @param isUrgent 是否加急
     */
    public void setIsUrgent(Integer isUrgent) {
        this.isUrgent = isUrgent;
    }

    /**
     * 获取质量要求
     *
     * @return iso - 质量要求
     */
    public Integer getIso() {
        return iso;
    }

    /**
     * 设置质量要求
     *
     * @param iso 质量要求
     */
    public void setIso(Integer iso) {
        this.iso = iso;
    }

    /**
     * 获取是否可选
     *
     * @return is_valid - 是否可选
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * 设置是否可选
     *
     * @param isValid 是否可选
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    /**
     * 获取创建人，外键用户表
     *
     * @return create_user - 创建人，外键用户表
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人，外键用户表
     *
     * @param createUser 创建人，外键用户表
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
     * @return edit_timei - 编辑时间
     */
    public Date getEditTimei() {
        return editTimei;
    }

    /**
     * 设置编辑时间
     *
     * @param editTimei 编辑时间
     */
    public void setEditTimei(Date editTimei) {
        this.editTimei = editTimei;
    }
}