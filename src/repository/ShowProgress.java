package repository;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import FTP.TransferTask;
import FTP.TransferTaskListener;

public class ShowProgress extends JPanel {
    public ShowProgress() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addTransferTask(TransferTask task) {
        JProgressBar bar = new JProgressBar();
        bar.setMaximum((int) task.getSize());
        add(bar);

        ListenTask listener = new ListenTask();
        listener.bar = bar;
        task.addListener(listener);

        updateUI();
    }

    private class ListenTask implements TransferTaskListener {
        public JProgressBar bar;

        @Override
        public void transfered(long transfered) {
            this.bar.setValue((int) transfered);

        }

        @Override
        public void finish() {
            ShowProgress.this.remove(this.bar);
            this.bar = null;
            ShowProgress.this.updateUI();

        }

    }
}
