package util;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ResourceBundle;

public class AttachmentControl {
    private static Logger LOGGER = Logger.getRootLogger();
    private String ACCESS_TOKEN;
    private String ROOT;
    private String LOCALE;
    private DbxClientV2 client;
    private ResourceBundle bundle;

    public AttachmentControl() {
        bundle = ResourceBundle.getBundle("dropbox");
        ACCESS_TOKEN = bundle.getString("ACCESS_TOKEN");
        ROOT = bundle.getString("ROOT");
        LOCALE = bundle.getString("LOCALE");
        DbxRequestConfig config = DbxRequestConfig.newBuilder(ROOT).build();
        client = new DbxClientV2(config, ACCESS_TOKEN);
    }

    public void uploadFile(String path, InputStream in) {
        try {
            client.files().uploadBuilder(path).uploadAndFinish(in);
        } catch (FileNotFoundException fne) {
            LOGGER.error("File Not Found Exception in Upload File: " + fne);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (DbxException dbxe) {
            dbxe.printStackTrace();
        }
    }

    public void downloadFile(String path, OutputStream downloadFile) {
        try {
            try {
                client.files().downloadBuilder(path).download(downloadFile);
            } finally {
                downloadFile.close();
            }
        } catch (DbxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(String path) {
        try {
//            client.files().delete(path);
            client.files().deleteV2(path);
        } catch (DbxException dbxe) {
            dbxe.printStackTrace();
        }
    }

    public boolean isExists(String path) {
        try {
            Long size = client.files().downloadBuilder(path).start().getResult().getSize();
            if (size > 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public long getFileSize(String path) {
        try {
            Long size = client.files().downloadBuilder(path).start().getResult().getSize();
            return size;

        } catch (Exception e) {
        }

        return 0;
    }
}
