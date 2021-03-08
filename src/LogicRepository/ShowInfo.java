package LogicRepository;

import all_interface.Invoke;
import tool.Language;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowInfo extends JDialog implements Invoke {

    private final JLabel name = new JLabel();
    private final JLabel path = new JLabel();
    private final JLabel size = new JLabel();
    private final JLabel owner = new JLabel();
    private final JLabel group = new JLabel();
    private final JLabel type = new JLabel();
    private final JLabel date = new JLabel();
    private final JLabel perm = new JLabel();

    public ShowInfo() {
        Language lang = new Language();

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        left.add(new JLabel(lang.getPhrase(Language.INFO_DIR)));
        left.add(new JLabel(lang.getPhrase(Language.INFO_NAME)));
        left.add(new JLabel(lang.getPhrase(Language.INFO_TYPE)));
        left.add(new JLabel(lang.getPhrase(Language.INFO_SIZE)));
        left.add(new JLabel(lang.getPhrase(Language.INFO_DATE)));
        left.add(new JLabel(lang.getPhrase(Language.INFO_PERM)));
        left.add(new JLabel(lang.getPhrase(Language.INFO_OWNER)));
        left.add(new JLabel(lang.getPhrase(Language.INFO_GROUP)));

        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        right.add(this.path);
        right.add(this.name);
        right.add(this.type);
        right.add(this.size);
        right.add(this.date);
        right.add(this.perm);
        right.add(this.owner);
        right.add(this.group);

        left.setAlignmentY(Component.TOP_ALIGNMENT);
        right.setAlignmentY(Component.TOP_ALIGNMENT);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        add(left);
        add(right);

        setSize(600, 180);
    }

    public void setDir(String dir) {
        this.path.setText(dir);
    }

    @Override
    public void setName(String name) {
        this.name.setText(name);
    }

    public void setTypeFile(String type) {
        this.type.setText(type);
    }

    public void setSize(long size) {
        this.size.setText(Long.toString(size));
    }

    public void setDate(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        this.date.setText(df.format(date));
    }

    public void setPerm(String perm) {
        this.perm.setText(perm);
    }

    public void setUnixOwner(String owner) {
        this.owner.setText(owner);
    }

    public void setUnixGroup(String group) {
        this.group.setText(group);
    }

    @Override
    public Object invoke_function(Object object, ArrayList<Invoke> components) {
        Language lang = new Language();

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        left.add(new JLabel(lang.getPhrase(Language.INFO_DIR)));
        left.add(new JLabel(lang.getPhrase(Language.INFO_NAME)));
        left.add(new JLabel(lang.getPhrase(Language.INFO_TYPE)));
        left.add(new JLabel(lang.getPhrase(Language.INFO_SIZE)));
        left.add(new JLabel(lang.getPhrase(Language.INFO_DATE)));
        left.add(new JLabel(lang.getPhrase(Language.INFO_PERM)));
        left.add(new JLabel(lang.getPhrase(Language.INFO_OWNER)));
        left.add(new JLabel(lang.getPhrase(Language.INFO_GROUP)));

        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        right.add(this.path);
        right.add(this.name);
        right.add(this.type);
        right.add(this.size);
        right.add(this.date);
        right.add(this.perm);
        right.add(this.owner);
        right.add(this.group);

        left.setAlignmentY(Component.TOP_ALIGNMENT);
        right.setAlignmentY(Component.TOP_ALIGNMENT);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        add(left);
        add(right);

        setSize(600, 180);
        return null;
    }
}
