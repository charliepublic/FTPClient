package UIRepository;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import all_interface.PiFTPListener;

public class LogFTP extends JPanel implements PiFTPListener {
    private final JTextArea text = new JTextArea();

    public LogFTP() {
        setLayout(new BorderLayout());
        JScrollPane scroll = new JScrollPane(this.text);
        add(scroll);
    }

    @Override
    public void receiveMsg(String msg) {
        this.text.append("recv: " + msg + "\n");

    }

    @Override
    public void sendMsg(String msg) {
        this.text.append("send: " + msg + "\n");

    }

    @Override
    public void connected() {
        this.text.append("#### Connected!\n");

    }

    @Override
    public void disconnected() {
        this.text.append("#### Disconnected!\n");

    }


}
