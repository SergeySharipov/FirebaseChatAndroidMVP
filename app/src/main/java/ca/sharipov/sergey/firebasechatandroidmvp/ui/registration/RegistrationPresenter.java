package ca.sharipov.sergey.firebasechatandroidmvp.ui.registration;

import android.text.TextUtils;

import ca.sharipov.sergey.firebasechatandroidmvp.data.AuthorizationContract;
import ca.sharipov.sergey.firebasechatandroidmvp.data.AuthorizationModel;

import static ca.sharipov.sergey.firebasechatandroidmvp.AppConstants.MINIMUM_PASSWORD_LENGTH;
import static ca.sharipov.sergey.firebasechatandroidmvp.AppConstants.MINIMUM_USERNAME_LENGTH;

class RegistrationPresenter implements RegistrationContract.Presenter, AuthorizationContract.Presenter {

    private static final String TAG = "RegistrationPresenter";

    private RegistrationContract.View view;
    private AuthorizationContract.Model model;

    RegistrationPresenter() {
        model = new AuthorizationModel(this);
    }

    @Override
    public void takeView(RegistrationContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

    @Override
    public void attemptRegistration(String username, String email, String password, String passwordConfirm) {
        view.hideErrors();

        username = username.trim();
        email = email.trim();
        password = password.trim();
        passwordConfirm = passwordConfirm.trim();

        if (TextUtils.isEmpty(username)) {
            view.showErrorUsernameRequired();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            view.showErrorEmailRequired();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            view.showErrorPasswordRequired();
            return;
        }

        if (TextUtils.isEmpty(passwordConfirm)) {
            view.showErrorPasswordConfirmRequired();
            return;
        }

        if (!isEmailValid(email)) {
            view.showErrorEmailInvalid();
            return;
        }

        if (!isUsernameLongEnough(username)) {
            view.showErrorUsernameTooShort();
            return;
        }

        if (!isPasswordLongEnough(password)) {
            view.showErrorPasswordTooShort();
            return;
        }

        if (!password.equals(passwordConfirm)) {
            view.showErrorPasswordsNotSame();
            return;
        }

        view.showProgress();

        model.createUser(username, email, password);
    }

    private boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isUsernameLongEnough(String username) {
        return username.length() >= MINIMUM_USERNAME_LENGTH;
    }

    private boolean isPasswordLongEnough(String password) {
        return password.length() >= MINIMUM_PASSWORD_LENGTH;
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
                case "ERROR_EMAIL_ALREADY_IN_USE":
                case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                    view.showErrorEmailExist();
                    break;
                default:
                    view.showErrorRegistration();
            }
        }
    }
}