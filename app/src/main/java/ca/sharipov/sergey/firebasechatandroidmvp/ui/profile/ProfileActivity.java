package ca.sharipov.sergey.firebasechatandroidmvp.ui.profile;

import android.support.v4.app.Fragment;

import ca.sharipov.sergey.firebasechatandroidmvp.ui.SingleFragmentActivity;

public class ProfileActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ProfileFragment();
    }
}
