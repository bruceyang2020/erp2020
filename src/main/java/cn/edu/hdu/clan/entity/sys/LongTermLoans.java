package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "long_term_loans")
public class LongTermLoans extends BaseBean {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "team_count")
    private Integer teamCount;

    /**
     * 长期贷款编码，计算机自动生成
     */
    private Integer number;

    /**
     * 摘要
     */
    private String name;

    /**
     * 利率
     */
    private BigDecimal rate;

    /**
     * 产生年度1.5.9.13.17.21
     */
    @Column(name = "period-loan")
    private Integer periodLoan;

    /**
     * 贷款金额
     */
    @Column(name = "money_total")
    private BigDecimal moneyTotal;

    /**
     * 当前会计期间1到24
     */
    private Integer period;

    /**
     * 长期贷款到期年限1.5.9.13.17.21
     */
    @Column(name = "return_time")
    private Integer returnTime;

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
     * 获取长期贷款编码，计算机自动生成
     *
     * @return number - 长期贷款编码，计算机自动生成
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置长期贷款编码，计算机自动生成
     *
     * @param number 长期贷款编码，计算机自动生成
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取摘要
     *
     * @return name - 摘要
     */
    public String getName() {
        return name;
    }

    /**
     * 设置摘要
     *
     * @param name 摘要
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
     * 获取产生年度1.5.9.13.17.21
     *
     * @return period-loan - 产生年度1.5.9.13.17.21
     */
    public Integer getPeriodLoan() {
        return periodLoan;
    }

    /**
     * 设置产生年度1.5.9.13.17.21
     *
     * @param periodLoan 产生年度1.5.9.13.17.21
     */
    public void setPeriodLoan(Integer periodLoan) {
        this.periodLoan = periodLoan;
    }

    /**
     * 获取贷款金额
     *
     * @return money_total - 贷款金额
     */
    public BigDecimal getMoneyTotal() {
        return moneyTotal;
    }

    /**
     * 设置贷款金额
     *
     * @param moneyTotal 贷款金额
     */
    public void setMoneyTotal(BigDecimal moneyTotal) {
        this.moneyTotal = moneyTotal;
    }

    /**
     * 获取当前会计期间1到24
     *
     * @return period - 当前会计期间1到24
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置当前会计期间1到24
     *
     * @param period 当前会计期间1到24
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * 获取长期贷款到期年限1.5.9.13.17.21
     *
     * @return return_time - 长期贷款到期年限1.5.9.13.17.21
     */
    public Integer getReturnTime() {
        return returnTime;
    }

    /**
     * 设置长期贷款到期年限1.5.9.13.17.21
     *
     * @param returnTime 长期贷款到期年限1.5.9.13.17.21
     */
    public void setReturnTime(Integer returnTime) {
        this.returnTime = returnTime;
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