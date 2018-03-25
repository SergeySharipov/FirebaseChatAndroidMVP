package ca.sharipov.sergey.firebasechatandroidmvp.ui.registration;

import ca.sharipov.sergey.firebasechatandroidmvp.ui.BasePresenter;

interface RegistrationContract {

    interface View {

        void showErrorEmailRequired();

        void showErrorPasswordRequired();

        void showErrorEmailInvalid();

        void showErrorRegistration();

        void hideErrors();

        void showProgress();

        void hideProgress();

        void launchMainActivity();

        void showErrorEmailExist();

        void showErrorUsernameRequired();

        void showErrorPasswordConfirmRequired();

        void showErrorPasswordsNotSame();

        void showErrorUsernameTooShort();

        void showErrorPasswordTooShort();
    }

    interface Presenter extends BasePresenter<View> {

        void attemptRegistration(String username, String email, String password, String passwordConfirm);
    }

    interface Model {


    }

}
