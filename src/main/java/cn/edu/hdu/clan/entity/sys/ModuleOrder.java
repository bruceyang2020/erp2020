package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.util.Date;
import javax.persistence.*;

@Table(name = "module_order")
public class ModuleOrder extends BaseBean {
    @Id
    private String id;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "team_count")
    private String teamCount;

    @Column(name = "activities")
    private String activities;

    @Column(name = "state")
    private Integer state;

    @Column(name = "period")
    private Integer period;

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
     * @return activities
     */
    public String getActivities() {
        return activities;
    }

    /**
     * @param activities
     */
    public void setActivities(String activities) {
        this.activities = activities;
    }

    /**
     * @return state
     */
     public Integer getState(){ return  state; }

    /**
     * @param state
     */
    public void setState(Integer state){this.state = state;}

    /**
     * @return period
     */
    public Integer getPeriod(){ return  period; }

    /**
     * @param period
     */
    public void setPeriod(Integer period){this.period = period;}

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