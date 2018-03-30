package ca.sharipov.sergey.firebasechatandroidmvp.ui.main.mycontacts;


class MyContactsPresenter implements MyContactsContract.Presenter {

    private MyContactsContract.View view;

    @Override
    public void takeView(MyContactsContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

}
