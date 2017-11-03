package model;

import java.time.LocalDate;

public class Attachment {
    private int idattachment;
    private String attachmentName;
    private String attachmentPath;
    private LocalDate dateUpload;
    private String attachmentComment;

    public LocalDate getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(LocalDate dateUpload) {
        this.dateUpload = dateUpload;
    }

    public int getIdattachment() {
        return idattachment;
    }

    public void setIdattachment(int idattachment) {
        this.idattachment = idattachment;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getAttachmentComment() {
        return attachmentComment;
    }

    public void setAttachmentComment(String attachmentComment) {
        this.attachmentComment = attachmentComment;
    }

    @Override
    public String toString() {
        return "Attachment: name = " + attachmentName + " path = " + attachmentPath;
    }
}
