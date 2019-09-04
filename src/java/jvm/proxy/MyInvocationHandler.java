package jvm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Logger;

interface Subject {
    int getField();

    void setField(int field);
}

public class MyInvocationHandler implements InvocationHandler {
    private final Logger logger = Logger.getLogger(MyInvocationHandler.class.getName());
    private Subject realSubject;

    private MyInvocationHandler(Subject realSubject) {
        this.realSubject = realSubject;
    }

    public static void main(String[] args) {
        Subject realSubject = new RealSubject();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(realSubject);
        Subject proxy = (Subject) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{Subject.class}, myInvocationHandler);
        proxy.setField(100);
        System.out.println(proxy.getField());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("start...");
        if ("getField".equals(method.getName())) {
            logger.info("getter");
            return method.invoke(realSubject, args);
        } else {
            logger.info("setter");
            method.invoke(realSubject, args);
            return null;
        }
    }
}

class RealSubject implements Subject {

    private int field;

    @Override
    public int getField() {
        return field;
    }

    @Override
    public void setField(int field) {
        this.field = field;
    }
}