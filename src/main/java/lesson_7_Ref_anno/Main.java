package lesson_7_Ref_anno;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws Exception {
        Class aClass = Tests.class;
        Object testObj = aClass.newInstance();
        ArrayList<Method> arrayList = new ArrayList<>();
        Method beforeMethod = null;
        Method afterMethod = null;
        for (Method a : aClass.getDeclaredMethods()) {
            if (a.isAnnotationPresent(Test.class)) {
                arrayList.add(a);
            }
            if (a.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeMethod == null) beforeMethod = a;
                else throw new RuntimeException("Больше одного метода с аннотацией lesson_7_Ref_anno.BeforeSuite");
            }
            if (a.isAnnotationPresent(AfterSuite.class)) {
                if (afterMethod == null) afterMethod = a;
                else throw new RuntimeException("Больше одного метода с аннотацией lesson_7_Ref_anno.AfterSuite");
            }
            arrayList.sort(new Comparator<Method>() {
                @Override
                public int compare(Method o1, Method o2) {
                    return o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority();
                }
            });
        }
        //beforeMethod
        if (beforeMethod != null) beforeMethod.invoke(testObj, null);
        //AllMethod
        for (int i = 0; i < arrayList.size(); i++) {
            Method o = arrayList.get(i);
            o.invoke(testObj, null);
        }
        //afterMethod
        if (afterMethod != null) afterMethod.invoke(testObj, null);
    }
}
