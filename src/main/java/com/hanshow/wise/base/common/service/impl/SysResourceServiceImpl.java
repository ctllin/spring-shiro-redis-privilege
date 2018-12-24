package com.hanshow.wise.base.common.service.impl;

import com.hanshow.wise.base.common.dao.SysResourceMapper;
import com.hanshow.wise.base.common.model.dto.SysResourceDto;
import com.hanshow.wise.base.common.service.SysResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title: SysResourceServiceImpl</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-12-24 10:14
 */
@Service
public class SysResourceServiceImpl implements SysResourceService {
    private static Logger logger = LoggerFactory.getLogger(SysResourceServiceImpl.class);
    @Autowired
    private SysResourceMapper sysResourceMapper;

    /**
     * 根据用户编号获取资源列表（上下级和按钮，递归）
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysResourceDto> getSysResourceByUserId(Integer userId) {
        if(userId==null){
            logger.info("userId={},return",userId);
        }
        List<SysResourceDto> sysResourceByUserId = sysResourceMapper.getSysResourceByUserId(userId);
        return null;
    }
}
