package tpintrospection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ReflectPermission;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        try {
            Vector cadeaux = PapiBarbu.vaChercherLesCadeaux();
            System.out.println("Ouh le gros cadeau que voilà ! Voyons voir ce qu'il y a dedans...\n ");

            for (Object cadeau: cadeaux)
                introspect(cadeau);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void introspect(Object o) {

        System.out.println("------------------- Question 1 ----------------------------");
        System.out.println(o.toString());

        System.out.println("------------------- Question 5  ----------------------------");

        Class testClass = ReflectPermission.class;

        Method[] methods = testClass.getMethods();
        for (Method method : methods) {
            System.out.println("The methods --> "+method.getName());
        }

        System.out.println("------------------- Question 6  ----------------------------");

        System.out.println(o.getClass().getFields());
        System.out.println(o.getClass().getDeclaredFields());

        Field[] fields = o.getClass().getFields();
        for (Field field : fields) {
            System.out.println("The field --> "+field.getName());
            System.out.println("The field --> "+field.toString());
        }

        System.out.println("------------------- Display Constructor ----------------------------");

        Constructor[] constructors = testClass.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("The constructor --> "+constructor.getName());
        }

        System.out.println("------------------- Question 10  ----------------------------");

        System.out.println("------------------- Question 11  ----------------------------");

        System.out.println("------------------- Question 12  ----------------------------");


    }


}
