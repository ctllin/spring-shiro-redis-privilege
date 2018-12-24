package com.hanshow.wise.base.common.service;

import com.ctl.common.util.StringUtil;
import com.hanshow.wise.base.common.dao.SysResourceMapper;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: SysResourceTest</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-12-24 10:41
 */
public class SysResourceTest {
    public static void main(String[] args) {
        ApplicationContext context= null;// new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        context=  new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis-test.xml");
        Map<String,Object> map = new HashMap<>();
        JsonConfig jsonConfig=new JsonConfig();
        SysResourceMapper sysResourceMapper = (SysResourceMapper) context.getBean("sysResourceMapper");
        //System.out.println(sysResourceMapper.selectByPrimaryKey(1));
        System.out.println(StringUtil.formatJson(JSONArray.fromObject(sysResourceMapper.getSysResourceByUserId(36)).toString()));
        System.out.println( JSONArray.fromObject(sysResourceMapper.getSysResourceByUserId(36)).toString() );
    }
}
