package com.hanshow.wise.base.common.model.dto;

import com.hanshow.wise.base.common.model.SysResource;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: SysResourceDto</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-12-24 10:20
 */
public class SysResourceDto extends SysResource implements Serializable {
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 下级菜单或按钮
     */
    private List<SysResource> childSysResourceList;

    public List<SysResource> getChildSysResourceList() {
        return childSysResourceList;
    }

    public void setChildSysResourceList(List<SysResource> childSysResourceList) {
        this.childSysResourceList = childSysResourceList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
