package ca.sharipov.sergey.firebasechatandroidmvp.ui.profile;

class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View view;

    @Override
    public void takeView(ProfileContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

}
