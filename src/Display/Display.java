package Display;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import FTP.FTPFile;
import FTP.PiFTP;
import FTP.TransferTask;
import repository.ShowProgress;

public class Display extends JFrame {
    private PiFTP pi = new PiFTP();
    private Socket sock;
    private ConnectPan conn = new ConnectPan();
    private LogFTP log = new LogFTP();
    private FileExplorerLocal expLocal = new FileExplorerLocal();
    private FileExplorerFTP expFTP = new FileExplorerFTP();
    private ShowProgress progress = new ShowProgress();
    private JScrollPane scLocal = new JScrollPane(expLocal);
    private JScrollPane scFTP = new JScrollPane(expFTP);
    private JPanel panTree = new JPanel();

    public Display() {
        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        setContentPane(pan);

        this.conn.setPreferredSize(new Dimension(800, 30));
        pan.add(this.conn);

        this.pi.addLisener(this.log);
        this.log.setPreferredSize(new Dimension(800, 150));
        add(this.log);

        JPanel panTree = new JPanel();
        panTree.setLayout(new BoxLayout(panTree, BoxLayout.X_AXIS));

        scLocal.setPreferredSize(new Dimension(400, 560));
        scFTP.setPreferredSize(new Dimension(400, 560));
        panTree.add(scLocal);
        panTree.add(scFTP);
        pan.add(panTree);

        JScrollPane scProgress = new JScrollPane(this.progress);
        scProgress.setPreferredSize(new Dimension(800, 100));
        pan.add(scProgress);

        this.conn.addListener(new ListenConnect());
        this.expLocal.addListener(new ListenExpLocal());
        this.expFTP.addListener(new ListenExpFTP());

        this.expLocal.setPath("D:\\data");
    }

    private class ListenExpLocal implements FileExplorerListener {

        @Override
        public void selectedFile(String path) {
            if (!Display.this.pi.isConnected()) {
                return;
            }
            File file = new File(path);

            try {
                TransferTask trf = new TransferTask(
                        new FileInputStream(file),
                        Display.this.pi.upload(Display.this.expFTP.getCurrentPath() + "/" + file.getName()),
                        file.length());

                Display.this.progress.addTransferTask(trf);

                Thread th = new Thread(trf);
                th.start();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            expLocal.setPath(Display.this.expLocal.getCurrentPath());
            expFTP.setPath(Display.this.expFTP.getCurrentPath());
            panTree.updateUI();
        }
    }

    private class ListenExpFTP implements FileExplorerListener {

        @Override
        public void selectedFile(String path) {
            if (!Display.this.pi.isConnected()) {
                return;
            }

            FTPFile fileR = Display.this.pi.getFile(path);
            if (fileR == null) {
                return;
            }
            File fileL = new File(Display.this.expLocal.getCurrentPath() + "/" + fileR.getName());
            try {
                TransferTask trf = new TransferTask(
                        Display.this.pi.download(fileR),
                        new FileOutputStream(fileL),
                        fileR.size());

                Display.this.progress.addTransferTask(trf);

                Thread th = new Thread(trf);
                th.start();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            expLocal.setPath(Display.this.expLocal.getCurrentPath());
            expFTP.setPath(Display.this.expFTP.getCurrentPath());
            panTree.updateUI();
        }
    }

    private class ListenConnect implements ConnectListener {

        @Override
        public void needConnect(String login, char[] passwd, String host, long port) {
            BufferedReader read;
            try {
                Socket socket = new Socket(host, (int) port);

                read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                read.readLine(); //To clear the reply of welcome..., but we should find another way.

                Display.this.pi.setInputStream(socket.getInputStream());
                Display.this.pi.setOutputStream(socket.getOutputStream());

                if (Display.this.pi.connect(login, new String(passwd))) {
                    Display.this.conn.setEnabled(false);
                    Display.this.sock = socket;
                    Display.this.expFTP.setPiFTP(pi);
                    Display.this.expFTP.setPath("/");
                } else {
                    Display.this.conn.setEnabled(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void needDisconnect() {
            Display.this.expFTP.setPiFTP(null);
            Display.this.expFTP.clear();

            if (Display.this.sock != null) {
                try {
                    Display.this.sock.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Display.this.sock = null;
            }

            Display.this.conn.setEnabled(true);
            Display.this.pi.disconnect();
        }

    }
}
