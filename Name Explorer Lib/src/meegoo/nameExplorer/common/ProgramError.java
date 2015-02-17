package meegoo.nameExplorer.common;

import java.lang.reflect.InvocationTargetException;

public class ProgramError {
    public ProgramError(String message) {
        System.out.println("Catched an error " + message);
        for (Class errorClass : ErrorRegistry.errorClasses) {
            System.out.println("Sent error to " + errorClass.getName());
            try {
                errorClass.getConstructor(String.class).newInstance(message);
            } catch (NoSuchMethodException e) {
                System.out.println("Your error constructor has to have only one String argument");
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
}
