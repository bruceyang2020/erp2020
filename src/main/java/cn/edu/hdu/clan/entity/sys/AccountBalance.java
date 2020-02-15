package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "account_balance")
public class AccountBalance extends BaseBean {


    @Column(name = "group_id")
    private String groupId;

    @Column(name = "team_count")
    private String teamCount;

    /**
     * 摘要
     */
    private String name;

    /**
     * 科目编号
     */
    private String acode;

    /**
     * 科目名称
     */
    private String aname;

    /**
     * 期初余额
     */
    @Column(name = "money_b")
    private BigDecimal moneyB;

    /**
     * 期末余额
     */
    @Column(name = "money_e")
    private BigDecimal moneyE;

    /**
     * 借方金额
     */
    @Column(name = "money_d")
    private BigDecimal moneyD;

    /**
     * 贷方金额
     */
    @Column(name = "money_c")
    private BigDecimal moneyC;

    /**
     * 当前会计期间
     */
    private Integer period;

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
     * 获取科目编号
     *
     * @return acode - 科目编号
     */
    public String getAcode() {
        return acode;
    }

    /**
     * 设置科目编号
     *
     * @param acode 科目编号
     */
    public void setAcode(String acode) {
        this.acode = acode;
    }

    /**
     * 获取科目名称
     *
     * @return aname - 科目名称
     */
    public String getAname() {
        return aname;
    }

    /**
     * 设置科目名称
     *
     * @param aname 科目名称
     */
    public void setAname(String aname) {
        this.aname = aname;
    }

    /**
     * 获取期初余额
     *
     * @return money_b - 期初余额
     */
    public BigDecimal getMoneyB() {
        return moneyB;
    }

    /**
     * 设置期初余额
     *
     * @param moneyB 期初余额
     */
    public void setMoneyB(BigDecimal moneyB) {
        this.moneyB = moneyB;
    }

    /**
     * 获取期末余额
     *
     * @return money_e - 期末余额
     */
    public BigDecimal getMoneyE() {
        return moneyE;
    }

    /**
     * 设置期末余额
     *
     * @param moneyE 期末余额
     */
    public void setMoneyE(BigDecimal moneyE) {
        this.moneyE = moneyE;
    }

    /**
     * 获取借方金额
     *
     * @return money_d - 借方金额
     */
    public BigDecimal getMoneyD() {
        return moneyD;
    }

    /**
     * 设置借方金额
     *
     * @param moneyD 借方金额
     */
    public void setMoneyD(BigDecimal moneyD) {
        this.moneyD = moneyD;
    }

    /**
     * 获取贷方金额
     *
     * @return money_c - 贷方金额
     */
    public BigDecimal getMoneyC() {
        return moneyC;
    }

    /**
     * 设置贷方金额
     *
     * @param moneyC 贷方金额
     */
    public void setMoneyC(BigDecimal moneyC) {
        this.moneyC = moneyC;
    }

    /**
     * 获取当前会计期间
     *
     * @return period - 当前会计期间
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置当前会计期间
     *
     * @param period 当前会计期间
     */
    public void setPeriod(Integer period) {
        this.period = period;
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