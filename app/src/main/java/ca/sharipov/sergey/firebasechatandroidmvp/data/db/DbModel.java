package ca.sharipov.sergey.firebasechatandroidmvp.data.db;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import ca.sharipov.sergey.firebasechatandroidmvp.data.AppSharedPrefHelper;
import ca.sharipov.sergey.firebasechatandroidmvp.data.model.Chat;
import ca.sharipov.sergey.firebasechatandroidmvp.data.model.Message;
import ca.sharipov.sergey.firebasechatandroidmvp.data.model.User;

import static ca.sharipov.sergey.firebasechatandroidmvp.AppConstants.CHATS_CHILD;
import static ca.sharipov.sergey.firebasechatandroidmvp.AppConstants.MESSAGE_CHILD;
import static ca.sharipov.sergey.firebasechatandroidmvp.AppConstants.USERS_CHILD;

public class DbModel implements DbContract.Model, OnSuccessListener<Void>, OnFailureListener {

    private DbContract.Presenter presenter;

    public DbModel(DbContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addAdditionalUserInfoToDb(String userId, User user) {
        FirebaseDatabase.getInstance()
                .getReference()
                .child(USERS_CHILD)
                .child(userId)
                .setValue(user)
                .addOnSuccessListener(this)
                .addOnFailureListener(this);
    }

    @Override
    public void addContactToMyContacts(String newContactId) {
        Map<String, Object> map = new HashMap<>();
        map.put(newContactId, true);
        addContactsToMyContacts(map);
    }

    @Override
    public void addContactsToMyContacts(Map<String, Object> newContactsId) {
        FirebaseDatabase.getInstance()
                .getReference()
                .child(USERS_CHILD)
                .child(AppSharedPrefHelper.getCurrentUserId())
                .child("contactList")
                .updateChildren(newContactsId)
                .addOnSuccessListener(this)
                .addOnFailureListener(this);
    }

    @Override
    public String createChat(String chatName, String secondMemberContactId) {
        Map<String, Boolean> map = new HashMap<>();
        map.put(secondMemberContactId, true);
        map.put(AppSharedPrefHelper.getCurrentUserId(), true);
        Chat chat = new Chat(chatName, false, map);
        String chatId = UUID.randomUUID().toString();

        FirebaseDatabase.getInstance()
                .getReference()
                .child(CHATS_CHILD)
                .child(chatId)
                .setValue(chat)
                .addOnSuccessListener(this)
                .addOnFailureListener(this);
        return chatId;
    }

    @Override
    public void createMessage(String chatId, String content) {
        Message message = new Message(AppSharedPrefHelper.getCurrentUserId(), content);

        FirebaseDatabase.getInstance()
                .getReference()
                .child(MESSAGE_CHILD)
                .child(chatId)
                .child(UUID.randomUUID().toString())
                .setValue(message)
                .addOnSuccessListener(this)
                .addOnFailureListener(this);
    }

    @Override
    public void onSuccess(Void aVoid) {
        presenter.onDbSuccess();
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        presenter.onDbFailure();
    }
}