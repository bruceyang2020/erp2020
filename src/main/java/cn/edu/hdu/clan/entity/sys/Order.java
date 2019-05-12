package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;

import javax.persistence.*;
import java.util.Date;

@Table(name = "order")
public class Order extends BaseBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "team_cout")
    private String teamCout;

    private Integer period;

    @Column(name = "market_id")
    private String marketId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "order_turn")
    private Integer orderTurn;

    private Integer number;

    private Integer state;

    @Column(name = "time_left")
    private Date timeLeft;

    @Column(name = "time_out")
    private Integer timeOut;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "eidt-user")
    private String eidtUser;

    @Column(name = "edit-time")
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
     * @return team_cout
     */
    public String getTeamCout() {
        return teamCout;
    }

    /**
     * @param teamCout
     */
    public void setTeamCout(String teamCout) {
        this.teamCout = teamCout;
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
     * @return market_id
     */
    public String getMarketId() {
        return marketId;
    }

    /**
     * @param marketId
     */
    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    /**
     * @return product_id
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return order_turn
     */
    public Integer getOrderTurn() {
        return orderTurn;
    }

    /**
     * @param orderTurn
     */
    public void setOrderTurn(Integer orderTurn) {
        this.orderTurn = orderTurn;
    }

    /**
     * @return number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * @param number
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * @return state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return time_left
     */
    public Date getTimeLeft() {
        return timeLeft;
    }

    /**
     * @param timeLeft
     */
    public void setTimeLeft(Date timeLeft) {
        this.timeLeft = timeLeft;
    }

    /**
     * @return time_out
     */
    public Integer getTimeOut() {
        return timeOut;
    }

    /**
     * @param timeOut
     */
    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
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
     * @return eidt-user
     */
    public String getEidtUser() {
        return eidtUser;
    }

    /**
     * @param eidtUser
     */
    public void setEidtUser(String eidtUser) {
        this.eidtUser = eidtUser;
    }

    /**
     * @return edit-time
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