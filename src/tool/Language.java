package tool;

public class Language {
    public static final int LOGIN = 1;
    public static final int PASSWORD = 2;
    public static final int HOST = 3;
    public static final int PORT = 4;
    public static final int CONNECT = 5;
    public static final int CONNECTED = 6;
    public static final int DISCONNECTED = 7;
    public static final int DELETE = 8;
    public static final int INFO = 9;
    public static final int MOVE = 10;
    public static final int NEWFOLDER = 11;
    public static final int INFO_DIR = 12;
    public static final int INFO_NAME = 13;
    public static final int INFO_TYPE = 14;
    public static final int INFO_SIZE = 19;
    public static final int INFO_DATE = 15;
    public static final int INFO_PERM = 16;
    public static final int INFO_OWNER = 17;
    public static final int INFO_GROUP = 18;

    String lang;
    String[] langEn = {"ENGLISH", "User: ", "Password: ", "Host: ", "Port: ", "Connect", "Connected", "Disconnected",
            "Delete", "Info", "Move", "New Folder", "Directory: ", "Name: ", "Type: ", "Date: ", "Permissions: ",
            "Owner: ", "Group: ","Size"};


    public Language() {
        lang = System.getProperty("user.language");
    }

    public String getPhrase(int phrase) {
        return langEn[phrase];

    }

}
