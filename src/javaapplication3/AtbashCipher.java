package sample;

/**
 * Encodes, decodes, and 'breaks' Atbash algorithm
 */
public class AtbashCipher {

    /**
     * Encodes String char by char using Atbash algorithm
     * @param msg String to encode
     * @return String of encoded message
     */
    public static String encode(String msg) {
        msg = msg.toLowerCase();
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < msg.length(); i++)
            if (msg.charAt(i) >= 'a' && msg.charAt(i) <= 'z')
                stringBuilder.append(atbashFlip(msg.charAt(i)));
            else
                stringBuilder.append(msg.charAt(i));
        return stringBuilder.toString();
    }

    public static String decode(String msg) {
        return encode(msg);
    }

    public static String breakCode(String msg) {
        return encode(msg);
    }

    private static char atbashFlip(char c) {
        switch (c) {
            case 'a':
                return 'z';
            case 'b':
                return 'y';
            case 'c':
                return 'x';
            case 'd':
                return 'w';
            case 'e':
                return 'v';
            case 'f':
                return 'u';
            case 'g':
                return 't';
            case 'h':
                return 's';
            case 'i':
                return 'r';
            case 'j':
                return 'q';
            case 'k':
                return 'p';
            case 'l':
                return 'o';
            case 'm':
                return 'n';
            case 'n':
                return 'm';
            case 'o':
                return 'l';
            case 'p':
                return 'k';
            case 'q':
                return 'j';
            case 'r':
                return 'i';
            case 's':
                return 'h';
            case 't':
                return 'g';
            case 'u':
                return 'f';
            case 'v':
                return 'e';
            case 'w':
                return 'd';
            case 'x':
                return 'c';
            case 'y':
                return 'b';
            case 'z':
                return 'a';
        }
        return '0'; //theoretically unreachable
    }
}
