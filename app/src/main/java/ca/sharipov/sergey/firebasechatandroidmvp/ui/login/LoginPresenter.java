package ca.sharipov.sergey.firebasechatandroidmvp.ui.login;

import android.text.TextUtils;

class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";

    private LoginContract.View view;
    private LoginContract.Model model;

    LoginPresenter() {
        model = new LoginModel(this);
    }

    @Override
    public void takeView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

    private boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isEmpty(String string) {
        return TextUtils.isEmpty(string);
    }

    @Override
    public void attemptLogin(String email, String password) {
        view.hideErrors();

        if (isEmpty(email)) {
            view.showErrorEmailRequired();
            return;
        }

        if (!isEmailValid(email)) {
            view.showErrorEmailInvalid();
            return;
        }

        if (isEmpty(password)) {
            view.showErrorPasswordRequired();
            return;
        }

        view.showProgress();

        model.signInWithEmailAndPassword(email, password);
    }

    @Override
    public void signInSuccess() {
        view.hideProgress();

        view.launchMainActivity();
    }

    @Override
    public void signInFailure(String errorCode) {
        view.hideProgress();

        if (errorCode != null) {
            switch (errorCode) {
                case "ERROR_INVALID_EMAIL":
                    view.showErrorEmailInvalid();
                    break;
                case "ERROR_WRONG_PASSWORD":
                case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                    view.showErrorWrongPassword();
                    break;
                case "ERROR_USER_NOT_FOUND":
                    view.showErrorEmailNotExist();
                    break;
                default:
                    view.showErrorAuthorization();
            }
        }
    }

}
