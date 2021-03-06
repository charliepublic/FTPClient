package UIRepository;

import all_interface.FileExplorerListener;
import tool.Language;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public abstract class FileExplorer extends JPanel {
    private List<FileExplorerListener> listeners = new ArrayList<>();
    protected DefaultListModel<String> model = new DefaultListModel<>();
    protected JList<String> list = new JList<>(this.model);
    protected String path = "D:\\Code_Libaray\\graduate";
    protected JPopupMenu menu = new JPopupMenu();
    private int indxPopMenu = -1;
    private Language lang;

    public FileExplorer() {
        lang = new Language();
        setLayout(new BorderLayout());
        this.list.addMouseListener(new ListenMouse());
        this.add(this.list);

        JMenuItem itemSuppr = new JMenuItem(lang.getPhrase(Language.DELETE));
        itemSuppr.addActionListener(arg0 -> delete(FileExplorer.this.indxPopMenu));
        this.menu.add(itemSuppr);

        JMenuItem itemInfo = new JMenuItem(lang.getPhrase(Language.INFO));
        itemInfo.addActionListener(arg0 -> info(FileExplorer.this.indxPopMenu));
        this.menu.add(itemInfo);

        JMenuItem itemRename = new JMenuItem(lang.getPhrase(Language.MOVE));
        itemRename.addActionListener(arg0 -> {
            String actionPath = JOptionPane.showInputDialog(lang.getPhrase(Language.NEWFOLDER),
                    FileExplorer.this.path + "/" + FileExplorer.this.model.get(FileExplorer.this.indxPopMenu));
            if (actionPath != null) {
                move(FileExplorer.this.indxPopMenu, actionPath);
            }

        });
        this.menu.add(itemRename);
    }

    public String getCurrentPath() {
        return this.path;
    }

    public void clear() {
        this.model.clear();
    }

    public abstract void setPath(String path);

    protected abstract void selected(int index);

    protected abstract void move(int index, String newAbsPath);

    protected abstract void delete(int index);

    protected abstract void info(int index);

    public void addListener(FileExplorerListener listener) {
        this.listeners.add(listener);
    }

    public boolean removeListener(FileExplorerListener listener) {
        return this.listeners.remove(listener);
    }

    protected void notifySelectedFile(String path) {
        for (FileExplorerListener listener : this.listeners) {
            listener.selectedFile(path);
        }
    }

    private class ListenMouse implements MouseListener {
        private int lastIndex = -1;
        private int nbClick = -1;

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                int index = FileExplorer.this.list.locationToIndex(e.getPoint());
                if (index != this.lastIndex) {
                    this.nbClick = 0;
                }
                this.lastIndex = index;
                this.nbClick++;
                if (this.nbClick == 2) {
                    this.nbClick = 0;
                    FileExplorer.this.selected(index);
                }
            } else {
                this.lastIndex = -1;
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
            if (e.isPopupTrigger()) {
                FileExplorer.this.indxPopMenu = FileExplorer.this.list.locationToIndex(e.getPoint());
                FileExplorer.this.menu.show(e.getComponent(), e.getX(), e.getY());
            }
        }

    }
}
