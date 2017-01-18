package forpdateam.ru.forpda.api.qms.models;

import forpdateam.ru.forpda.api.qms.interfaces.IQmsChatItem;

/**
 * Created by radiationx on 03.08.16.
 */
public class QmsMessage implements IQmsChatItem {
    private boolean whoseMessage = false;
    private boolean isDate = false;
    private String id, readStatus, time, avatar, date, content;

    @Override
    public boolean getWhoseMessage() {
        return whoseMessage;
    }

    public void setWhoseMessage(boolean whoseMessage) {
        this.whoseMessage = whoseMessage;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    @Override
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean isDate() {
        return isDate;
    }

    @Override
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIsDate(boolean isDate) {
        this.isDate = isDate;
    }
}
