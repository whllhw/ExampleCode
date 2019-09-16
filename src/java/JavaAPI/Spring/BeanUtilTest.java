package JavaAPI.Spring;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class BeanUtilTest {

    @org.junit.jupiter.api.Test
    void mapToBean() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("str", "S1");
        TestA t = BeanUtil.mapToBean(map, TestA.class);
        System.out.println(t);
    }

    @Test
    void beanToMap() throws Exception {
        TestA t = new TestA();
        t.setStr("s2");
        System.out.println(BeanUtil.beanToMap(t));
    }

    @Test
    void beanToMapByPropertyDescriptor() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("str", "S1");
        TestA t = BeanUtil.mapToBeanByPropertyDescriptor(map, TestA.class);
        System.out.println(t);
    }

    @Test
    void mapToBeanByPropertyDescriptor() throws Exception {
        TestA t = new TestA();
        t.setStr("s2");
        System.out.println(BeanUtil.beanToMapByPropertyDescriptor(t));
    }
}

class TestA {
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "TestA{" +
                "str='" + str + '\'' +
                '}';
    }
}