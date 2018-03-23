package ca.sharipov.sergey.firebasechatandroidmvp.ui.main;

import ca.sharipov.sergey.firebasechatandroidmvp.ui.BasePresenter;

interface MainContract {

    interface View {

        void launchLoginActivity();
    }

    interface Presenter extends BasePresenter<View> {


    }

    interface Model {


    }

}
