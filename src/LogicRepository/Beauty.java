package LogicRepository;

import all_interface.Invoke;

import javax.swing.*;
import java.util.ArrayList;

public class Beauty implements Invoke {

    public Beauty(){

    }

    @Override
    public Object invoke_function(Object object, ArrayList<Invoke> components) {
        try {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            return null;
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        return null;
    }
}
