package forpdateam.ru.forpda;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.content.res.AppCompatResources;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.HashMap;

import biz.source_code.miniTemplator.MiniTemplator;
import forpdateam.ru.forpda.client.Client;
import io.realm.Realm;
import io.realm.RealmConfiguration;

import static org.acra.ReportField.ANDROID_VERSION;
import static org.acra.ReportField.APP_VERSION_CODE;
import static org.acra.ReportField.APP_VERSION_NAME;
import static org.acra.ReportField.CUSTOM_DATA;
import static org.acra.ReportField.LOGCAT;
import static org.acra.ReportField.PHONE_MODEL;
import static org.acra.ReportField.STACK_TRACE;
import static org.acra.ReportField.USER_COMMENT;

/**
 * Created by radiationx on 28.07.16.
 */
//acra
@ReportsCrashes(
        mailTo = "ololosh100500@gmail.com",
        mode = ReportingInteractionMode.DIALOG,
        resDialogTheme = R.style.AlertDialog,
        resDialogIcon = R.drawable.ic_warning_gray_24dp,
        resDialogTitle = R.string.crash_notif_title,
        resDialogText = R.string.crash_notif_text,
        resDialogCommentPrompt = R.string.crash_notif_ticker_text,
        resDialogOkToast = R.string.ok,
        customReportContent = {APP_VERSION_NAME, APP_VERSION_CODE, PHONE_MODEL, ANDROID_VERSION, USER_COMMENT, CUSTOM_DATA, STACK_TRACE, LOGCAT}
)
public class App extends android.app.Application {
    public static int keyboardHeight = 0;
    public static int statusBarHeight = 0;
    public static HashMap<Integer, Drawable> drawableHashMap = new HashMap<>();
    public static int px2, px4, px6, px8, px12, px14, px16, px24, px32, px36, px40, px48, px56, px64;
    private static App INSTANCE = new App();
    private static int savedKeyboardHeight = 0;
    private static DisplayImageOptions.Builder options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .resetViewBeforeLoading(true)
            .cacheOnDisc(true)
            .bitmapConfig(Bitmap.Config.ARGB_8888)
            .handler(new Handler())
            .displayer(new FadeInBitmapDisplayer(500, true, true, false));
    private SharedPreferences preferences;
    private MiniTemplator templator;

    public App() {
        INSTANCE = this;
    }

    public static int getStatusBarHeight() {
        return statusBarHeight;
    }

    public static void setStatusBarHeight(int statusBarHeight) {
        App.statusBarHeight = statusBarHeight;
    }

    public static int getKeyboardHeight() {
        return keyboardHeight;
    }

    public static void setKeyboardHeight(int newKeyboardHeight) {
        keyboardHeight = newKeyboardHeight;
        if (keyboardHeight == savedKeyboardHeight) return;
        App.getInstance().getPreferences().edit().putInt("keyboard_height", keyboardHeight).apply();
    }

    public static App getInstance() {
        return INSTANCE;
    }

    public static Drawable getAppDrawable(int id) {
        return drawableHashMap.get(id);
    }

    public static DisplayImageOptions.Builder getDefaultOptionsUIL() {
        return options;
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(5)
                .threadPriority(Thread.MIN_PRIORITY)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(5 * 1024 * 1024)) // 2 Mb
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .defaultDisplayImageOptions(options.build())
                .build();

        ImageLoader.getInstance().init(config);
    }

    public static Context getContext() {
        return getInstance();
    }

    public MiniTemplator getTemplator() {
        return templator;
    }

    /*private final static Pattern p = Pattern.compile("(?:[^\\s\\-—.,:;&?=#@><\\{\\}\\[\\]!~`*^%$\\|\"'\\/][\\s\\S][^\\s\\-—.,:;&?=#@><\\{\\}\\[\\]!~`*^%$\\|\"'\\/]*)");
    private Matcher matcher1;
    private Matcher matcher2;

    public boolean notStrictEquals(final String s1, final String s2) {
        matcher1 = matcher1 == null ? p.matcher(s1) : matcher1.reset(s1);
        matcher2 = matcher2 == null ? p.matcher(s2) : matcher2.reset(s2);
        while (matcher1.find() & matcher2.find())
            if (!matcher1.group().equalsIgnoreCase(matcher2.group())) return false;
        return true;
    }

    final String s1 = "Искусственная гравитация в Sci-Fi. Ищем истину";
    final String s2 = "Искусственная гравитация в Sci-Fi — ищем истину";*/
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // The following line triggers the initialization of ACRA
        ACRA.init(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /*long time = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            //Log.d("kek", "eq1 "+notStrictEquals(s1,s2));
            notStrictEquals(s1, s2);
        }
        Log.d("kek", "eq1 time: " + (System.currentTimeMillis() - time));
        *//*time = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            //Log.d("kek", "eq1 "+notStrictEquals(s1,s2));
            notStrictEquals2(s1, s2);
        }
        Log.d("kek", "eq2 time: " + (System.currentTimeMillis() - time));*//*
        //Log.d("kek", "eq2 "+notStrictEquals2(s1,s2));*/

        InputStream stream = null;
        try {
            stream = App.getInstance().getAssets().open("temp.html");
            try {
                templator = new MiniTemplator.Builder().build(stream, Charset.forName("utf-8"));
            } catch (Exception e) {
                Toast.makeText(getContext(), "Ошибка шаблона: " + e.getMessage(), Toast.LENGTH_LONG).show();
                //создание пустого шаблона
                templator = new MiniTemplator.Builder().build(new ByteArrayInputStream("Template error!".getBytes(Charset.forName("utf-8"))), Charset.forName("utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //init
        Client.getInstance();
        initImageLoader(this);
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("forpda.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
        px2 = getContext().getResources().getDimensionPixelSize(R.dimen.dp2);
        px4 = getContext().getResources().getDimensionPixelSize(R.dimen.dp4);
        px6 = getContext().getResources().getDimensionPixelSize(R.dimen.dp6);
        px8 = getContext().getResources().getDimensionPixelSize(R.dimen.dp8);
        px12 = getContext().getResources().getDimensionPixelSize(R.dimen.dp12);
        px14 = getContext().getResources().getDimensionPixelSize(R.dimen.dp14);
        px16 = getContext().getResources().getDimensionPixelSize(R.dimen.dp16);
        px24 = getContext().getResources().getDimensionPixelSize(R.dimen.dp24);
        px32 = getContext().getResources().getDimensionPixelSize(R.dimen.dp32);
        px36 = getContext().getResources().getDimensionPixelSize(R.dimen.dp36);
        px40 = getContext().getResources().getDimensionPixelSize(R.dimen.dp40);
        px48 = getContext().getResources().getDimensionPixelSize(R.dimen.dp48);
        px56 = getContext().getResources().getDimensionPixelSize(R.dimen.dp56);
        px64 = getContext().getResources().getDimensionPixelSize(R.dimen.dp64);

        //Для более быстрого доступа к drawable при работе программы
        for (Field f : R.drawable.class.getFields()) {
            try {
                if (!f.getName().contains("abc_"))
                    drawableHashMap.put(f.getInt(f), AppCompatResources.getDrawable(App.getContext(), f.getInt(f)));
            } catch (Exception ignore) {
            }
        }
        keyboardHeight = getPreferences().getInt("keyboard_height", getContext().getResources().getDimensionPixelSize(R.dimen.default_keyboard_height));
        savedKeyboardHeight = keyboardHeight;
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public SharedPreferences getPreferences() {
        if (preferences == null)
            preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences;
    }
}
