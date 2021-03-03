
package FTP;
import java.util.Date;

import Display.Constants;


public class FTPFile {
	//private PiFTP ftp;
	protected int owner;
	protected int ownerGroup;
	protected int mode;
	protected String perm;
	protected long size;
	protected String type; //linux-like
	protected String absPath;
	protected Date date;
	boolean exist=false;
	
	
	protected FTPFile(){
		
	}
	
	public int getUnixOwner(){
		return this.owner;
	}
	
	public int getUnixGroup(){
		return this.ownerGroup;
	}
	
	public String getType(){
		return this.type;
	}
	
	public int getMode(){
		return this.mode;
	}
	
	public String getPerm(){
		return this.perm;
	}
	
	public long size(){
		return this.size;
	}
	
	public boolean isDirectory(){
		return this.type.equals("dir") || this.type.equals("cdir") || this.type.equals("pdir");
	}
	
	public boolean exist(){
		return this.exist;
	}
	
	public boolean isSimlik(){
		return false;
	}
	
	public String getPath(){
		int indx=this.absPath.lastIndexOf(Constants.FILE_SEPARATOR);
		return indx==-1 || indx==0 ? "/" : this.absPath.substring(0,  indx);
	}
	
	public String getName(){
		int indx=this.absPath.lastIndexOf(Constants.FILE_SEPARATOR);
		return indx==-1 ? new String(this.absPath) : this.absPath.substring(indx+1, this.absPath.length());
	}
	
	public String getAbsPath(){
		return this.absPath;
	}
	
	public Date getDate(){
		return this.date;
	}
}
