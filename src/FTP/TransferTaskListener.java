package FTP;

public interface TransferTaskListener {
    public void transfered(long transfered);

    public void finish();
}
