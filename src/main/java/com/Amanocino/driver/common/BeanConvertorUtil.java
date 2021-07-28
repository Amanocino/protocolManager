package com.Amanocino.driver.common;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : zhicheng chen
 * @date : 2021/7/22
 * @time : 15:09
 */
public class BeanConvertorUtil {
    /**
     * @author lixiao 90183971
     * @date 2021/5/21 9:02
     * @param src 带属性的实体   target被赋值的实体
     * @return void
     */
    public static Object convertor(Object src,Object target) throws Exception {

        Class<?> srcClz = src.getClass();
        Class<?> targetClz = target.getClass();

        Field[] fields = targetClz.getDeclaredFields();

        for (Field field : fields) {

            field.setAccessible(true);

            //获取注解
            FileName annotation = field.getAnnotation(FileName.class);
            if(annotation!=null && annotation.name()!=null){
                //获取当前方法的参数类型
//                Class<?>  c = getMethodParamTypes(target,"set" + captureName(annotation.name()));
//                Method method = targetClz.getDeclaredMethod("set" + captureName(annotation.name()),c);

                //获取当前方法的参数类型
//                Class<?>  getc = getMethodParamTypes(srcClz,"get" + captureName(annotation.name()));
                Method getMethod = srcClz.getDeclaredMethod("get" + captureName(annotation.name()));

                getMethod.setAccessible(true);
                //获取属性值
                Object value = getMethod.invoke(src);

                field.set(target, convert(value, field.getType()));
//                method.invoke(target,convert(value, c));
            }
        }
        return target;
    }
    //方法首字母大写
    private static String captureName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }
    //获取方法的类型
    private static Class<?>  getMethodParamTypes(Object classInstance,String methodName){
        //全部方法
        Method[]  methods = classInstance.getClass().getMethods();
        Class<?> param = null;
        for (int  i = 0;  i< methods.length; i++) {
            //和传入方法名匹配
            if(methodName.equals(methods[i].getName())){
                param = methods[i].getParameterTypes()[0];
            }
        }
        return param;
    }

    public static<T> T convert(Object obj, Class<T> type) {
        if (obj != null && StringUtils.hasText(obj.toString())) {
            if (type.equals(Integer.class)||type.equals(int.class)) {
                return (T)new Integer(obj.toString());
            } else if (type.equals(Long.class)||type.equals(long.class)) {
                return (T)new Long(obj.toString());
            } else if (type.equals(Boolean.class)||type.equals(boolean.class)) {
                return (T) new Boolean(obj.toString());
            } else if (type.equals(Short.class)||type.equals(short.class)) {
                return (T) new Short(obj.toString());
            } else if (type.equals(Float.class)||type.equals(float.class)) {
                return (T) new Float(obj.toString());
            } else if (type.equals(Double.class)||type.equals(double.class)) {
                return (T) new Double(obj.toString());
            } else if (type.equals(Byte.class)||type.equals(byte.class)) {
                return (T) new Byte(obj.toString());
            } else if (type.equals(Character.class)||type.equals(char.class)) {
                return (T)new Character(obj.toString().charAt(0));
            } else if (type.equals(String.class)) {
                return (T) obj;
            } else if (type.equals(BigDecimal.class)) {
                return (T) new BigDecimal(obj.toString());
            }if (type.equals(int.class)) {
                return (T)obj;
            } else if (type.equals(long.class)) {
                return (T)obj;
            } else if (type.equals(boolean.class)) {
                return (T)obj;
            } else if (type.equals(short.class)) {
                return (T)obj;
            } else if (type.equals(float.class)) {
                return (T)obj;
            } else if (type.equals(double.class)) {
                return (T)obj;
            } else if (type.equals(byte.class)) {
                return (T)obj;
            } else if (type.equals(char.class)) {
                return (T)obj;
            } else if (type.equals(LocalDateTime.class)) {
                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                return (T) LocalDateTime.parse(obj.toString());
            } else if (type.equals(Date.class)) {
                try
                {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    return (T) formatter.parse(obj.toString());
                }
                catch (ParseException e)
                {
                    throw new RuntimeException(e.getMessage());
                }

            }else{
                return null;
            }
        } else {
            if (type.equals(int.class)) {
                return (T)new Integer(0);
            } else if (type.equals(long.class)) {
                return (T)new Long(0L);
            } else if (type.equals(boolean.class)) {
                return (T)new Boolean(false);
            } else if (type.equals(short.class)) {
                return (T)new Short("0");
            } else if (type.equals(float.class)) {
                return (T) new Float(0.0);
            } else if (type.equals(double.class)) {
                return (T) new Double(0.0);
            } else if (type.equals(byte.class)) {
                return (T) new Byte("0");
            } else if (type.equals(char.class)) {
                return (T) new Character('\u0000');
            }else {
                return null;
            }
        }
    }
}
