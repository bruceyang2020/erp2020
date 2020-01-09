package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "material_order")
public class MaterialOrder extends BaseBean {
    /**
     * 主键
     */
    @Id
    private String id;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "team_count")
    private Integer teamCount;

    /**
     * 下订单期间
     */
    private Integer period;

    /**
     * 原材料编码
     */
    @Column(name = "material_id")
    private Integer materialId;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 总金额
     */
    @Column(name = "money_total")
    private BigDecimal moneyTotal;

    /**
     * 状态 1入库0未入库
     */
    private Integer state;

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
     * 获取下订单期间
     *
     * @return period - 下订单期间
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置下订单期间
     *
     * @param period 下订单期间
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * 获取原材料编码
     *
     * @return material_id - 原材料编码
     */
    public Integer getMaterialId() {
        return materialId;
    }

    /**
     * 设置原材料编码
     *
     * @param materialId 原材料编码
     */
    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    /**
     * 获取数量
     *
     * @return amount - 数量
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置数量
     *
     * @param amount 数量
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 获取总金额
     *
     * @return money_total - 总金额
     */
    public BigDecimal getMoneyTotal() {
        return moneyTotal;
    }

    /**
     * 设置总金额
     *
     * @param moneyTotal 总金额
     */
    public void setMoneyTotal(BigDecimal moneyTotal) {
        this.moneyTotal = moneyTotal;
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