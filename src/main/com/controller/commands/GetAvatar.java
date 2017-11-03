package controller.commands;

import controller.ActionCommand;
import util.AttachmentControl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class GetAvatar implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AttachmentControl control;
        try {
            control = new AttachmentControl();

            String url = request.getParameter("image");
            ResourceBundle bundle = ResourceBundle.getBundle("dropbox");
            if (url != null && control.isExists(url)) {
                control.downloadFile(url, response.getOutputStream());
            } else {
                control.downloadFile(bundle.getString("DEFAULT_PHOTO"), response.getOutputStream());
            }
        } catch (IOException e) {
            LOGGER.error("IOException in GetAvatar method: " + e);
        }
        return null;
    }
}
