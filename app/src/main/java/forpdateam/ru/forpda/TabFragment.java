package forpdateam.ru.forpda;

import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;

import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * Created by radiationx on 07.08.16.
 */
public class TabFragment extends RxFragment {
    protected View view;
    private String title;
    private String subtitle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        getSupportActionBar().setTitle(title);
        getMainActivity().updateTabList();
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        getSupportActionBar().setSubtitle(subtitle);
    }

    public View findViewById(@IdRes int id) {
        return view.findViewById(id);
    }

    public ActionBar getSupportActionBar() {
        return getMainActivity().getSupportActionBar();
    }

    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("kek", this + " : onresume");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setSubtitle(subtitle);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("kek", this + " : onpause");
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setSubtitle(null);
    }
}