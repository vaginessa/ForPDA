package forpdateam.ru.forpda.fragments;

/**
 * Created by radiationx on 07.08.16.
 */
public interface ITabFragment {
    String getDefaultTitle();

    String getTitle();

    String getTabUrl();

    String getParentTag();

    int getUID();

    void setUID();

    boolean isAlone();

    boolean isUseCache();

    boolean onBackPressed();

    void hidePopupWindows();

    void loadData();

}
