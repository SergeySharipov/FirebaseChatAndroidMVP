package ca.sharipov.sergey.firebasechatandroidmvp.ui.contacts;

import ca.sharipov.sergey.firebasechatandroidmvp.data.db.DbContract;
import ca.sharipov.sergey.firebasechatandroidmvp.data.db.DbModel;

class ContactsPresenter implements ContactsContract.Presenter, DbContract.Presenter {

    private ContactsContract.View view;
    private DbModel dbModel;

    public ContactsPresenter() {
        dbModel = new DbModel(this);
    }

    @Override
    public void takeView(ContactsContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

    @Override
    public void addContactToMyContacts(String id, String username) {
        dbModel.addContactToMyContacts(id);
        String chatId = dbModel.createChat(username, id);
        dbModel.createMessage(chatId, "Initial message");
    }

    @Override
    public void onDbSuccess() {

    }

    @Override
    public void onDbFailure() {

    }
}
