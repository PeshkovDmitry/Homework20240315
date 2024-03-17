import Annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.AccessFlag;

public class TestRunner {

    public static void run(Class<?> testClass) {
        final Object testObj = initTestObj(testClass);
        for (Method testMethod : testClass.getDeclaredMethods()) {
            if (testMethod.accessFlags().contains(AccessFlag.PRIVATE)) {
                continue;
            }

            if (testMethod.getAnnotation(Test.class) != null) {
                try {
                    testMethod.invoke(testObj);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static Object initTestObj(Class<?> testClass) {
        try {
            Constructor<?> noArgsConstructor = testClass.getConstructor();
            return noArgsConstructor.newInstance();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Нет конструктора по умолчанию");
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Не удалось создать объект тест класса");
        }
    }

}
