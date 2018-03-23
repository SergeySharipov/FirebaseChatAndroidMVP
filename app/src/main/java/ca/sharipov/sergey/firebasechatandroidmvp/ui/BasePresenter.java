package ca.sharipov.sergey.firebasechatandroidmvp.ui;

public interface BasePresenter<V> {

    void takeView(V v);

    void dropView();

}
