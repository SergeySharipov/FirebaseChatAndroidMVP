package ca.sharipov.sergey.firebasechatandroidmvp.ui.registration;

class RegistrationPresenter implements RegistrationContract.Presenter {

    private RegistrationContract.View view;

    @Override
    public void takeView(RegistrationContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

}
