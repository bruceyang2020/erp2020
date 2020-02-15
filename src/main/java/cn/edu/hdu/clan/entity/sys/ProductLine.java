package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "product_line")
public class ProductLine extends BaseBean {


    @Column(name = "group_id")
    private String groupId;

    @Column(name = "team_count")
    private String teamCount;

    /**
     * 厂房编号
     */
    @Column(name = "factory_number")
    private String factoryNumber;

    /**
     * 生产线编号
     */
    @Column(name = "product_line_number")
    private String productLineNumber;

    /**
     * 当前会计期间
     */
    private Integer period;

    /**
     * 产线类型编码
     */
    @Column(name = "product_line_type_id")
    private String productLineTypeId;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 购入会计期间
     */
    @Column(name = "period_buy")
    private Integer periodBuy;

    /**
     * 设备原值
     */
    @Column(name = "device_value")
    private BigDecimal deviceValue;

    /**
     * 本期折旧
     */
    @Column(name = "depreciation_c")
    private BigDecimal depreciationC;

    /**
     * 已提折旧
     */
    @Column(name = "deprecation_a")
    private BigDecimal deprecationA;

    /**
     * 已安装周期
     */
    @Column(name = "installed_period_a")
    private Integer installedPeriodA;

    /**
     * 已转产周期
     */
    @Column(name = "transferred_period_a")
    private Integer transferredPeriodA;

    /**
     * 已投入转产费
     */
    @Column(name = "transfer_fee_a")
    private BigDecimal transferFeeA;

    /**
     * 已投资金额
     */
    @Column(name = "investment_amount_a")
    private BigDecimal investmentAmountA;

    /**
     * 累计维修费
     */
    @Column(name = "maintenance_fee_a")
    private BigDecimal maintenanceFeeA;

    /**
     * 当前产品编号
     */
    @Column(name = "product_c")
    private String productC;

    /**
     * 所需加工周期
     */
    @Column(name = "processing_cycle")
    private Integer processingCycle;

    /**
     * 投入生产期间
     */
    @Column(name = "processing_cycle_b")
    private Integer processingCycleB;


    /**
     * 当期的操作是否完成。0未完成 1完成
     */
    @Column(name = "edit_flag")
    private Integer editFlag;


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
     * 获取厂房编号
     *
     * @return number - 厂房编号
     */
    public String getFactoryNumber() {
        return factoryNumber;
    }

    /**
     * 设置厂房编号
     *
     * @param factoryNumber 厂房编号
     */
    public void setFactoryNumber(String factoryNumber) {
        this.factoryNumber = factoryNumber;
    }

    /**
     * 获取生产线编号
     *
     * @return product_line_number - 生产线编号
     */
    public String getProductLineNumber() {
        return productLineNumber;
    }

    /**
     * 设置生产线编号
     *
     * @param productLineNumber 生产线编号
     */
    public void setProductLineNumber(String productLineNumber) {
        this.productLineNumber = productLineNumber;
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
     * 获取产线类型编码
     *
     * @return product_line_type_id - 产线类型编码
     */
    public String getProductLineTypeId() {
        return productLineTypeId;
    }

    /**
     * 设置产线类型编码
     *
     * @param productLineTypeId 产线类型编码
     */
    public void setProductLineTypeId(String productLineTypeId) {
        this.productLineTypeId = productLineTypeId;
    }

    /**
     * 获取状态
     *
     * @return state - 状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态
     *
     * @param state 状态
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取购入会计期间
     *
     * @return period_buy - 购入会计期间
     */
    public Integer getPeriodBuy() {
        return periodBuy;
    }

    /**
     * 设置购入会计期间
     *
     * @param periodBuy 购入会计期间
     */
    public void setPeriodBuy(Integer periodBuy) {
        this.periodBuy = periodBuy;
    }

    /**
     * 获取设备原值
     *
     * @return device_value - 设备原值
     */
    public BigDecimal getDeviceValue() {
        return deviceValue;
    }

    /**
     * 设置设备原值
     *
     * @param deviceValue 设备原值
     */
    public void setDeviceValue(BigDecimal deviceValue) {
        this.deviceValue = deviceValue;
    }

    /**
     * 获取本期折旧
     *
     * @return depreciation_c - 本期折旧
     */
    public BigDecimal getDepreciationC() {
        return depreciationC;
    }

    /**
     * 设置本期折旧
     *
     * @param depreciationC 本期折旧
     */
    public void setDepreciationC(BigDecimal depreciationC) {
        this.depreciationC = depreciationC;
    }

    /**
     * 获取已提折旧
     *
     * @return deprecation_a - 已提折旧
     */
    public BigDecimal getDeprecationA() {
        return deprecationA;
    }

    /**
     * 设置已提折旧
     *
     * @param deprecationA 已提折旧
     */
    public void setDeprecationA(BigDecimal deprecationA) {
        this.deprecationA = deprecationA;
    }

    /**
     * 获取已安装周期
     *
     * @return installed_period_a - 已安装周期
     */
    public Integer getInstalledPeriodA() {
        return installedPeriodA;
    }

    /**
     * 设置已安装周期
     *
     * @param installedPeriodA 已安装周期
     */
    public void setInstalledPeriodA(Integer installedPeriodA) {
        this.installedPeriodA = installedPeriodA;
    }

    /**
     * 获取已转产周期
     *
     * @return transferred_period_a - 已转产周期
     */
    public Integer getTransferredPeriodA() {
        return transferredPeriodA;
    }

    /**
     * 设置已转产周期
     *
     * @param transferredPeriodA 已转产周期
     */
    public void setTransferredPeriodA(Integer transferredPeriodA) {
        this.transferredPeriodA = transferredPeriodA;
    }

    /**
     * 获取已投入转产费
     *
     * @return transfer_fee_a - 已投入转产费
     */
    public BigDecimal getTransferFeeA() {
        return transferFeeA;
    }

    /**
     * 设置已投入转产费
     *
     * @param transferFeeA 已投入转产费
     */
    public void setTransferFeeA(BigDecimal transferFeeA) {
        this.transferFeeA = transferFeeA;
    }

    /**
     * 获取已投资金额
     *
     * @return investment_amount_a - 已投资金额
     */
    public BigDecimal getInvestmentAmountA() {
        return investmentAmountA;
    }

    /**
     * 设置已投资金额
     *
     * @param investmentAmountA 已投资金额
     */
    public void setInvestmentAmountA(BigDecimal investmentAmountA) {
        this.investmentAmountA = investmentAmountA;
    }

    /**
     * 获取累计维修费
     *
     * @return maintenance_fee_a - 累计维修费
     */
    public BigDecimal getMaintenanceFeeA() {
        return maintenanceFeeA;
    }

    /**
     * 设置累计维修费
     *
     * @param maintenanceFeeA 累计维修费
     */
    public void setMaintenanceFeeA(BigDecimal maintenanceFeeA) {
        this.maintenanceFeeA = maintenanceFeeA;
    }

    /**
     * 获取当前产品编号
     *
     * @return product_c - 当前产品编号
     */
    public String getProductC() {
        return productC;
    }

    /**
     * 设置当前产品编号
     *
     * @param productC 当前产品编号
     */
    public void setProductC(String productC) {
        this.productC = productC;
    }

    /**
     * 获取所需加工周期
     *
     * @return processing_cycle - 所需加工周期
     */
    public Integer getProcessingCycle() {
        return processingCycle;
    }

    /**
     * 设置所需加工周期
     *
     * @param processingCycle 所需加工周期
     */
    public void setProcessingCycle(Integer processingCycle) {
        this.processingCycle = processingCycle;
    }

    /**
     * 获取投入生产期间
     *
     * @return processing_cycle_b - 投入生产期间
     */
    public Integer getProcessingCycleB() {
        return processingCycleB;
    }

    /**
     * 设置投入生产期间
     *
     * @param processingCycleB 投入生产期间
     */
    public void setProcessingCycleB(Integer processingCycleB) {
        this.processingCycleB = processingCycleB;
    }


    /**
     * 获取当前操作状态
     *
     * @return  - 状态
     */
    public Integer getEditFlag() {
        return editFlag;
    }

    /**
     * 设置状态当前操作状态
     *
     * @param editFlag 状态
     */
    public void setEditFlag(Integer editFlag) {
        this.editFlag = editFlag;
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