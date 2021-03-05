package Display;

import repository.Language;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ConnectPan extends JPanel {
    private List<ConnectListener> listeners = new ArrayList<>();
    private JTextField login = new JTextField();
    private JPasswordField passwd = new JPasswordField();
    private JFormattedTextField host = new JFormattedTextField();
    private JFormattedTextField port = new JFormattedTextField(DecimalFormat.getIntegerInstance());
    private JButton button;
    private boolean enabled = true;
    private Language lang;

    public ConnectPan() {
        lang = new Language();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(new JLabel(lang.getPhrase(Language.LOGIN)));
        add(this.login);
        add(new JLabel(lang.getPhrase(Language.PASSWORD)));
        add(this.passwd);
        add(new JLabel(lang.getPhrase(Language.HOST)));
        add(this.host);
        add(new JLabel(lang.getPhrase(Language.PORT)));
        add(this.port);

        ListenText lst = new ListenText();
        this.login.addKeyListener(lst);
        this.passwd.addKeyListener(lst);
        this.host.addKeyListener(lst);
        this.port.addKeyListener(lst);

        this.login.setText("123");
        this.passwd.setText("123");
        this.host.setText("192.168.0.66");
        this.port.setValue(21L);

        this.button = new JButton(lang.getPhrase(Language.CONNECT));
        button.setEnabled(true);
        this.button.addMouseListener(new ConnListener());
        add(this.button);

    }

    @Override
    public void setEnabled(boolean enable) {
        this.login.setEnabled(enable);
        this.passwd.setEnabled(enable);
        this.host.setEnabled(enable);
        this.port.setEnabled(enable);
        this.button.setText(
                enable ? lang.getPhrase(Language.CONNECTED) : lang.getPhrase(Language.DISCONNECTED)
        );
        this.enabled = enable;
    }

    public boolean isEnable() {
        return this.enabled;
    }

    public void addListener(ConnectListener listener) {
        this.listeners.add(listener);
    }

    public boolean removeListener(ConnectListener listener) {
        return this.listeners.remove(listener);
    }

    protected void notifyConnect(String login, char[] passwd, String host, long port) {
        for (ConnectListener listener : this.listeners) {
            listener.needConnect(login, passwd, host, port);
        }
    }

    protected void notifyDisconnect() {
        for (ConnectListener listener : this.listeners) {
            listener.needDisconnect();
        }
    }

    private class ListenText implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            ConnectPan th = ConnectPan.this;
            if (th.isEnable()) {
                th.button.setEnabled(!th.login.getText().isEmpty()
                        && th.passwd.getPassword().length != 0
                        && !th.host.getText().isEmpty()
                        && th.port.getValue() != null);
            }

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

    }

    private class ConnListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (ConnectPan.this.isEnable()) {
                ConnectPan.this.notifyConnect(
                        ConnectPan.this.login.getText(),
                        ConnectPan.this.passwd.getPassword(),
                        ConnectPan.this.host.getText(),
                        (Long) ConnectPan.this.port.getValue());
            } else {
                ConnectPan.this.notifyDisconnect();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {


        }

        @Override
        public void mouseExited(MouseEvent e) {


        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

    }
}
