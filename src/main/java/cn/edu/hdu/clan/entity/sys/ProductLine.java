package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "product_line")
public class ProductLine extends BaseBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "team_count")
    private String teamCount;

    private String number;

    @Column(name = "product_line_number")
    private String productLineNumber;

    private Integer period;

    @Column(name = "product_line_type_id")
    private String productLineTypeId;

    private Integer state;

    @Column(name = "period_buy")
    private Integer periodBuy;

    @Column(name = "device_value")
    private BigDecimal deviceValue;

    @Column(name = "depreciation_c")
    private BigDecimal depreciationC;

    @Column(name = "deprecation_a")
    private BigDecimal deprecationA;

    @Column(name = "installed_period_a")
    private Integer installedPeriodA;

    @Column(name = "transferred_period_a")
    private Integer transferredPeriodA;

    @Column(name = "transfer_fee_a")
    private Long transferFeeA;

    @Column(name = "investment_amount_a")
    private Long investmentAmountA;

    @Column(name = "maintenance_fee_a")
    private Long maintenanceFeeA;

    @Column(name = "product_c")
    private String productC;

    @Column(name = "processing_cycle")
    private Integer processingCycle;

    @Column(name = "processing_cycle_b")
    private Integer processingCycleB;

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
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
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
     * @return number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return product_line_number
     */
    public String getProductLineNumber() {
        return productLineNumber;
    }

    /**
     * @param productLineNumber
     */
    public void setProductLineNumber(String productLineNumber) {
        this.productLineNumber = productLineNumber;
    }

    /**
     * @return period
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * @param period
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * @return product_line_type_id
     */
    public String getProductLineTypeId() {
        return productLineTypeId;
    }

    /**
     * @param productLineTypeId
     */
    public void setProductLineTypeId(String productLineTypeId) {
        this.productLineTypeId = productLineTypeId;
    }

    /**
     * @return state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return period_buy
     */
    public Integer getPeriodBuy() {
        return periodBuy;
    }

    /**
     * @param periodBuy
     */
    public void setPeriodBuy(Integer periodBuy) {
        this.periodBuy = periodBuy;
    }

    /**
     * @return device_value
     */
    public BigDecimal getDeviceValue() {
        return deviceValue;
    }

    /**
     * @param deviceValue
     */
    public void setDeviceValue(BigDecimal deviceValue) {
        this.deviceValue = deviceValue;
    }

    /**
     * @return depreciation_c
     */
    public BigDecimal getDepreciationC() {
        return depreciationC;
    }

    /**
     * @param depreciationC
     */
    public void setDepreciationC(BigDecimal depreciationC) {
        this.depreciationC = depreciationC;
    }

    /**
     * @return deprecation_a
     */
    public BigDecimal getDeprecationA() {
        return deprecationA;
    }

    /**
     * @param deprecationA
     */
    public void setDeprecationA(BigDecimal deprecationA) {
        this.deprecationA = deprecationA;
    }

    /**
     * @return installed_period_a
     */
    public Integer getInstalledPeriodA() {
        return installedPeriodA;
    }

    /**
     * @param installedPeriodA
     */
    public void setInstalledPeriodA(Integer installedPeriodA) {
        this.installedPeriodA = installedPeriodA;
    }

    /**
     * @return transferred_period_a
     */
    public Integer getTransferredPeriodA() {
        return transferredPeriodA;
    }

    /**
     * @param transferredPeriodA
     */
    public void setTransferredPeriodA(Integer transferredPeriodA) {
        this.transferredPeriodA = transferredPeriodA;
    }

    /**
     * @return transfer_fee_a
     */
    public Long getTransferFeeA() {
        return transferFeeA;
    }

    /**
     * @param transferFeeA
     */
    public void setTransferFeeA(Long transferFeeA) {
        this.transferFeeA = transferFeeA;
    }

    /**
     * @return investment_amount_a
     */
    public Long getInvestmentAmountA() {
        return investmentAmountA;
    }

    /**
     * @param investmentAmountA
     */
    public void setInvestmentAmountA(Long investmentAmountA) {
        this.investmentAmountA = investmentAmountA;
    }

    /**
     * @return maintenance_fee_a
     */
    public Long getMaintenanceFeeA() {
        return maintenanceFeeA;
    }

    /**
     * @param maintenanceFeeA
     */
    public void setMaintenanceFeeA(Long maintenanceFeeA) {
        this.maintenanceFeeA = maintenanceFeeA;
    }

    /**
     * @return product_c
     */
    public String getProductC() {
        return productC;
    }

    /**
     * @param productC
     */
    public void setProductC(String productC) {
        this.productC = productC;
    }

    /**
     * @return processing_cycle
     */
    public Integer getProcessingCycle() {
        return processingCycle;
    }

    /**
     * @param processingCycle
     */
    public void setProcessingCycle(Integer processingCycle) {
        this.processingCycle = processingCycle;
    }

    /**
     * @return processing_cycle_b
     */
    public Integer getProcessingCycleB() {
        return processingCycleB;
    }

    /**
     * @param processingCycleB
     */
    public void setProcessingCycleB(Integer processingCycleB) {
        this.processingCycleB = processingCycleB;
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