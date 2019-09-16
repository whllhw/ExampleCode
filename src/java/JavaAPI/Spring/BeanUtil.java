package JavaAPI.Spring;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {
    /**
     * 将Map 转换为 JavaBean
     * 这里直接使用反射获取其所有的字段，从map中得到填值。
     * 这里也用到了模板Class<T>，就是不同类的class对象。T就是对象实例
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> beanClass) throws Exception {
        T obj = beanClass.newInstance();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 注意静态、常量不需要！
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            // 设置访问权限
            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }
        return obj;
    }

    /**
     * 将JavaBean 转换为 Map
     * 比较简单处理，直接生成一个map返回即可
     */
    public static Map<String, Object> beanToMap(Object bean) throws IllegalAccessException, InstantiationException {
        Field[] fields = bean.getClass().getDeclaredFields();
        Map<String, Object> map = new HashMap<>();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            map.put(field.getName(), field.get(bean));
        }
        return map;
    }

    /**
     * 通过内省机制获取Bean对象的方法！
     */
    public static <T> T mapToBeanByPropertyDescriptor(Map<String, Object> map, Class<T> beanClass) throws Exception {
        T obj = beanClass.newInstance();
        // 注意使用Introspector时去除Object类的属性
        BeanInfo beanInfo = Introspector.getBeanInfo(beanClass, Object.class);
        // 获取所有的Java bean字段
        PropertyDescriptor[] pDs = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pDs) {
            // 获得method，直接反射调用即可
            pd.getWriteMethod().invoke(obj,
                    map.get(pd.getName()));
        }
        return obj;
    }

    /**
     * 与上面的类似，可以获取到set方法
     */
    public static Map<String, Object> beanToMapByPropertyDescriptor(Object bean) throws Exception {
        Map<String, Object> map = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
        PropertyDescriptor[] pDs = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pD : pDs) {
            map.put(pD.getName(), pD.getReadMethod().invoke(bean));
        }
        return map;
    }
}
