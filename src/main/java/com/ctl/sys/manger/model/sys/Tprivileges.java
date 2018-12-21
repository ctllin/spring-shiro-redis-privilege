package com.ctl.sys.manger.model.sys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: Tprivileges</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-12-21 10:29
 */
public class Tprivileges implements Serializable {
    private Date createdatetime; // 创建时间
    private String name; // 名称
    private String url; // 菜单路径
    private String description; // 描述
    private String icon; // 图标
    private Integer seq; // 排序号
    private Integer pid; //
    private Integer uid; //
    private Integer id; //
    private Integer resourcetype; // 资源类型, 0菜单 1功能
    private List<Tprivileges> tprivilegesList; // 子菜单(按钮)
    private Integer state; // 状态 0启用 1停用

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(Integer resourcetype) {
        this.resourcetype = resourcetype;
    }

    public List<Tprivileges> getTprivilegesList() {
        return tprivilegesList;
    }

    public void setTprivilegesList(List<Tprivileges> tprivilegesList) {
        this.tprivilegesList = tprivilegesList;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
