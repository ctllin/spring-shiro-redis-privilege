package com.hanshow.wise.portal.center.controller.common;

import com.ctl.sys.manger.controller.sys.IndexController;
import com.ctl.sys.manger.pageModel.base.Json;
import com.hanshow.wise.base.common.model.SysResource;
import com.hanshow.wise.base.common.model.dto.SysResourceDto;
import com.hanshow.wise.base.common.service.SysResourceService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: SysResourceController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-12-24 10:11
 */
@RestController
@RequestMapping("/system")
public class SysResourceController {
    private static Logger logger = LoggerFactory.getLogger(SysResourceController.class);
    @Autowired
    private SysResourceService sysResourceService;
    /**
     * 根据用户编号获取用户对应的权限
     */
    @RequestMapping(value = "/resource/getResourceByUserId", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public Object getResourceByUserId(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
        Json returnJson = new Json();
        if (jsonObject == null || jsonObject.get("userId") == null) {
            List<SysResourceDto> sysResourceDtoList = new ArrayList<>();
            logger.info("userId={},return", jsonObject.get("userId"));
            returnJson.setSuccess(false);
            returnJson.setMsg("COMMON_USERID_IS_NEED");
            return returnJson;
        } else {
            returnJson.setSuccess(true);
            returnJson.setObj(sysResourceService.getSysResourceByUserId(Integer.parseInt(jsonObject.get("userId").toString())));
            return returnJson;
        }
    }
    /**
     * 新建资源
     * @param request
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/resource/addResource", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public Object addResource(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
        Json returnJson = new Json();
        SysResource record = null;
        JsonConfig jsonConfig = new JsonConfig();
        Map<String, Object> classMap = new HashMap<>();
        //classMap.put("storeIdList", String.class);
        // classMap.put("clientTypeList", Integer.class);
        jsonConfig.setClassMap(classMap);
        record = (SysResource) JSONObject.toBean(jsonObject, new SysResource(), jsonConfig);
        if (record == null) {
            returnJson.setSuccess(false);
            returnJson.setMsg("COMMON_RECORD_IS_NEED");
            return returnJson;
        }else {
            returnJson.setSuccess(true);
            return sysResourceService.addResource(record);
        }
    }
    /**
     * 更新资源
     * @param request
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/resource/modifyResource", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public Object modifyResource(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
        if(jsonObject==null||jsonObject.get("userId")==null){
            List<SysResourceDto> sysResourceDtoList= new ArrayList<>();
            logger.info("userId={},return",jsonObject.get("userId"));
            return sysResourceDtoList;
        }else{
            return sysResourceService.getSysResourceByUserId(Integer.parseInt(jsonObject.get("userId").toString()));
        }
    }
}
