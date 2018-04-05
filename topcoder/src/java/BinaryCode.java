import java.util.Arrays;

public class BinaryCode {
    public BinaryCode() {
    }

    public static String[] decode(String message) {
        String[] plainTextPossibility = new String[]{"NONE", "NONE"};
        if (message.length() >= 1 && message.length() <= 50) {
            int count = 0;
            char[] var6;
            int i = (var6 = message.toCharArray()).length;

            for(int var4 = 0; var4 < i; ++var4) {
                char c = var6[var4];
                if (c == '0' || c == '1' || c == '2' || c == '3') {
                    ++count;
                }
            }

            if (count == message.length()) {
                String[] cipherTextString = message.split("");
                Integer[] cipherTextInt = new Integer[cipherTextString.length];

                for(i = 0; i < cipherTextString.length; ++i) {
                    cipherTextInt[i] = Integer.parseInt(cipherTextString[i]);
                }

                int[] plainTextInt = new int[cipherTextInt.length];
                System.out.println(Arrays.toString(plainTextInt));
                System.out.println(Arrays.toString(cipherTextInt));
                boolean tag = true;
                if (cipherTextInt.length == 1 && cipherTextInt[0] != 1 && cipherTextInt[0] != 0) {
                    tag = false;
                }

                for(i = 1; i < plainTextInt.length; ++i) {
                    if (i == 1) {
                        plainTextInt[1] = cipherTextInt[0];
                        if (plainTextInt[1] != 0 && plainTextInt[1] != 1) {
                            tag = false;
                        }
                    } else if (i == plainTextInt.length - 1) {
                        plainTextInt[i] = cipherTextInt[i - 1] - plainTextInt[i - 1];
                        if (plainTextInt[i] != 0 && plainTextInt[i] != 1) {
                            tag = false;
                        }
                    } else {
                        plainTextInt[i] = cipherTextInt[i - 1] - plainTextInt[i - 2] - plainTextInt[i - 1];
                        if (plainTextInt[i] != 0 && plainTextInt[i] != 1) {
                            tag = false;
                        }
                    }
                }

                plainTextPossibility[0] = Arrays.toString(plainTextInt).replace(", ", "");
                plainTextPossibility[0] = plainTextPossibility[0].substring(1, plainTextInt.length + 1);
                if (!tag) {
                    plainTextPossibility[0] = "NONE";
                }

                System.out.println("==========the answer======");
                System.out.println(Arrays.toString(plainTextInt));
            }
        }

        return plainTextPossibility;
    }

    public static void main(String[] args) {
        String encryption = "123210120";
        String[] plainText = decode(encryption);
        System.out.println("{ \"" + plainText[0] + "\", \"" + plainText[1] + "\" }");
    }
}
