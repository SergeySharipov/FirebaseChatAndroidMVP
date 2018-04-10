package ca.sharipov.sergey.firebasechatandroidmvp.ui.registration;

import android.text.TextUtils;

import com.google.firebase.auth.FirebaseUser;

import ca.sharipov.sergey.firebasechatandroidmvp.data.autharization.AuthorizationContract;
import ca.sharipov.sergey.firebasechatandroidmvp.data.autharization.AuthorizationModel;
import ca.sharipov.sergey.firebasechatandroidmvp.data.db.DbContract;
import ca.sharipov.sergey.firebasechatandroidmvp.data.db.DbModel;
import ca.sharipov.sergey.firebasechatandroidmvp.data.model.User;

import static ca.sharipov.sergey.firebasechatandroidmvp.AppConstants.MINIMUM_PASSWORD_LENGTH;
import static ca.sharipov.sergey.firebasechatandroidmvp.AppConstants.MINIMUM_USERNAME_LENGTH;

class RegistrationPresenter implements RegistrationContract.Presenter,
        AuthorizationContract.Presenter, DbContract.Presenter {

    private RegistrationContract.View view;
    private AuthorizationContract.Model authorizationModel;
    private DbContract.Model dbModel;

    RegistrationPresenter() {
        authorizationModel = new AuthorizationModel(this);
        dbModel = new DbModel(this);
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

        authorizationModel.createUser(email, password);
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
    public void onSuccessAuthorization(FirebaseUser firebaseUser) {
        addAdditionalUserInfoToDb(firebaseUser);
    }

    private void addAdditionalUserInfoToDb(FirebaseUser firebaseUser) {
        String userId = firebaseUser.getUid();
        String email = firebaseUser.getEmail();

        User user = new User("username", email);//todo use proper username

        dbModel.addAdditionalUserInfoToDb(userId, user);
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

    @Override
    public void onDbSuccess() {
        view.hideProgress();

        view.launchMainActivity();
    }

    @Override
    public void onDbFailure() {
        authorizationModel.deleteCurrentUser();

        view.hideProgress();

        view.showErrorRegistration();
    }
}