package tpintrospection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

public class PapiBarbu {

    static Vector vaChercherLesCadeaux() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        /**
         * PAS BESOIN DE REGARDER ICI,
         * VUE QUE C'EST LE PAPI BARBU QUI S'OCCUPE D'ALLER CHERCHER LES CADEAUX...
         ***/

        Class jarClass = Class.forName("com.bfa.JarStarter");
        String classToLoad = "tpintrospection.Magasin";

        Constructor constructor = jarClass.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        Object starter = constructor.newInstance(null);

        Method initMethod = jarClass.getDeclaredMethod("init",String.class);
        initMethod.setAccessible(true);
        initMethod.invoke(starter, classToLoad);

        Method getClassLoaderMethod = jarClass.getDeclaredMethod("getClassLoader", null);
        getClassLoaderMethod.setAccessible(true);
        ClassLoader innerClassLoader = (ClassLoader) getClassLoaderMethod.invoke(starter);
        Class<?> cls = innerClassLoader.loadClass(classToLoad);

        Method main = cls.getMethod("getCadeau", null);
        Object cadeau = main.invoke(null, null);

        return (Vector)cadeau;
    }

    // http://www.bfa-it.com/index.php?lang=en&id=products/jarprotector
}
