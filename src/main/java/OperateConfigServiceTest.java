import com.ctl.sys.manger.mapper.SysUserMapper;
import com.ctl.sys.manger.po.SysUser;
import com.ctl.sys.manger.po.SysUserExample;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: OperateConfigServiceTest</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-10-17 11:38
 */
public class OperateConfigServiceTest {
    public static void main(String[] args) {
        ApplicationContext context= null;// new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        context=  new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        Map<String,Object> map = new HashMap<>();
        JsonConfig jsonConfig=new JsonConfig();

        SysUserMapper sysUserMapper = (SysUserMapper) context.getBean("sysUserMapper");
        SysUserExample sysUserExample=new SysUserExample();
        //state 0启用 1 停用  停用用户不能登录
        sysUserExample.createCriteria().andLoginnameEqualTo("ctl").andStateEqualTo(0);
        // List<SysUser> selectByExample = sysUserMapper.selectByExample(sysUserExample);
       // System.out.println(selectByExample.get(0));
        //System.out.println(sysUserMapper.selectByPrimaryKey(36));
        System.out.println(JSONArray.fromObject(sysUserMapper.getRolesByUserId(36)));
        System.out.println(JSONArray.fromObject(sysUserMapper.getPermissionsByUserId(36)));
    }
}
