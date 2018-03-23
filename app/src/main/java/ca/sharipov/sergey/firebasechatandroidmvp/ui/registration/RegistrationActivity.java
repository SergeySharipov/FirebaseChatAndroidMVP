package ca.sharipov.sergey.firebasechatandroidmvp.ui.registration;

import android.support.v4.app.Fragment;

import ca.sharipov.sergey.firebasechatandroidmvp.ui.SingleFragmentActivity;

public class RegistrationActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new RegistrationFragment();
    }

}
