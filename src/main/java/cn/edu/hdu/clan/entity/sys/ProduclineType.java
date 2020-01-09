package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "producline_type")
public class ProduclineType extends BaseBean {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 生产线类型编号
     */
    @Column(name = "productline_type_id")
    private Integer productlineTypeId;

    /**
     * 生产线类型名称
     */
    @Column(name = "productline_type_name")
    private Integer productlineTypeName;

    /**
     * 购买价格
     */
    private BigDecimal price;

    /**
     * 安装价格
     */
    @Column(name = "price-install")
    private Integer priceInstall;

    /**
     * 安装周期
     */
    @Column(name = "period_install")
    private Integer periodInstall;

    /**
     * 转产周期
     */
    @Column(name = "period_change")
    private Integer periodChange;

    /**
     * 转产成本
     */
    @Column(name = "cost_change")
    private BigDecimal costChange;

    /**
     * 残值
     */
    @Column(name = "cost_salvage")
    private BigDecimal costSalvage;

    /**
     * 折旧周期1
     */
    @Column(name = "period_dep1")
    private BigDecimal periodDep1;

    /**
     * 折旧周期2
     */
    @Column(name = "period_dep2")
    private BigDecimal periodDep2;

    /**
     * 折旧周期3
     */
    @Column(name = "period_dep3")
    private BigDecimal periodDep3;

    /**
     * 折旧周期4
     */
    @Column(name = "period_dep4")
    private Long periodDep4;

    /**
     * 折旧周期5
     */
    @Column(name = "period_dep5")
    private Long periodDep5;

    /**
     * 维修检查费
     */
    @Column(name = "cost_check")
    private Long costCheck;

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
     * 获取生产线类型编号
     *
     * @return productline_type_id - 生产线类型编号
     */
    public Integer getProductlineTypeId() {
        return productlineTypeId;
    }

    /**
     * 设置生产线类型编号
     *
     * @param productlineTypeId 生产线类型编号
     */
    public void setProductlineTypeId(Integer productlineTypeId) {
        this.productlineTypeId = productlineTypeId;
    }

    /**
     * 获取生产线类型名称
     *
     * @return productline_type_name - 生产线类型名称
     */
    public Integer getProductlineTypeName() {
        return productlineTypeName;
    }

    /**
     * 设置生产线类型名称
     *
     * @param productlineTypeName 生产线类型名称
     */
    public void setProductlineTypeName(Integer productlineTypeName) {
        this.productlineTypeName = productlineTypeName;
    }

    /**
     * 获取购买价格
     *
     * @return price - 购买价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置购买价格
     *
     * @param price 购买价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取安装价格
     *
     * @return price-install - 安装价格
     */
    public Integer getPriceInstall() {
        return priceInstall;
    }

    /**
     * 设置安装价格
     *
     * @param priceInstall 安装价格
     */
    public void setPriceInstall(Integer priceInstall) {
        this.priceInstall = priceInstall;
    }

    /**
     * 获取安装周期
     *
     * @return period_install - 安装周期
     */
    public Integer getPeriodInstall() {
        return periodInstall;
    }

    /**
     * 设置安装周期
     *
     * @param periodInstall 安装周期
     */
    public void setPeriodInstall(Integer periodInstall) {
        this.periodInstall = periodInstall;
    }

    /**
     * 获取转产周期
     *
     * @return period_change - 转产周期
     */
    public Integer getPeriodChange() {
        return periodChange;
    }

    /**
     * 设置转产周期
     *
     * @param periodChange 转产周期
     */
    public void setPeriodChange(Integer periodChange) {
        this.periodChange = periodChange;
    }

    /**
     * 获取转产成本
     *
     * @return cost_change - 转产成本
     */
    public BigDecimal getCostChange() {
        return costChange;
    }

    /**
     * 设置转产成本
     *
     * @param costChange 转产成本
     */
    public void setCostChange(BigDecimal costChange) {
        this.costChange = costChange;
    }

    /**
     * 获取残值
     *
     * @return cost_salvage - 残值
     */
    public BigDecimal getCostSalvage() {
        return costSalvage;
    }

    /**
     * 设置残值
     *
     * @param costSalvage 残值
     */
    public void setCostSalvage(BigDecimal costSalvage) {
        this.costSalvage = costSalvage;
    }

    /**
     * 获取折旧周期1
     *
     * @return period_dep1 - 折旧周期1
     */
    public BigDecimal getPeriodDep1() {
        return periodDep1;
    }

    /**
     * 设置折旧周期1
     *
     * @param periodDep1 折旧周期1
     */
    public void setPeriodDep1(BigDecimal periodDep1) {
        this.periodDep1 = periodDep1;
    }

    /**
     * 获取折旧周期2
     *
     * @return period_dep2 - 折旧周期2
     */
    public BigDecimal getPeriodDep2() {
        return periodDep2;
    }

    /**
     * 设置折旧周期2
     *
     * @param periodDep2 折旧周期2
     */
    public void setPeriodDep2(BigDecimal periodDep2) {
        this.periodDep2 = periodDep2;
    }

    /**
     * 获取折旧周期3
     *
     * @return period_dep3 - 折旧周期3
     */
    public BigDecimal getPeriodDep3() {
        return periodDep3;
    }

    /**
     * 设置折旧周期3
     *
     * @param periodDep3 折旧周期3
     */
    public void setPeriodDep3(BigDecimal periodDep3) {
        this.periodDep3 = periodDep3;
    }

    /**
     * 获取折旧周期4
     *
     * @return period_dep4 - 折旧周期4
     */
    public Long getPeriodDep4() {
        return periodDep4;
    }

    /**
     * 设置折旧周期4
     *
     * @param periodDep4 折旧周期4
     */
    public void setPeriodDep4(Long periodDep4) {
        this.periodDep4 = periodDep4;
    }

    /**
     * 获取折旧周期5
     *
     * @return period_dep5 - 折旧周期5
     */
    public Long getPeriodDep5() {
        return periodDep5;
    }

    /**
     * 设置折旧周期5
     *
     * @param periodDep5 折旧周期5
     */
    public void setPeriodDep5(Long periodDep5) {
        this.periodDep5 = periodDep5;
    }

    /**
     * 获取维修检查费
     *
     * @return cost_check - 维修检查费
     */
    public Long getCostCheck() {
        return costCheck;
    }

    /**
     * 设置维修检查费
     *
     * @param costCheck 维修检查费
     */
    public void setCostCheck(Long costCheck) {
        this.costCheck = costCheck;
    }

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
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