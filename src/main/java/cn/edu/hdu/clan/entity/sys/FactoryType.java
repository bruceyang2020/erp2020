package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "factory_type")
public class FactoryType extends BaseBean {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 厂房编号
     */
    private Integer number;

    /**
     * 厂房名称
     */
    private String name;

    /**
     * 购买价格
     */
    private BigDecimal price;

    /**
     * 每年租金
     */
    @Column(name = "cost_rent")
    private Long costRent;

    /**
     * 容量
     */
    private Integer capacity;

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
     * 获取厂房编号
     *
     * @return number - 厂房编号
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置厂房编号
     *
     * @param number 厂房编号
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取厂房名称
     *
     * @return name - 厂房名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置厂房名称
     *
     * @param name 厂房名称
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取每年租金
     *
     * @return cost_rent - 每年租金
     */
    public Long getCostRent() {
        return costRent;
    }

    /**
     * 设置每年租金
     *
     * @param costRent 每年租金
     */
    public void setCostRent(Long costRent) {
        this.costRent = costRent;
    }

    /**
     * 获取容量
     *
     * @return capacity - 容量
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * 设置容量
     *
     * @param capacity 容量
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
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