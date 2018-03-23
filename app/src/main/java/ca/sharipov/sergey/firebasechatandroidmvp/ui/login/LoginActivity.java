package ca.sharipov.sergey.firebasechatandroidmvp.ui.login;

import android.support.v4.app.Fragment;

import ca.sharipov.sergey.firebasechatandroidmvp.ui.SingleFragmentActivity;

public class LoginActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new LoginFragment();
    }

}
