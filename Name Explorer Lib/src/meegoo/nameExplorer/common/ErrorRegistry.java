package meegoo.nameExplorer.common;

import meegoo.nameExplorer.util.CustomList;

public class ErrorRegistry {

    static CustomList<Class> errorClasses = new CustomList<>();

    public static void registerErrorHandler(Class target) {
        System.out.println("Registered error handler " + target.getName());
        errorClasses.addIfNotPresent(target);
    }

}
