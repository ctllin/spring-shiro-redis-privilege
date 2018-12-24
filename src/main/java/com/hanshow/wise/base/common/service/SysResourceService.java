package com.hanshow.wise.base.common.service;

import com.ctl.sys.manger.po.SysResource;
import com.hanshow.wise.base.common.model.dto.SysResourceDto;

import java.util.List;

/**
 * <p>Title: SysResourceService</p>
 * <p>Description: 资源接口</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-12-24 10:13
 */
public interface SysResourceService {
    /**
     * 根据用户编号获取资源列表（上下级和按钮，递归）
     * @param userId
     * @return
     */
    List<SysResourceDto> getSysResourceByUserId(Integer userId);
}
