package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "production")
public class Production extends BaseBean {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 产品编号
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 研发单位成本
     */
    @Column(name = "research_cost")
    private Integer researchCost;

    /**
     * 研发期数
     */
    @Column(name = "research_period")
    private Integer researchPeriod;

    /**
     * 研发总投入
     */
    @Column(name = "cost_total_research")
    private BigDecimal costTotalResearch;

    /**
     * 生产人工
     */
    @Column(name = "cost_labor")
    private BigDecimal costLabor;

    /**
     * 材料成本
     */
    @Column(name = "cost_material")
    private BigDecimal costMaterial;

    /**
     * 总成本
     */
    @Column(name = "cost_total")
    private BigDecimal costTotal;

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
     * 获取产品编号
     *
     * @return product_id - 产品编号
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置产品编号
     *
     * @param productId 产品编号
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取研发单位成本
     *
     * @return research_cost - 研发单位成本
     */
    public Integer getResearchCost() {
        return researchCost;
    }

    /**
     * 设置研发单位成本
     *
     * @param researchCost 研发单位成本
     */
    public void setResearchCost(Integer researchCost) {
        this.researchCost = researchCost;
    }

    /**
     * 获取研发期数
     *
     * @return research_period - 研发期数
     */
    public Integer getResearchPeriod() {
        return researchPeriod;
    }

    /**
     * 设置研发期数
     *
     * @param researchPeriod 研发期数
     */
    public void setResearchPeriod(Integer researchPeriod) {
        this.researchPeriod = researchPeriod;
    }

    /**
     * 获取研发总投入
     *
     * @return cost_total_research - 研发总投入
     */
    public BigDecimal getCostTotalResearch() {
        return costTotalResearch;
    }

    /**
     * 设置研发总投入
     *
     * @param costTotalResearch 研发总投入
     */
    public void setCostTotalResearch(BigDecimal costTotalResearch) {
        this.costTotalResearch = costTotalResearch;
    }

    /**
     * 获取生产人工
     *
     * @return cost_labor - 生产人工
     */
    public BigDecimal getCostLabor() {
        return costLabor;
    }

    /**
     * 设置生产人工
     *
     * @param costLabor 生产人工
     */
    public void setCostLabor(BigDecimal costLabor) {
        this.costLabor = costLabor;
    }

    /**
     * 获取材料成本
     *
     * @return cost_material - 材料成本
     */
    public BigDecimal getCostMaterial() {
        return costMaterial;
    }

    /**
     * 设置材料成本
     *
     * @param costMaterial 材料成本
     */
    public void setCostMaterial(BigDecimal costMaterial) {
        this.costMaterial = costMaterial;
    }

    /**
     * 获取总成本
     *
     * @return cost_total - 总成本
     */
    public BigDecimal getCostTotal() {
        return costTotal;
    }

    /**
     * 设置总成本
     *
     * @param costTotal 总成本
     */
    public void setCostTotal(BigDecimal costTotal) {
        this.costTotal = costTotal;
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