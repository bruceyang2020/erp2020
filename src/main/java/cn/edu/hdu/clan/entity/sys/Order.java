package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.util.Date;
import javax.persistence.*;

@Table(name = "order")
public class Order extends BaseBean {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "team_count")
    private Integer teamCount;

    /**
     * 当前会计期间
     */
    private Integer period;

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
     * 订单名称
     */
    @Column(name = "order_turn")
    private String orderTurn;

    /**
     * 选择次数
     */
    private Integer number;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 剩余可选时间
     */
    @Column(name = "time_left")
    private Date timeLeft;

    /**
     * 可超时次数
     */
    @Column(name = "time_out")
    private Integer timeOut;

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
    @Column(name = "eidt-user")
    private String eidtUser;

    /**
     * 编辑时间
     */
    @Column(name = "edit-time")
    private Date editTime;

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
     * @return group_id
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
     * 获取订单名称
     *
     * @return order_turn - 订单名称
     */
    public String getOrderTurn() {
        return orderTurn;
    }

    /**
     * 设置订单名称
     *
     * @param orderTurn 订单名称
     */
    public void setOrderTurn(String orderTurn) {
        this.orderTurn = orderTurn;
    }

    /**
     * 获取选择次数
     *
     * @return number - 选择次数
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置选择次数
     *
     * @param number 选择次数
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取状态
     *
     * @return state - 状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态
     *
     * @param state 状态
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取剩余可选时间
     *
     * @return time_left - 剩余可选时间
     */
    public Date getTimeLeft() {
        return timeLeft;
    }

    /**
     * 设置剩余可选时间
     *
     * @param timeLeft 剩余可选时间
     */
    public void setTimeLeft(Date timeLeft) {
        this.timeLeft = timeLeft;
    }

    /**
     * 获取可超时次数
     *
     * @return time_out - 可超时次数
     */
    public Integer getTimeOut() {
        return timeOut;
    }

    /**
     * 设置可超时次数
     *
     * @param timeOut 可超时次数
     */
    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
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
     * @return eidt-user - 编辑人
     */
    public String getEidtUser() {
        return eidtUser;
    }

    /**
     * 设置编辑人
     *
     * @param eidtUser 编辑人
     */
    public void setEidtUser(String eidtUser) {
        this.eidtUser = eidtUser;
    }

    /**
     * 获取编辑时间
     *
     * @return edit-time - 编辑时间
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