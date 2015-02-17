package meegoo.nameExplorer.gui;

import meegoo.nameExplorer.common.ErrorRegistry;

public class Main {

    public static void main(String[] args) {
        ErrorRegistry.registerErrorHandler(meegoo.nameExplorer.gui.GUIError.class);
        GUI.initialize();
    }
}
