package ca.sharipov.sergey.firebasechatandroidmvp.ui.login;

import android.text.TextUtils;

import ca.sharipov.sergey.firebasechatandroidmvp.data.AuthorizationContract;
import ca.sharipov.sergey.firebasechatandroidmvp.data.AuthorizationModel;

class LoginPresenter implements LoginContract.Presenter, AuthorizationContract.Presenter {

    private static final String TAG = "LoginPresenter";

    private LoginContract.View view;
    private AuthorizationContract.Model model;

    LoginPresenter() {
        model = new AuthorizationModel(this);
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

    @Override
    public void attemptLogin(String email, String password) {
        view.hideErrors();

        email = email.trim();
        password = password.trim();

        if (TextUtils.isEmpty(email)) {
            view.showErrorEmailRequired();
            return;
        }

        if (!isEmailValid(email)) {
            view.showErrorEmailInvalid();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            view.showErrorPasswordRequired();
            return;
        }

        view.showProgress();

        model.signInWithEmailAndPassword(email, password);
    }

    @Override
    public void onSuccessAuthorization() {
        view.hideProgress();

        view.launchMainActivity();
    }

    @Override
    public void onFailureAuthorization(String errorCode) {
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
