package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "producline_type")
public class ProduclineType extends BaseBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "productline_type_id")
    private String productlineTypeId;

    @Column(name = "productline_type_name")
    private String productlineTypeName;

    private BigDecimal price;

    @Column(name = "price-install")
    private Integer priceInstall;

    @Column(name = "period_install")
    private Integer periodInstall;

    @Column(name = "period_change")
    private Integer periodChange;

    @Column(name = "cost_change")
    private BigDecimal costChange;

    @Column(name = "cost_salvage")
    private BigDecimal costSalvage;

    @Column(name = "period_dep1")
    private BigDecimal periodDep1;

    @Column(name = "period_dep2")
    private BigDecimal periodDep2;

    @Column(name = "period_dep3")
    private Long periodDep3;

    @Column(name = "period_dep4")
    private Long periodDep4;

    @Column(name = "period_dep5")
    private Long periodDep5;

    @Column(name = "cost_check")
    private Long costCheck;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "edit_user")
    private String editUser;

    @Column(name = "edit_time")
    private Date editTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return productline_type_id
     */
    public String getProductlineTypeId() {
        return productlineTypeId;
    }

    /**
     * @param productlineTypeId
     */
    public void setProductlineTypeId(String productlineTypeId) {
        this.productlineTypeId = productlineTypeId;
    }

    /**
     * @return productline_type_name
     */
    public String getProductlineTypeName() {
        return productlineTypeName;
    }

    /**
     * @param productlineTypeName
     */
    public void setProductlineTypeName(String productlineTypeName) {
        this.productlineTypeName = productlineTypeName;
    }

    /**
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return price-install
     */
    public Integer getPriceInstall() {
        return priceInstall;
    }

    /**
     * @param priceInstall
     */
    public void setPriceInstall(Integer priceInstall) {
        this.priceInstall = priceInstall;
    }

    /**
     * @return period_install
     */
    public Integer getPeriodInstall() {
        return periodInstall;
    }

    /**
     * @param periodInstall
     */
    public void setPeriodInstall(Integer periodInstall) {
        this.periodInstall = periodInstall;
    }

    /**
     * @return period_change
     */
    public Integer getPeriodChange() {
        return periodChange;
    }

    /**
     * @param periodChange
     */
    public void setPeriodChange(Integer periodChange) {
        this.periodChange = periodChange;
    }

    /**
     * @return cost_change
     */
    public BigDecimal getCostChange() {
        return costChange;
    }

    /**
     * @param costChange
     */
    public void setCostChange(BigDecimal costChange) {
        this.costChange = costChange;
    }

    /**
     * @return cost_salvage
     */
    public BigDecimal getCostSalvage() {
        return costSalvage;
    }

    /**
     * @param costSalvage
     */
    public void setCostSalvage(BigDecimal costSalvage) {
        this.costSalvage = costSalvage;
    }

    /**
     * @return period_dep1
     */
    public BigDecimal getPeriodDep1() {
        return periodDep1;
    }

    /**
     * @param periodDep1
     */
    public void setPeriodDep1(BigDecimal periodDep1) {
        this.periodDep1 = periodDep1;
    }

    /**
     * @return period_dep2
     */
    public BigDecimal getPeriodDep2() {
        return periodDep2;
    }

    /**
     * @param periodDep2
     */
    public void setPeriodDep2(BigDecimal periodDep2) {
        this.periodDep2 = periodDep2;
    }

    /**
     * @return period_dep3
     */
    public Long getPeriodDep3() {
        return periodDep3;
    }

    /**
     * @param periodDep3
     */
    public void setPeriodDep3(Long periodDep3) {
        this.periodDep3 = periodDep3;
    }

    /**
     * @return period_dep4
     */
    public Long getPeriodDep4() {
        return periodDep4;
    }

    /**
     * @param periodDep4
     */
    public void setPeriodDep4(Long periodDep4) {
        this.periodDep4 = periodDep4;
    }

    /**
     * @return period_dep5
     */
    public Long getPeriodDep5() {
        return periodDep5;
    }

    /**
     * @param periodDep5
     */
    public void setPeriodDep5(Long periodDep5) {
        this.periodDep5 = periodDep5;
    }

    /**
     * @return cost_check
     */
    public Long getCostCheck() {
        return costCheck;
    }

    /**
     * @param costCheck
     */
    public void setCostCheck(Long costCheck) {
        this.costCheck = costCheck;
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