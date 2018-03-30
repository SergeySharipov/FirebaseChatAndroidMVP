package ca.sharipov.sergey.firebasechatandroidmvp.ui.contacts;

import ca.sharipov.sergey.firebasechatandroidmvp.ui.BasePresenter;

interface ContactsContract {

    interface View {

    }

    interface Presenter extends BasePresenter<View> {

        void addContactToMyContacts(String id);

    }

    interface Model {


    }

}
