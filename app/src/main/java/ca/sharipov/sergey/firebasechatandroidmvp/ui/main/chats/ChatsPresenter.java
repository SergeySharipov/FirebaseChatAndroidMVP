package ca.sharipov.sergey.firebasechatandroidmvp.ui.main.chats;

class ChatsPresenter implements ChatsContract.Presenter {

    private ChatsContract.View view;

    @Override
    public void takeView(ChatsContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

}
