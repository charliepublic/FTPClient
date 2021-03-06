package UIRepository;

import java.util.List;

import LogicRepository.FTPFile;
import mian_part.PiFTP;
import LogicRepository.ShowInfo;

public class FileExplorerFTP extends FileExplorer {
    private PiFTP pi = null;

    public FileExplorerFTP() {

    }

    @Override
    public void setPath(String path) {
        this.path = path;
        this.model.clear();
        if (this.pi == null) {
            return;
        }

        List<FTPFile> files = this.pi.getFiles(path);
        for (FTPFile file : files) {
            this.model.addElement(file.getName());
        }

        updateUI();
    }

    public void setPiFTP(PiFTP pi) {
        this.pi = pi;
        this.model.clear();
    }

    public PiFTP getPiFTP() {
        return this.pi;
    }

    @Override
    protected void selected(int index) {
        String path = this.path + "/" + this.model.get(index);
        FTPFile file = this.pi.getFile(path);

        if (file.isDirectory()) {
            setPath(file.getAbsPath());
        } else {
            notifySelectedFile(path);
        }

    }

    @Override
    protected void delete(int index) {
        String name = this.model.get(index);

        if (!name.equals("..")) {
            this.pi.delete(this.pi.getFile(this.path + "/" + this.model.get(index)));
            setPath(this.path);
        }

    }

    @Override
    protected void move(int index, String newAbsPath) {
        String name = this.model.get(index);

        if (!name.equals("..")) {
            this.pi.move(this.pi.getFile(this.path + "/" + this.model.get(index)), newAbsPath);
            setPath(this.path);
        }

    }

    @Override
    protected void info(int index) {
        FTPFile file = this.pi.getFile(this.path + "/" + this.model.get(index));
        ShowInfo inf = new ShowInfo();

        inf.setDir(file.getPath() == null? file.getPath(): " ");
        inf.setName(file.getName()== null? file.getName(): " ");
        inf.setTypeFile(file.getType() == null? file.getType(): " ");
        inf.setSize(file.size());
        inf.setDate(file.getDate());
        inf.setPerm(file.getPerm());
        inf.setUnixOwner(Integer.toString(file.getUnixOwner()));
        inf.setUnixGroup(Integer.toString(file.getUnixGroup()));

        inf.setVisible(true);
        inf.setLocationRelativeTo(this);
    }

}
