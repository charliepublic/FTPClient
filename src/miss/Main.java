package miss;

import LogicRepository.Beauty;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        Beauty beauty = new Beauty();
        windowShow();

    }

    public static void windowShow() {
        Display disp = new Display();
        disp.setSize(800, 600);
        disp.setTitle("FTP——Client");
        disp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        disp.setLocationRelativeTo(null);
        disp.setVisible(true);
    }
}
