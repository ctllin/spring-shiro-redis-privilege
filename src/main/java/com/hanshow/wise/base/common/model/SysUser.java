package com.hanshow.wise.base.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: SysUser</p>
 * <p>Description:用户 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 * @author hanshow
 * @date 2018-12-24 10:32:11
 * @version 1.0
 */
public class SysUser implements Serializable {
    /**
    * 分页查询条件
    */
    private String whereStr;

    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String loginname;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String passwordSalt;

    /**
     * 
     */
    private Integer customerId;

    /**
     * 
     */
    private Integer sex;

    /**
     * 
     */
    private Integer age;

    /**
     * 
     */
    private Integer usertype;

    /**
     * 
     */
    private Integer isdefault;

    /**
     * 
     */
    private Integer state;

    /**
     * 
     */
    private Integer organizationId;

    /**
     * 
     */
    private Date createdatetime;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private String organizationname;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt == null ? null : passwordSalt.trim();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOrganizationname() {
        return organizationname;
    }

    public void setOrganizationname(String organizationname) {
        this.organizationname = organizationname == null ? null : organizationname.trim();
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginname=").append(loginname);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", passwordSalt=").append(passwordSalt);
        sb.append(", customerId=").append(customerId);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append(", usertype=").append(usertype);
        sb.append(", isdefault=").append(isdefault);
        sb.append(", state=").append(state);
        sb.append(", organizationId=").append(organizationId);
        sb.append(", createdatetime=").append(createdatetime);
        sb.append(", phone=").append(phone);
        sb.append(", organizationname=").append(organizationname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getWhereStr() {
        return whereStr;
    }

    public void setWhereStr(String whereStr) {
        this.whereStr = whereStr;
    }
}