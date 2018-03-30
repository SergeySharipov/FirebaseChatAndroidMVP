package ca.sharipov.sergey.firebasechatandroidmvp.data;

public interface UsersContract {

    interface Presenter {

        void onSuccess();

        void onFailure(String errorCode);

    }

    interface Model {


    }

}
