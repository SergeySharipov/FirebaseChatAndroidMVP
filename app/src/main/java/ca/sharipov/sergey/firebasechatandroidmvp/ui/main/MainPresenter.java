package ca.sharipov.sergey.firebasechatandroidmvp.ui.main;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

class MainPresenter implements MainContract.Presenter, FirebaseAuth.AuthStateListener {

    private MainContract.View view;

    private FirebaseAuth firebaseAuth;

    MainPresenter() {
        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseAuth.signOut();
    }

    @Override
    public void takeView(MainContract.View view) {
        this.view = view;
        if (firebaseAuth != null) {
            firebaseAuth.addAuthStateListener(this);
        }
    }

    @Override
    public void dropView() {
        this.view = null;
        if (firebaseAuth != null) {
            firebaseAuth.removeAuthStateListener(this);
        }
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (view != null && user == null) {
            // user firebaseAuth state is changed - user is null
            // launch login activity
            view.launchLoginActivity();
        }
    }
}
