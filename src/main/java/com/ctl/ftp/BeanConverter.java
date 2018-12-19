package com.ctl.ftp;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method; 
import java.util.Date;
import java.util.HashMap; 
import java.util.Iterator;
import java.util.Map; 
import java.util.Set;

import org.slf4j.*;

/** 
*转换器 
*1:将JavaBean 转换成Map、JSONObject 
*2:将JSONObject 转换成Map 
* 
* @author xxx 
*/ 
public class BeanConverter 
{ 
	static Logger logger = LoggerFactory.getLogger(BeanConverter.class);
	static{
		try {
			new com.ctl.ftp.DesUtils();
		} catch (Exception e) {
			logger.error("初始化DesUtils失败：",e);
		}
	}
	
	public static void main(String[] args) throws Exception {
		String str=DateUtil.sdf.format(new Date());
		System.out.println(DesUtils.encrypt(str));
		System.out.println(DesUtils.decrypt(DesUtils.encrypt(str)));
	}
    /** 
     * 将javaBean转换成Map(加密后)  
     * 
     * @param javaBean javaBean 
     * @return Map对象 
     */ 
    public static Map<String, String> toEncryptMap(Object javaBean) 
    { 
        Map<String, String> result = new HashMap<String, String>(); 
        Method[] methods = javaBean.getClass().getDeclaredMethods(); 
        for (Method method : methods){ 
            try{ 
                if (method.getName().startsWith("get")){
                    String field = method.getName(); 
                    String simpleName=method.getReturnType().getSimpleName();
                    field = field.substring(field.indexOf("get") + 3); 
                    field = field.toLowerCase().charAt(0) + field.substring(1); 
                    Object value = method.invoke(javaBean, (Object[])null); 
                    if(null!=value&&!"".equals(value)&&"String".equals(simpleName)){
                    	result.put(field, null == value ? "" : DesUtils.encrypt( value.toString()));
                    }else{
                    	result.put(field, null == value ? "" : value.toString());
                    }
                } 
            }catch (Exception e){
            	logger.error("toEncryptMap",e);
            } 
        } 
        return result; 
    } 
	/**
	 * 
	 * @param 将javaBean转换成Map(解密后)  
	 * @return
	 */
    public static Map<String, String> toDecryptMap(Object javaBean) 
    { 
        Map<String, String> result = new HashMap<String, String>(); 
        Method[] methods = javaBean.getClass().getDeclaredMethods(); 

        for (Method method : methods){
            try{
                if (method.getName().startsWith("get")){
                	 String simpleName=method.getReturnType().getSimpleName();
                	String field = method.getName(); 
                    field = field.substring(field.indexOf("get") + 3); 
                    field = field.toLowerCase().charAt(0) + field.substring(1); 

                    Object value = method.invoke(javaBean, (Object[])null); 
                    if(null!=value&&!"".equals(value)&&"String".equals(simpleName)){
                    	result.put(field, null == value ? "" : DesUtils.decrypt( value.toString()));
                    }else{
                    	result.put(field, null == value ? "" : value.toString());
                    }
                } 
            }catch (Exception e){
            	logger.error("toDecryptMap",e);
            } 
        } 
        return result; 
    } 
    /**
     * 
     * @param mapData  待加密map
     * @return
     */
    public static Map<String, Object> toDecryptMap(Map<String,Object> mapData) 
    { 
    	 Set<String> key=mapData.keySet();
         Iterator<String> iter=key.iterator();
         while(iter.hasNext()){
         	String keyStr=iter.next();
         	if(!(mapData.get(keyStr) instanceof String)){
         		mapData.put(keyStr, mapData.get(keyStr));
         		continue;
         	}
         	String valueStr=(String) mapData.get(keyStr);
         	try {
         		new com.ctl.ftp.DesUtils();
				valueStr=null==valueStr ? "" : com.ctl.ftp.DesUtils.decrypt(valueStr);
			} catch (Exception e) {
				logger.error(keyStr+" 解密失败,解密前："+valueStr,e);
			}
         	mapData.put(keyStr, valueStr);
         }
        return mapData; 
    } 
    
    /** 
     * 将json对象转换成Map 
     * 
     * @param jsonObject json对象 
     * @return Map对象 
     */ 
//    public static Map<String, String> toMap(JSONObject jsonObject) 
//    { 
//        Map<String, String> result = new HashMap<String, String>(); 
//        Iterator<String> iterator = jsonObject.keys(); 
//        String key = null; 
//        String value = null; 
//        while (iterator.hasNext()) 
//        { 
//            key = iterator.next(); 
//            value = jsonObject.getString(key); 
//            result.put(key, value); 
//        } 
//        return result; 
//    } 

    /** 
     * 将javaBean转换成JSONObject 
     * 
     * @param bean javaBean 
     * @return json对象 
     */ 
//    public static JSONObject toJSON(Object bean) 
//    { 
//        return new JSONObject(toMap(bean)); 
//    } 

    /** 
     * 将map转换成Javabean 
     * @param javabean javaBean 
     * @param data map数据 
     */ 
    public static Object toJavaBean(Object javabean, Map<String, Object> data) 
    { 
        Method[] methods = javabean.getClass().getDeclaredMethods(); 
        for (Method method : methods){ 
        	String field =null;
            try { 
                if (method.getName().startsWith("set")){
                    field = method.getName(); 
                    field = field.substring(field.indexOf("set") + 3); 
                    field = field.toLowerCase().charAt(0) + field.substring(1); 
                    Class<?>[] returnType = method.getParameterTypes();
                    //logger.info(Arrays.deepToString(returnType)+"\t"+returnType[0].getSimpleName());
                    Object value=data.get(field);
                    String fieldType=returnType[0].getSimpleName();
                    if(value!=null){
	                    try {
	                    	method.invoke(javabean, new Object[]{(Object)value}); 
						} catch (Exception e) {
							try {
								if("Integer".equals(fieldType)){
									method.invoke(javabean, new Object[]{Integer.parseInt(value.toString())}); 
								}else if("Float".equals(fieldType)){
									method.invoke(javabean, new Object[]{Float.parseFloat(value.toString())}); 
								}else if("Double".equals(fieldType)){
									method.invoke(javabean, new Object[]{Double.parseDouble(value.toString())}); 
								}
							} catch (Exception e2) {
								logger.error(field+"类型为:"+field.getClass()+" "+value,e);
							}
						}
                    }
                } 
            }catch (Exception e){ 
            	logger.error(field+"类型为:"+field.getClass(),e);
            } 
        } 
        return javabean; 
    } 
    public static Map<String, Object> transBean2Map(Object obj,Map<String,Object> map) {
        if(obj == null){
            return null;
        }        
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    if(null!=value&&!"".equals(value))
                    map.put(key, value);
                }

            }
        } catch (Exception e) {
        	logger.error("transBean2Map Error " , e);
        }
        return map;
    }
    
    
    public static Object transMap2Bean(Object javabean, Map<String, Object> data) 
    { 
        Method[] methods = javabean.getClass().getDeclaredMethods(); 
        for (Method method : methods){
            try{ 
                if (method.getName().startsWith("set")){
                    String field = method.getName(); 
                    field = field.substring(field.indexOf("set") + 3); 
                    field = field.toLowerCase().charAt(0) + field.substring(1); 
                    method.invoke(javabean, new Object[]{data.get(field)}); 
                } 
            }catch (Exception e){ 
            	logger.error("transMap2Bean",e);
            } 
        } 
        return javabean; 
    } 
    
    /** 
     * 将javaBean转换成JSONObject 
     * 
     * @param bean javaBean 
     * @return json对象 
     * @throws ParseException json解析异常 
     */ 
//    public static void toJavaBean(Object javabean, String data) throws ParseException 
//    { 
//        JSONObject jsonObject = new JSONObject(data); 
//        Map<String, String> datas = toMap(jsonObject); 
//        toJavaBean(javabean, datas); 
//    } 
}
