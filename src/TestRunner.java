import Annotations.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestRunner {

    public static void run(Class<?> testClass) {

        final Object testObj = initTestObj(testClass);
        List<Method> testMethods = new ArrayList<>();
        List<Method> beforeAllMethods = new ArrayList<>();
        List<Method> beforeEachMethods = new ArrayList<>();
        List<Method> afterAllMethods = new ArrayList<>();
        List<Method> afterEachMethods = new ArrayList<>();

        for (Method method : testClass.getDeclaredMethods()) {
            if (method.getModifiers() == Modifier.PUBLIC) {
                if (method.getAnnotation(Test.class) != null) {
                    testMethods.add(method);
                } else if (method.getAnnotation(BeforeAll.class) != null) {
                    beforeAllMethods.add(method);
                } else if (method.getAnnotation(BeforeEach.class) != null) {
                    beforeEachMethods.add(method);
                } else if (method.getAnnotation(AfterAll.class) != null) {
                    afterAllMethods.add(method);
                } else if (method.getAnnotation(AfterEach.class) != null) {
                    afterEachMethods.add(method);
                }
            }
        }

        testMethods.sort(Comparator.comparingInt(m -> m.getAnnotation(Test.class).order()));

        try {
            for (Method method : beforeAllMethods) {
                method.invoke(testObj);
            }
            for (Method testMethod : testMethods) {
                for (Method method : beforeEachMethods) {
                    method.invoke(testObj);
                }
                testMethod.invoke(testObj);
                for (Method method : afterEachMethods) {
                    method.invoke(testObj);
                }
            }
            for (Method method : afterAllMethods) {
                method.invoke(testObj);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
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
