package forpdateam.ru.forpda.api.theme.models;

import forpdateam.ru.forpda.api.theme.interfaces.IThemePost;

/**
 * Created by radiationx on 04.08.16.
 */
public class ThemePost implements IThemePost {
    private String date, avatar, nick, groupColor, group, reputation, body;
    private boolean curator, online, minus, plus, report, edit, delete;
    private int id, number, userId;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public String getGroupColor() {
        return groupColor;
    }

    public void setGroupColor(String groupColor) {
        if (groupColor == null)
            groupColor = "black";
        this.groupColor = groupColor;
    }

    @Override
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    @Override
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean isCurator() {
        return curator;
    }

    public void setCurator(boolean curator) {
        this.curator = curator;
    }

    @Override
    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public boolean canMinusRep() {
        return minus;
    }

    @Override
    public boolean canPlusRep() {
        return plus;
    }

    @Override
    public boolean canReport() {
        return report;
    }

    @Override
    public boolean canEdit() {
        return edit;
    }

    @Override
    public boolean canDelete() {
        return delete;
    }

    public void setCanMinus(boolean minus) {
        this.minus = minus;
    }

    public void setCanPlus(boolean plus) {
        this.plus = plus;
    }

    public void setCanReport(boolean report) {
        this.report = report;
    }

    public void setCanEdit(boolean edit) {
        this.edit = edit;
    }

    public void setCanDelete(boolean delete) {
        this.delete = delete;
    }
}
