package meegoo.nameExplorer.util;

public class GUIHelper {

    public static String convertToCenteredMultiline(String orig)
    {
        return "<html>" + "<center>" + orig.replaceAll("\n", "<br>") + "</center>";
    }
}
