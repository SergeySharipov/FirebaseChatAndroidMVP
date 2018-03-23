package ca.sharipov.sergey.firebasechatandroidmvp.ui.registration;

import android.text.TextUtils;

import static ca.sharipov.sergey.firebasechatandroidmvp.AppConstants.MINIMUM_PASSWORD_LENGTH;
import static ca.sharipov.sergey.firebasechatandroidmvp.AppConstants.MINIMUM_USERNAME_LENGTH;

class RegistrationPresenter implements RegistrationContract.Presenter {

    private RegistrationContract.View view;

    @Override
    public void takeView(RegistrationContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

    public boolean checkEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            view.showErrorEmailRequired();
            return false;
        } else if (email.length() < 3 || !email.contains("@") || !email.contains(".")) {
            view.showErrorEmailInvalid();
            return false;
        }
        return true;
    }

    public boolean checkPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            view.showErrorPasswordRequired();
            return false;
        } else if (password.length() < MINIMUM_PASSWORD_LENGTH) {
            view.showErrorPasswordTooShort();
            return false;
        }
        return true;
    }

    public boolean checkPasswords(String password, String password2) {
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(password2)) {
            view.showErrorConfirmPasswordRequired();
            return false;
        } else if (!password.equals(password2)) {
            view.showErrorPasswordsNotSame();
            return false;
        }
        return true;
    }

    public boolean checkUsername(String username) {
        if (TextUtils.isEmpty(username)) {
            view.showErrorUsernameRequired();
            return false;
        } else if (username.length() < MINIMUM_USERNAME_LENGTH) {
            view.showErrorUsernameTooShort();
            return false;
        }
        return true;
    }

}

//        ("ERROR_INVALID_CUSTOM_TOKEN", "The custom token format is incorrect. Please check the documentation."));
//        ("ERROR_CUSTOM_TOKEN_MISMATCH", "The custom token corresponds to a different audience."));
//        ("ERROR_INVALID_CREDENTIAL", "The supplied auth credential is malformed or has expired."));
//        ("ERROR_INVALID_EMAIL", "The email address is badly formatted."));
//        ("ERROR_WRONG_PASSWORD", "The password is invalid or the user does not have a password."));
//        ("ERROR_USER_MISMATCH", "The supplied credentials do not correspond to the previously signed in user."));
//        ("ERROR_REQUIRES_RECENT_LOGIN", "This operation is sensitive and requires recent authentication. Log in again before retrying this request."));
//        ("ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL", "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address."));
//        ("ERROR_EMAIL_ALREADY_IN_USE", "The email address is already in use by another account."));
//        ("ERROR_CREDENTIAL_ALREADY_IN_USE", "This credential is already associated with a different user account."));
//        ("ERROR_USER_DISABLED", "The user account has been disabled by an administrator."));
//        ("ERROR_USER_TOKEN_EXPIRED", "The user\'s credential is no longer valid. The user must sign in again."));
//        ("ERROR_USER_NOT_FOUND", "There is no user record corresponding to this identifier. The user may have been deleted."));
//        ("ERROR_INVALID_USER_TOKEN", "The user\'s credential is no longer valid. The user must sign in again."));
//        ("ERROR_OPERATION_NOT_ALLOWED", "This operation is not allowed. You must enable this service in the console."));
//        ("ERROR_WEAK_PASSWORD", "The given password is invalid."));