package ca.sharipov.sergey.firebasechatandroidmvp.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static ca.sharipov.sergey.firebasechatandroidmvp.AppConstants.USERS_CHILD;

public class AuthorizationModel implements AuthorizationContract.Model, OnFailureListener {

    private static final String TAG = "AuthorizationModel";

    private AuthorizationContract.Presenter presenter;

    private FirebaseAuth firebaseAuth;

    public AuthorizationModel(AuthorizationContract.Presenter presenter) {
        this.presenter = presenter;

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void createUser(final String username, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser firebaseUser = authResult.getUser();
                        String userId = firebaseUser.getUid();
                        String email = firebaseUser.getEmail();

                        User user = new User(username, email);
                        addAdditionalUserInfoToDb(userId, user);
                    }
                })
                .addOnFailureListener(this);
    }

    private void addAdditionalUserInfoToDb(String userId, User user) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child(USERS_CHILD);
        if (usersRef != null) {
            usersRef.child(userId).setValue(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            presenter.onSuccessAuthorization();
                        }
                    }).addOnFailureListener(this);
        }
    }

    @Override
    public void signInWithEmailAndPassword(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        presenter.onSuccessAuthorization();
                    }
                })
                .addOnFailureListener(this);
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        Log.w(TAG, "onFailureAuthorization: " + e.getMessage());
        if (e instanceof FirebaseAuthException) {
            String errorCode = ((FirebaseAuthException) e).getErrorCode();
            presenter.onFailureAuthorization(errorCode);
        }
    }

}
