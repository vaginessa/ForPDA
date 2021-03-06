package forpdateam.ru.forpda.fragments.theme.editpost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import forpdateam.ru.forpda.App;
import forpdateam.ru.forpda.R;
import forpdateam.ru.forpda.TabManager;
import forpdateam.ru.forpda.api.Api;
import forpdateam.ru.forpda.api.theme.editpost.models.AttachmentItem;
import forpdateam.ru.forpda.api.theme.editpost.models.EditPostForm;
import forpdateam.ru.forpda.api.theme.models.ThemePage;
import forpdateam.ru.forpda.client.RequestFile;
import forpdateam.ru.forpda.fragments.TabFragment;
import forpdateam.ru.forpda.fragments.theme.ThemeFragment;
import forpdateam.ru.forpda.messagepanel.MessagePanel;
import forpdateam.ru.forpda.messagepanel.attachments.AttachmentsPopup;
import forpdateam.ru.forpda.utils.FilePickHelper;

import static forpdateam.ru.forpda.api.theme.editpost.models.EditPostForm.ARG_TYPE;
import static forpdateam.ru.forpda.api.theme.editpost.models.EditPostForm.TYPE_EDIT_POST;
import static forpdateam.ru.forpda.api.theme.editpost.models.EditPostForm.TYPE_NEW_POST;

/**
 * Created by radiationx on 14.01.17.
 */

public class EditPostFragment extends TabFragment {
    private final static String ARG_ATTACHMENTS = "attachments";
    private final static String ARG_MESSAGE = "message";
    private final static String ARG_FORUM_ID = "forumId";
    private final static String ARG_TOPIC_ID = "topicId";
    private final static String ARG_POST_ID = "postId";
    private final static String ARG_ST = "st";
    private static final int PICK_IMAGE = 1228;
    private final EditPostForm postForm = new EditPostForm();
    private MessagePanel messagePanel;
    private AttachmentsPopup attachmentsPopup;
    private Subscriber<ThemePage> sendSubscriber = new Subscriber<>();
    private Subscriber<EditPostForm> formSubscriber = new Subscriber<>();
    private Subscriber<List<AttachmentItem>> attachmentSubscriber = new Subscriber<>();

    public static EditPostFragment newInstance(int postId, int topicId, int forumId, int st) {
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, TYPE_EDIT_POST);
        args.putInt(ARG_FORUM_ID, forumId);
        args.putInt(ARG_TOPIC_ID, topicId);
        args.putInt(ARG_POST_ID, postId);
        args.putInt(ARG_ST, st);
        EditPostFragment fragment = new EditPostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static EditPostFragment newInstance(EditPostForm form) {
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, TYPE_NEW_POST);
        args.putParcelableArrayList(ARG_ATTACHMENTS, form.getAttachments());
        args.putString(ARG_MESSAGE, form.getMessage());
        args.putInt(ARG_FORUM_ID, form.getForumId());
        args.putInt(ARG_TOPIC_ID, form.getTopicId());
        args.putInt(ARG_POST_ID, form.getPostId());
        args.putInt(ARG_ST, form.getSt());
        EditPostFragment fragment = new EditPostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle args = getArguments();
            postForm.setType(args.getInt(ARG_TYPE));
            postForm.setAttachments(args.getParcelableArrayList(ARG_ATTACHMENTS));
            postForm.setMessage(args.getString(ARG_MESSAGE));
            postForm.setForumId(args.getInt(ARG_FORUM_ID));
            postForm.setTopicId(args.getInt(ARG_TOPIC_ID));
            postForm.setPostId(args.getInt(ARG_POST_ID));
            postForm.setSt(args.getInt(ARG_ST));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initBaseView(inflater, container);
        //baseInflateFragment(inflater, R.layout.fragment_qms_chat);
        messagePanel = new MessagePanel(getContext(), (ViewGroup) findViewById(R.id.fragment_container), (ViewGroup) view.findViewById(R.id.fragment_content), true);
        messagePanel.addSendOnClickListener(v -> {
            if (postForm.getType() == TYPE_EDIT_POST) {
                showReasonDialog();
            } else {
                sendMessage();
            }
        });
        attachmentsPopup = messagePanel.getAttachmentsPopup();
        attachmentsPopup.setAddOnClickListener(v -> pickImage());
        attachmentsPopup.setDeleteOnClickListener(v -> removeFiles());


        viewsReady();

        return view;
    }

    @Override
    public void loadData() {
        if (postForm.getType() == TYPE_EDIT_POST) {
            loadForm();
        } else {
            messagePanel.insertText(postForm.getMessage());
            messagePanel.getAttachmentsPopup().onLoadAttachments(postForm);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        messagePanel.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        messagePanel.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        messagePanel.onDestroy();
    }

    @Override
    public boolean onBackPressed() {
        return messagePanel.onBackPressed();
    }

    @Override
    public void hidePopupWindows() {
        super.hidePopupWindows();
        messagePanel.hidePopupWindows();
    }

    private void sendMessage() {
        messagePanel.setProgressState(true);
        postForm.setMessage(messagePanel.getMessage());
        List<AttachmentItem> attachments = messagePanel.getAttachments();
        postForm.getAttachments().clear();
        for (AttachmentItem item : attachments) {
            postForm.addAttachment(item);
        }
        sendSubscriber.subscribe(Api.EditPost().sendPost(postForm), s -> {
            messagePanel.setProgressState(false);
            ThemeFragment fragment = (ThemeFragment) TabManager.getInstance().get(getParentTag());
            if (fragment != null) {
                if (postForm.getType() == TYPE_EDIT_POST) {
                    fragment.onEditPostCompleted(s);
                } else {
                    fragment.onSendPostCompleted(s);
                }
            }
            TabManager.getInstance().remove(EditPostFragment.this);
        }, new ThemePage(), v -> loadData());
    }

    private void loadForm() {
        formSubscriber.subscribe(Api.EditPost().loadForm(postForm.getPostId()), form -> {
            postForm.setMessage(form.getMessage());
            postForm.setEditReason(form.getEditReason());
            postForm.setAttachments(form.getAttachments());
            attachmentsPopup.onLoadAttachments(form);
            messagePanel.insertText(postForm.getMessage());
        }, postForm, null);
    }

    public void uploadFiles(List<RequestFile> files) {
        attachmentsPopup.preUploadFiles(files);
        attachmentSubscriber.subscribe(Api.EditPost().uploadFiles(postForm.getPostId(), files), items -> attachmentsPopup.onUploadFiles(items), new ArrayList<>(), null);
    }

    public void removeFiles() {
        attachmentsPopup.preDeleteFiles();
        List<AttachmentItem> selectedFiles = attachmentsPopup.getSelected();
        attachmentSubscriber.subscribe(Api.EditPost().deleteFiles(postForm.getPostId(), selectedFiles), item -> attachmentsPopup.onDeleteFiles(item), selectedFiles, null);
    }

    public void pickImage() {
        startActivityForResult(FilePickHelper.pickImage(PICK_IMAGE), PICK_IMAGE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }
            uploadFiles(FilePickHelper.onActivityResult(getContext(), data.getData()));
        }
    }

    private void showReasonDialog() {
        AppCompatEditText editText = new AppCompatEditText(getContext());
        editText.setPadding(App.px24, 0, App.px24, 0);
        editText.setHint("Причина редактирования");
        editText.setText(postForm.getEditReason());
        new AlertDialog.Builder(getContext())
                .setView(editText)
                .setTitle("Причина редактирования")
                .setPositiveButton("Ок", (dialog, which) -> sendMessage())
                .setNegativeButton("Отмена", null)
                .show();
    }

}
