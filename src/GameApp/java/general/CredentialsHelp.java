package GameApp.java.general;

public class CredentialsHelp {
    private static String username = "dan";
    private static String password = "dan";

    public static boolean checkCredentials(String user, String pass){
        return user.equals(username) && pass.equals(password);
    }
}
