package util;

public class ParameterValidator {
    private ParameterValidator() { }

    public static boolean isInteger(String str) {
        if (str == null)
            return false;

        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
