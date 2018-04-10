package ca.sharipov.sergey.firebasechatandroidmvp.data.db;

import java.util.Map;

import ca.sharipov.sergey.firebasechatandroidmvp.data.model.User;

public interface DbContract {

    interface Presenter {

        void onDbSuccess();

        void onDbFailure();

    }

    interface Model {

        void addAdditionalUserInfoToDb(String userId, User user);

        void addContactToMyContacts(String newContactId);

        void addContactsToMyContacts(Map<String, Object> newContactsId);

        String createChat(String chatName, String receiverContactId);

        void createMessage(String chatId, String content);

    }

}
