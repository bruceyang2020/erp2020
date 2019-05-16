package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;

import javax.persistence.*;
import java.util.Date;

@Table(name = "module_order")
public class ModuleOrder extends BaseBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "team_count")
    private String teamCount;

    private Integer advertises;

    @Column(name = "group_order")
    private Integer groupOrder;

    @Column(name = "sale_order")
    private Integer saleOrder;

    @Column(name = "short_term")
    private Integer shortTerm;

    private Integer usury;

    @Column(name = "raw_materials_order")
    private Integer rawMaterialsOrder;

    private Integer produtline;

    @Column(name = "account_pay")
    private Integer accountPay;

    @Column(name = "account_receive")
    private Integer accountReceive;

    @Column(name = "product_order")
    private Integer productOrder;

    @Column(name = "product_development")
    private Integer productDevelopment;

    @Column(name = "other_fees")
    private Integer otherFees;

    @Column(name = "iso_certification")
    private Integer isoCertification;

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
     * @return team_count
     */
    public String getTeamCount() {
        return teamCount;
    }

    /**
     * @param teamCount
     */
    public void setTeamCount(String teamCount) {
        this.teamCount = teamCount;
    }

    /**
     * @return advertises
     */
    public Integer getAdvertises() {
        return advertises;
    }

    /**
     * @param advertises
     */
    public void setAdvertises(Integer advertises) {
        this.advertises = advertises;
    }

    /**
     * @return group_order
     */
    public Integer getGroupOrder() {
        return groupOrder;
    }

    /**
     * @param groupOrder
     */
    public void setGroupOrder(Integer groupOrder) {
        this.groupOrder = groupOrder;
    }

    /**
     * @return sale_order
     */
    public Integer getSaleOrder() {
        return saleOrder;
    }

    /**
     * @param saleOrder
     */
    public void setSaleOrder(Integer saleOrder) {
        this.saleOrder = saleOrder;
    }

    /**
     * @return short_term
     */
    public Integer getShortTerm() {
        return shortTerm;
    }

    /**
     * @param shortTerm
     */
    public void setShortTerm(Integer shortTerm) {
        this.shortTerm = shortTerm;
    }

    /**
     * @return usury
     */
    public Integer getUsury() {
        return usury;
    }

    /**
     * @param usury
     */
    public void setUsury(Integer usury) {
        this.usury = usury;
    }

    /**
     * @return raw_materials_order
     */
    public Integer getRawMaterialsOrder() {
        return rawMaterialsOrder;
    }

    /**
     * @param rawMaterialsOrder
     */
    public void setRawMaterialsOrder(Integer rawMaterialsOrder) {
        this.rawMaterialsOrder = rawMaterialsOrder;
    }

    /**
     * @return produtline
     */
    public Integer getProdutline() {
        return produtline;
    }

    /**
     * @param produtline
     */
    public void setProdutline(Integer produtline) {
        this.produtline = produtline;
    }

    /**
     * @return account_pay
     */
    public Integer getAccountPay() {
        return accountPay;
    }

    /**
     * @param accountPay
     */
    public void setAccountPay(Integer accountPay) {
        this.accountPay = accountPay;
    }

    /**
     * @return account_receive
     */
    public Integer getAccountReceive() {
        return accountReceive;
    }

    /**
     * @param accountReceive
     */
    public void setAccountReceive(Integer accountReceive) {
        this.accountReceive = accountReceive;
    }

    /**
     * @return product_order
     */
    public Integer getProductOrder() {
        return productOrder;
    }

    /**
     * @param productOrder
     */
    public void setProductOrder(Integer productOrder) {
        this.productOrder = productOrder;
    }

    /**
     * @return product_development
     */
    public Integer getProductDevelopment() {
        return productDevelopment;
    }

    /**
     * @param productDevelopment
     */
    public void setProductDevelopment(Integer productDevelopment) {
        this.productDevelopment = productDevelopment;
    }

    /**
     * @return other_fees
     */
    public Integer getOtherFees() {
        return otherFees;
    }

    /**
     * @param otherFees
     */
    public void setOtherFees(Integer otherFees) {
        this.otherFees = otherFees;
    }

    /**
     * @return iso_certification
     */
    public Integer getIsoCertification() {
        return isoCertification;
    }

    /**
     * @param isoCertification
     */
    public void setIsoCertification(Integer isoCertification) {
        this.isoCertification = isoCertification;
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