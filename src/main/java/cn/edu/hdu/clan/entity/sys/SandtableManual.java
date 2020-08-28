package cn.edu.hdu.clan.entity.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sandtable_manual")
public class SandtableManual  extends BaseBean {

    /**
     * 用户名
     */
    @Column(name = "user_id")
    private String userId;


    /**
     * 期间
     */
    private Integer period;


    /**
     * 用户沙盘盘面数据
     */
    @Column(name = "user_data")
    private String userData;

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



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getEditUser() {
        return editUser;
    }

    @Override
    public void setEditUser(String editUser) {
        this.editUser = editUser;
    }

    @Override
    public Date getEditTime() {
        return editTime;
    }

    @Override
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}
