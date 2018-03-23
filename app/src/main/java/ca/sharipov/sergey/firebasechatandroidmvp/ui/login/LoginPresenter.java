package ca.sharipov.sergey.firebasechatandroidmvp.ui.login;

class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    @Override
    public void takeView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

}
