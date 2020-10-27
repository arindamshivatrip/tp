package seedu.commons;

public class Util {

    public static String generatePadStringWithCharAndLength(char pad, int length) {
        if (length == 0) {
            return "";
        } else {
            return String.format("%" + length + "s", "").replace(' ', pad);
        }
    }

    public static String limitStringWithDots(String string, int limit) {
        // Could be changed to -4 to get an extra space
        if (limit <= 0) {
            return "";
        } else if (limit < 3) {
            return string.substring(0, limit);
        }
        return (string.length() > limit) ? (string.substring(0, limit - 3) + "...") : string;
    }

    public static void putsIntoArray(String string, char[] arr, int start) {
        string.getChars(0, string.length(), arr, start);
    }

    public static void putsIntoArrayWithCentralise(String string, char[] arr, int start, int end) {
        // assert within range
        assert (end - start) > string.length();
        int dstBegin = start + (end - start - string.length()) / 2;
        string.getChars(0, string.length(), arr, dstBegin);
    }
}
