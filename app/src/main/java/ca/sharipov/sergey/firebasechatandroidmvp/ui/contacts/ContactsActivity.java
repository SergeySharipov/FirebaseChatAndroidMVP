package ca.sharipov.sergey.firebasechatandroidmvp.ui.contacts;

import android.support.v4.app.Fragment;

import ca.sharipov.sergey.firebasechatandroidmvp.ui.SingleFragmentActivity;

public class ContactsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ContactsFragment();
    }

}
