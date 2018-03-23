package ca.sharipov.sergey.firebasechatandroidmvp.ui.chat;

class ChatPresenter implements ChatContract.Presenter {

    private ChatContract.View view;

    @Override
    public void takeView(ChatContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

}
