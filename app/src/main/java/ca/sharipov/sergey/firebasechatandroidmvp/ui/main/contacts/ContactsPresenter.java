package ca.sharipov.sergey.firebasechatandroidmvp.ui.main.contacts;


class ContactsPresenter implements ContactsContract.Presenter {

    private ContactsContract.View view;

    @Override
    public void takeView(ContactsContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

}
