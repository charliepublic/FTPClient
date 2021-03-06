package LogicRepository;

import java.util.Date;

/**
 * @author wangy
 */
public class FTPFile {
    protected int owner;
    protected int ownerGroup;
    protected int mode;
    protected String perm;
    protected long size;
    protected String type;
    protected String absPath;
    protected Date date;
    boolean exist = false;


    public FTPFile() {

    }
    public void setOwner(int owner) {
        this.owner = owner;
    }

    public void setOwnerGroup(int ownerGroup) {
        this.ownerGroup = ownerGroup;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAbsPath(String absPath) {
        this.absPath = absPath;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
    public int getUnixOwner() {
        return this.owner;
    }

    public int getUnixGroup() {
        return this.ownerGroup;
    }

    public String getType() {
        return this.type;
    }

    public int getMode() {
        return this.mode;
    }

    public String getPerm() {
        return this.perm;
    }

    public long size() {
        return this.size;
    }

    public boolean isDirectory() {
        return this.type.equals("dir") || this.type.equals("cdir") || this.type.equals("pdir");
    }

    public boolean exist() {
        return this.exist;
    }

    public boolean isSimlik() {
        return false;
    }

    public String getPath() {
        int indx = this.absPath.lastIndexOf("/");
        return indx == -1 || indx == 0 ? "/" : this.absPath.substring(0, indx);
    }

    public String getName() {
        int indx = this.absPath.lastIndexOf("/");
        return indx == -1 ? this.absPath : this.absPath.substring(indx + 1);
    }

    public String getAbsPath() {
        return this.absPath;
    }

    public Date getDate() {
        return this.date;
    }
}
