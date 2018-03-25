package ca.sharipov.sergey.firebasechatandroidmvp.data;

public interface AuthorizationContract {

    interface Presenter {

        void onSuccessAuthorization();

        void onFailureAuthorization(String errorCode);

    }

    interface Model {

        void signInWithEmailAndPassword(String email, String password);

        void createUser(final String username, String email, String password);

    }

}
