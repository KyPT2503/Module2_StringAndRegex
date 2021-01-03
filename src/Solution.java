import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    static final Pattern EMAIL_REGEX = Pattern.compile("[A-Za-z0-9]+@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$");
    static final Pattern ACCOUNT_REGEX = Pattern.compile("^[a-z0-9_]{6,}");
    static final Pattern CLASSNAME_REGEX = Pattern.compile("[A-Z][0-9]{4}[GHIKLM]");
    static final Pattern PHONE_NUMBER_REGEX = Pattern.compile("[(][0-9]{2}[)]-[(]0[0-9]{9}[)]");
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayNews();
    }

    private static void getAndCheckEmail() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println(isValidEmail(email) ? "Your email is Valid." : "Your email is Invalid.");
    }

    private static boolean isValidEmail(String email) {
        return EMAIL_REGEX.matcher(email).matches();
    }

    private static boolean isValidAccount(String account) {
        return ACCOUNT_REGEX.matcher(account).matches();
    }

    private static void getAndCheckAccount() {
        System.out.print("Enter your account: ");
        String account = scanner.nextLine();
        System.out.println(isValidAccount(account) ? "Account is valid." : "Account is Invalid.");
    }

    private static void displaySongListOfUrl() {
        try {
            System.out.print("Enter URL: ");
            URL url = new URL(scanner.next());
            Scanner urlScanner = new Scanner(new InputStreamReader(url.openStream()));
            urlScanner.useDelimiter("\\Z");
            String content = urlScanner.next();
            content.replaceAll("\\n+", "");
            Pattern songPattern = Pattern.compile("name_song\"(.*?)\" >(.*?)</a>");
            Matcher matcher = songPattern.matcher(content);
            while (matcher.find()) {
                System.out.println(matcher.group(2));
            }
            urlScanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static boolean isValidClassName(String className) {
        return CLASSNAME_REGEX.matcher(className).matches();
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        return PHONE_NUMBER_REGEX.matcher(phoneNumber).matches();
    }

    private static void displayNews() {
        try {
            Scanner urlScanner = new Scanner(new InputStreamReader(new URL("https://dantri.com.vn/").openStream()));
            urlScanner.useDelimiter("\\Z");
            String content = urlScanner.next();
            content.replaceAll("\\n+", "");
            Pattern newsPattern = Pattern.compile("class=\"news-item__sapo\"(.*?)[(]Dân trí[)]&nbsp;- (.*?)</a>");
            Matcher matcher = newsPattern.matcher(content);
            while (matcher.find()) {
                System.out.println(matcher.group(2));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
