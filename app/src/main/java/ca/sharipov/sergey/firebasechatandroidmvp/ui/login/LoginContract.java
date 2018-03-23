package ca.sharipov.sergey.firebasechatandroidmvp.ui.login;

import ca.sharipov.sergey.firebasechatandroidmvp.ui.BasePresenter;

interface LoginContract {

    interface View {

        void showErrorEmailRequired();

        void showErrorPasswordRequired();

        void showErrorEmailInvalid();

        void showErrorWrongPassword();

        void showErrorAuthorization();

        void hideErrors();

        void showProgress();

        void hideProgress();

        void launchMainActivity();

        void launchRegistrationActivity();

        void showErrorEmailNotExist();
    }

    interface Presenter extends BasePresenter<View> {

        void attemptLogin(String email, String password);

    }

    interface Model {


    }

}
