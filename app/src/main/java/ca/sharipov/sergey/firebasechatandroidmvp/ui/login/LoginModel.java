package ca.sharipov.sergey.firebasechatandroidmvp.ui.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LoginModel implements LoginContract.Model {

    private static final String TAG = "LoginModel";

    private LoginContract.Presenter presenter;

    private FirebaseAuth firebaseAuth;

    LoginModel(LoginContract.Presenter presenter) {
        this.presenter = presenter;

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void signInWithEmailAndPassword(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        presenter.signInSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "signInWithEmailAndPassword:onComplete: "
                                + e.getMessage());
                        String errorCode = ((FirebaseAuthException) e).getErrorCode();
                        presenter.signInFailure(errorCode);
                    }
                });
    }
}
