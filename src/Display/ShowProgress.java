/*//////////////////////////////////////////////////////////////////////
	This file is part of FTP, an client FTP.
	Copyright (C) 2013  Nicolas Barranger <wicowyn@gmail.com>

    FTP is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FTP is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with FTP.  If not, see <http://www.gnu.org/licenses/>.
*///////////////////////////////////////////////////////////////////////

package Display;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import FTP.TransferTask;
import FTP.TransferTaskListener;

public class ShowProgress extends JPanel {
	private static final long serialVersionUID = -5585045453142379373L;

	public ShowProgress(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public void addTransferTask(TransferTask task){
		JProgressBar bar=new JProgressBar();
		bar.setMaximum((int) task.getSize());
		add(bar);
		
		ListenTask listener=new ListenTask();
		listener.bar=bar;
		task.addListener(listener);
		
		updateUI();
	}
	
	private class ListenTask implements TransferTaskListener{
		public JProgressBar bar;
		
		@Override
		public void transfered(long transfered) {
			this.bar.setValue((int) transfered);
			
		}

		@Override
		public void finish() {
			ShowProgress.this.remove(this.bar);
			this.bar=null;
			ShowProgress.this.updateUI();
			
		}
		
	}
}
