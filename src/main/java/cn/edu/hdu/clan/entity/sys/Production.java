package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "production")
public class Production extends BaseBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "research_cost")
    private String researchCost;

    @Column(name = "research_period")
    private BigDecimal researchPeriod;

    @Column(name = "cost_total_research")
    private BigDecimal costTotalResearch;

    @Column(name = "cost_labor")
    private BigDecimal costLabor;

    @Column(name = "cost_material")
    private BigDecimal costMaterial;

    @Column(name = "cost_total")
    private BigDecimal costTotal;

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
    public String getId() {
        return String.valueOf(id);
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return research_cost
     */
    public String getResearchCost() {
        return researchCost;
    }

    /**
     * @param researchCost
     */
    public void setResearchCost(String researchCost) {
        this.researchCost = researchCost;
    }

    /**
     * @return research_period
     */
    public BigDecimal getResearchPeriod() {
        return researchPeriod;
    }

    /**
     * @param researchPeriod
     */
    public void setResearchPeriod(BigDecimal researchPeriod) {
        this.researchPeriod = researchPeriod;
    }

    /**
     * @return cost_total_research
     */
    public BigDecimal getCostTotalResearch() {
        return costTotalResearch;
    }

    /**
     * @param costTotalResearch
     */
    public void setCostTotalResearch(BigDecimal costTotalResearch) {
        this.costTotalResearch = costTotalResearch;
    }

    /**
     * @return cost_labor
     */
    public BigDecimal getCostLabor() {
        return costLabor;
    }

    /**
     * @param costLabor
     */
    public void setCostLabor(BigDecimal costLabor) {
        this.costLabor = costLabor;
    }

    /**
     * @return cost_material
     */
    public BigDecimal getCostMaterial() {
        return costMaterial;
    }

    /**
     * @param costMaterial
     */
    public void setCostMaterial(BigDecimal costMaterial) {
        this.costMaterial = costMaterial;
    }

    /**
     * @return cost_total
     */
    public BigDecimal getCostTotal() {
        return costTotal;
    }

    /**
     * @param costTotal
     */
    public void setCostTotal(BigDecimal costTotal) {
        this.costTotal = costTotal;
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