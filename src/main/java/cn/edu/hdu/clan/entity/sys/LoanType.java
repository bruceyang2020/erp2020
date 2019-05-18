package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "loan_type")
public class LoanType extends BaseBean {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 贷款类型编号
     */
    private Integer number;

    /**
     * 贷款类型名称
     */
    private String name;

    /**
     * 利率
     */
    private BigDecimal rate;

    /**
     * 最大期数
     */
    @Column(name = "period_max")
    private Integer periodMax;

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
     * 获取贷款类型编号
     *
     * @return number - 贷款类型编号
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置贷款类型编号
     *
     * @param number 贷款类型编号
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取贷款类型名称
     *
     * @return name - 贷款类型名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置贷款类型名称
     *
     * @param name 贷款类型名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取利率
     *
     * @return rate - 利率
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置利率
     *
     * @param rate 利率
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 获取最大期数
     *
     * @return period_max - 最大期数
     */
    public Integer getPeriodMax() {
        return periodMax;
    }

    /**
     * 设置最大期数
     *
     * @param periodMax 最大期数
     */
    public void setPeriodMax(Integer periodMax) {
        this.periodMax = periodMax;
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