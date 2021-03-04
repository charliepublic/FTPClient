
package Display;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import FTP.PiFTPListener;

public class LogFTP extends JPanel implements PiFTPListener{
//	private static final long serialVersionUID = 1719675270912008567L;

	private JTextArea text=new JTextArea();
	private JScrollPane scroll=new JScrollPane(this.text);

	public LogFTP() {
		setLayout(new BorderLayout());
		add(this.scroll);
	}
	
	@Override
	public void receiveMsg(String msg) {
		this.text.append("recv: "+msg+"\n");
		
	}

	@Override
	public void sendMsg(String msg) {
		this.text.append("send: "+msg+"\n");
		
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
