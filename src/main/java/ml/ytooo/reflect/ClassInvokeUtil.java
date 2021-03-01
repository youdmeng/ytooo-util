package ml.ytooo.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClassInvokeUtil {

    public static Object getMethodValue(Object obj, String methodName) throws Exception {
        methodName = "get" + upperFirst(methodName);
        Method method = getTargetMethod(obj, methodName);
        return method.invoke(obj, null);
    }

    public static void setMethodValue(Object obj, String methodName, Object value) throws Exception {
        methodName = "set" + upperFirst(methodName);
        Method method = getTargetMethod(obj, methodName);
        Object[] params = { value };
        method.invoke(obj, params);
    }

    public static List<Object> getAllFields(Object obj) {
        List<Object> filedList = new ArrayList<Object>();
        Class cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            filedList.add(upperFirst(field.getName()));
        }
        return filedList;
    }

    public static Class getReturnType(Object obj, String methodName) {
        methodName = "get" + upperFirst(methodName);
        Method method = getTargetMethod(obj, methodName);
        return method.getReturnType();
    }

    public static Method getTargetMethod(Object facade, String facadeMethod) {
        Class type = facade.getClass();
        Method methods[] = type.getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (facadeMethod.equals(methods[i].getName())) {
                Class[] paramTypes = methods[i].getParameterTypes();
                if (null == paramTypes || 0 == paramTypes.length || 1 == paramTypes.length) {
                    return methods[i];
                }
                else {
                    // 只调用参数个数为0或者1的方法，其他忽略
                    continue;
                }
            }
        }
        throw new RuntimeException("Invalid target method " + facadeMethod + " In Class" + facade);
    }

    public static Map<Object,Object> initCommonDTO(Object commonDTO) throws Exception {
        Map<Object,Object> rsMap = new HashMap<Object,Object>();
        Class dtoClass = commonDTO.getClass();
        Field[] fields = dtoClass.getDeclaredFields();
        for (Field filed : fields) {
            String filedName = filed.getName();
            Object value = getMethodValue(commonDTO, upperFirst(filedName));
            rsMap.put(filedName.toUpperCase(), value);
        }
        return rsMap;
    }

    public static void objectToMap(Map source, Object target) throws Exception {
        Set keyset = source.keySet();

        for (Object key : keyset) {
            setMethodValue(target, String.valueOf(key), source.get(key));
        }
    }

    private static String upperFirst(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
    }

    public static Object invokeService(Object obj, String methodName, Object requestObj) throws Exception {
        Method method = getTargetMethod(obj, methodName);
        Object[] reqObj = { requestObj };
        if(requestObj == null){
            return method.invoke(obj, null);
        }else{
            return method.invoke(obj, reqObj);
        }

    }
}
