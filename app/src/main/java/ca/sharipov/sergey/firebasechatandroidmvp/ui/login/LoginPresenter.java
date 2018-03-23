package ca.sharipov.sergey.firebasechatandroidmvp.ui.login;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";

    private LoginContract.View view;

    private FirebaseAuth firebaseAuth;

    LoginPresenter() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void takeView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

    private boolean checkEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            view.showErrorEmailRequired();
            return false;
        } else if (email.length() < 3 || !email.contains("@") || !email.contains(".")) {
            view.showErrorEmailInvalid();
            return false;
        }
        return true;
    }

    private boolean checkPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            view.showErrorPasswordRequired();
            return false;
        }
        return true;
    }

    @Override
    public void attemptLogin(String email, String password) {
        view.hideErrors();

        if (checkEmail(email) && checkPassword(password)) {
            view.showProgress();

            signInWithEmailAndPassword(email, password);
        }
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    private void signInWithEmailAndPassword(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        view.hideProgress();

                        if (task.isSuccessful()) {
                            view.launchMainActivity();
                        } else {
                            Log.w(TAG, "signInWithEmailAndPassword:onComplete: "
                                    + task.getException());
                            if (task.getException() != null) {
                                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();

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
                });
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
