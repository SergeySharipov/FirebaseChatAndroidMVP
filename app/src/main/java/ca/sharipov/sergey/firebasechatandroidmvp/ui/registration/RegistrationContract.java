package ca.sharipov.sergey.firebasechatandroidmvp.ui.registration;

import ca.sharipov.sergey.firebasechatandroidmvp.ui.BasePresenter;

interface RegistrationContract {

    interface View {

        void showErrorEmailRequired();

        void showErrorEmailInvalid();

        void showErrorPasswordRequired();

        void showErrorPasswordTooShort();

        void showErrorConfirmPasswordRequired();

        void showErrorPasswordsNotSame();

        void showErrorUsernameRequired();

        void showErrorUsernameTooShort();
    }

    interface Presenter extends BasePresenter<View> {


    }

    interface Model {


    }

}
