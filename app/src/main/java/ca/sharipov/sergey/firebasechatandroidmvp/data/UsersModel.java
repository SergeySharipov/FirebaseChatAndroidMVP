package ca.sharipov.sergey.firebasechatandroidmvp.data;

import com.google.firebase.auth.FirebaseAuth;

public class UsersModel implements UsersContract.Model {

    private UsersContract.Presenter presenter;

    private FirebaseAuth firebaseAuth;

    public UsersModel(UsersContract.Presenter presenter) {
        this.presenter = presenter;

        firebaseAuth = FirebaseAuth.getInstance();
    }

//    @Override
//    public void createUser(final String username, String email, String password) {
//        firebaseAuth.createUserWithEmailAndPassword(email, password)
//                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                    @Override
//                    public void onSuccess(AuthResult authResult) {
//                        FirebaseUser firebaseUser = authResult.getUser();
//                        String userId = firebaseUser.getUid();
//                        String email = firebaseUser.getEmail();
//
//                        User user = new User(username, email);
//                        addAdditionalUserInfoToDb(userId, user);
//                    }
//                })
//                .addOnFailureListener(this);
//    }
//
//    private void addAdditionalUserInfoToDb(String userId, User user) {
//        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child(USERS_CHILD);
//        if (usersRef != null) {
//            usersRef.child(userId).setValue(user)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            presenter.onSuccessAuthorization(getCurrentUserUid());
//                        }
//                    }).addOnFailureListener(this);
//        }
//    }
//
//    @Override
//    public void signInWithEmailAndPassword(String email, String password) {
//        firebaseAuth.signInWithEmailAndPassword(email, password)
//                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                    @Override
//                    public void onSuccess(AuthResult authResult) {
//                        presenter.onSuccessAuthorization(getCurrentUserUid());
//                    }
//                })
//                .addOnFailureListener(this);
//    }
//
//    @Override
//    public void onFailure(@NonNull Exception e) {
//        Log.w(TAG, "onFailureAuthorization: " + e.getMessage());
//        if (e instanceof FirebaseAuthException) {
//            String errorCode = ((FirebaseAuthException) e).getErrorCode();
//            presenter.onFailureAuthorization(errorCode);
//        }
//    }

}
