package all_interface;

public interface ConnectListener {
    void needConnect(String login, char[] passwd, String host, long port);

    void needDisconnect();
}
