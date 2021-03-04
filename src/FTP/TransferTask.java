
package FTP;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class TransferTask implements Runnable {
	private List<TransferTaskListener> listeners=new ArrayList<TransferTaskListener>();
	private BufferedInputStream in;
	private BufferedOutputStream out;
	private long size;
	
	public TransferTask(InputStream in, OutputStream out, long size){
		this.in=new BufferedInputStream(in);
		this.out=new BufferedOutputStream(out);
		this.size=size;
	}
	
	public long getSize(){
		return this.size;
	}
	
	@Override
	public void run() {
		byte[] buff=new byte[4096];
		int read;
		long totalRead=0;
		
		try {
			read=this.in.read(buff);

			while(read!=-1 && totalRead<this.size+1){
				totalRead+=read;
				notifyTransfered(totalRead);
				this.out.write(buff, 0, read);
				read=this.in.read(buff);
			}
			
			this.in.close();
			this.out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		notifyFinish();
	}

	public void addListener(TransferTaskListener listener){
		this.listeners.add(listener);
	}
	
	public boolean removeListener(TransferTaskListener listener){
		return this.listeners.remove(listener);
	}
	
	protected void notifyTransfered(long transfered){
		for(TransferTaskListener listener : this.listeners) {
			listener.transfered(transfered);
		}
	}
	
	protected void notifyFinish(){
		for(TransferTaskListener listener : this.listeners) listener.finish();
	}
}
