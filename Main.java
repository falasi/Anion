import java.util.Base64;
import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your data:");
        String inputData = scanner.nextLine();

        if (isBase64Encoded(inputData)) {
            byte[] decodedBytes = Base64.getDecoder().decode(inputData);
            String decodedString = new String(decodedBytes);
            System.out.println("Base64 Decoded value: " + decodedString);

            if (isUrlEncoded(decodedString)) {
                try {
                    String urlDecodedString = URLDecoder.decode(decodedString, "UTF-8");
                    System.out.println("URL Decoded value: " + urlDecodedString);
                } catch (UnsupportedEncodingException e) {
                    System.out.println("Error while URL decoding!");
                }
            }
        } else if (isUrlEncoded(inputData)) {
            try {
                String urlDecodedString = URLDecoder.decode(inputData, "UTF-8");
                System.out.println("URL Decoded value: " + urlDecodedString);

                if (isBase64Encoded(urlDecodedString)) {
                    byte[] decodedBytes = Base64.getDecoder().decode(urlDecodedString);
                    String base64DecodedString = new String(decodedBytes);
                    System.out.println("Base64 Decoded value: " + base64DecodedString);
                }
            } catch (UnsupportedEncodingException e) {
                System.out.println("Error while URL decoding!");
            }
        } else {
            System.out.println("Input doesn't seem to be Base64 or URL encoded.");
        }
    }

    public static boolean isBase64Encoded(String s) {
        try {
            Base64.getDecoder().decode(s);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isUrlEncoded(String s) {
        if (s.equals(URLDecoder.decode(s, java.nio.charset.StandardCharsets.UTF_8))) {
            return false;
        }
        return true;
    }
}
