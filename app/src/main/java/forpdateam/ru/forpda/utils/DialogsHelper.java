package forpdateam.ru.forpda.utils;

import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.webkit.WebView;

/**
 * Created by radiationx on 01.11.16.
 */

public class DialogsHelper {
    private final static String openNewTab = "Открыть в новой вкладке";
    private final static String openBrowser = "Открыть в браузере";
    private final static String copyUrl = "Скопировать ссылку";
    private final static String openImage = "Открыть изображение";
    private final static String saveImage = "Сохранить изображение";
    private final static String copyImageUrl = "Скопировать ссылку изображения";
    private static AlertDialogMenu<Context, Pair<String, String>> alertDialogMenu;
    private static AlertDialogMenu<Context, Pair<String, String>> showedAlertDialogMenu;

    public static void handleContextMenu(Context context, int type, String extra, String nodeHref) {

        Log.d("kek", "context " + type + " : " + extra + " : " + nodeHref);
        if (type == WebView.HitTestResult.UNKNOWN_TYPE || type == WebView.HitTestResult.EDIT_TEXT_TYPE)
            return;
        type = type == WebView.HitTestResult.ANCHOR_TYPE ? WebView.HitTestResult.SRC_ANCHOR_TYPE : type;
        type = type == WebView.HitTestResult.IMAGE_ANCHOR_TYPE ? WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE : type;

        int index;
        boolean anchor = false, image = false;
        switch (type) {
            case WebView.HitTestResult.SRC_ANCHOR_TYPE:
                anchor = true;
                break;
            case WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE:
                anchor = true;
                image = true;
                break;
            case WebView.HitTestResult.IMAGE_TYPE:
                image = true;
                break;
        }

        if (image)
            image = !extra.contains("4pda.ru/forum/style_images");
        if (!anchor && !image)
            return;

        if (alertDialogMenu == null) {
            alertDialogMenu = new AlertDialogMenu<>();
            showedAlertDialogMenu = new AlertDialogMenu<>();

            alertDialogMenu.addItem(openNewTab, (context1, data) -> IntentHandler.handle(data.second));
            alertDialogMenu.addItem(openBrowser, null);
            alertDialogMenu.addItem(copyUrl, (context1, data) -> Utils.copyToClipBoard(data.second));
            alertDialogMenu.addItem(openImage, null);
            alertDialogMenu.addItem(saveImage, null);
            alertDialogMenu.addItem(copyImageUrl, (context1, data) -> Utils.copyToClipBoard(data.first));
        }
        showedAlertDialogMenu.clear();

        if (anchor) {
            showedAlertDialogMenu.addItem(alertDialogMenu.get(0));
            showedAlertDialogMenu.addItem(alertDialogMenu.get(1));
            showedAlertDialogMenu.addItem(alertDialogMenu.get(2));
        }
        if (image) {
            showedAlertDialogMenu.addItem(alertDialogMenu.get(3));
            showedAlertDialogMenu.addItem(alertDialogMenu.get(4));
            showedAlertDialogMenu.addItem(alertDialogMenu.get(5));
        }
        new AlertDialog.Builder(context)
                .setItems(showedAlertDialogMenu.getTitles(), (dialog, which) -> showedAlertDialogMenu.onClick(which, context, new Pair<>(extra, nodeHref)))
                .show();
    }
}
