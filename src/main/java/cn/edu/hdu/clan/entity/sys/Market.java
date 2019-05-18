package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "market")
public class Market extends BaseBean {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 市场编号
     */
    @Column(name = "market_id")
    private Integer marketId;

    /**
     * 市场名称
     */
    private String name;

    /**
     * 单位投入
     */
    private BigDecimal cost;

    /**
     * 总开发期数
     */
    private Integer period;

    /**
     * 总投入
     */
    @Column(name = "cost_total")
    private BigDecimal costTotal;

    /**
     * 单项分值
     */
    private Integer points;

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
     * 获取市场编号
     *
     * @return market_id - 市场编号
     */
    public Integer getMarketId() {
        return marketId;
    }

    /**
     * 设置市场编号
     *
     * @param marketId 市场编号
     */
    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    /**
     * 获取市场名称
     *
     * @return name - 市场名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置市场名称
     *
     * @param name 市场名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取单位投入
     *
     * @return cost - 单位投入
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * 设置单位投入
     *
     * @param cost 单位投入
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * 获取总开发期数
     *
     * @return period - 总开发期数
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置总开发期数
     *
     * @param period 总开发期数
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * 获取总投入
     *
     * @return cost_total - 总投入
     */
    public BigDecimal getCostTotal() {
        return costTotal;
    }

    /**
     * 设置总投入
     *
     * @param costTotal 总投入
     */
    public void setCostTotal(BigDecimal costTotal) {
        this.costTotal = costTotal;
    }

    /**
     * 获取单项分值
     *
     * @return points - 单项分值
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * 设置单项分值
     *
     * @param points 单项分值
     */
    public void setPoints(Integer points) {
        this.points = points;
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