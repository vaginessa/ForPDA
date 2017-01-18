package forpdateam.ru.forpda.api.profile.interfaces;

import android.text.Spanned;
import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by radiationx on 03.08.16.
 */
public interface IProfileModel {
    String getAvatar();

    void setAvatar(String arg);

    String getNick();

    void setNick(String arg);

    String getStatus();

    void setStatus(String arg);

    String getGroup();

    void setGroup(String arg);

    String getRegDate();

    void setRegDate(String arg);

    String getAlerts();

    void setAlerts(String arg);

    String getOnlineDate();

    void setOnlineDate(String arg);

    Spanned getSign();

    void setSign(Spanned arg);

    String getGender();

    void setGender(String arg);

    String getBirthDay();

    void setBirthDay(String arg);

    String getUserTime();

    void setUserTime(String arg);

    ArrayList<Pair<String, String>> getContacts();

    ArrayList<Pair<String, String>> getDevices();

    Pair<String, String> getKarma();

    void setKarma(Pair<String, String> arg);

    Pair<String, String> getSitePosts();

    void setSitePosts(Pair<String, String> arg);

    Pair<String, String> getComments();

    void setComments(Pair<String, String> arg);

    Pair<String, String> getReputation();

    void setReputation(Pair<String, String> arg);

    Pair<String, String> getTopics();

    void setTopics(Pair<String, String> arg);

    Pair<String, String> getPosts();

    void setPosts(Pair<String, String> arg);

    String getNote();

    void setNote(String arg);

    Spanned getAbout();

    void setAbout(Spanned arg);

    void addContact(Pair<String, String> arg);

    void addDevice(Pair<String, String> arg);
}
