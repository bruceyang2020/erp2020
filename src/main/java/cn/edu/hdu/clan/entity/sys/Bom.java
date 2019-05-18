package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.util.Date;
import javax.persistence.*;

@Table(name = "bom")
public class Bom extends BaseBean {
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
     * 产品编号
     */
    @Column(name = "product_name")
    private Integer productName;

    /**
     * 物料编号
     */
    @Column(name = "material_id")
    private Integer materialId;

    /**
     * 物料数量
     */
    private String amount;

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
     * 获取产品编号
     *
     * @return product_name - 产品编号
     */
    public Integer getProductName() {
        return productName;
    }

    /**
     * 设置产品编号
     *
     * @param productName 产品编号
     */
    public void setProductName(Integer productName) {
        this.productName = productName;
    }

    /**
     * 获取物料编号
     *
     * @return material_id - 物料编号
     */
    public Integer getMaterialId() {
        return materialId;
    }

    /**
     * 设置物料编号
     *
     * @param materialId 物料编号
     */
    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    /**
     * 获取物料数量
     *
     * @return amount - 物料数量
     */
    public String getAmount() {
        return amount;
    }

    /**
     * 设置物料数量
     *
     * @param amount 物料数量
     */
    public void setAmount(String amount) {
        this.amount = amount;
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