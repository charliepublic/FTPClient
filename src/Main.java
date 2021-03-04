import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Display.Display;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		}catch (Exception e){
			return;
		}
		//调用BeautyEyeLNF系统皮肤

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		windowShow();
		
	}

	public static void windowShow(){
		Display disp=new Display();
		disp.setSize(800, 600);
		disp.setTitle("FTP——Client");
		disp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		disp.setLocationRelativeTo(null);
		disp.setVisible(true);
	}
}
