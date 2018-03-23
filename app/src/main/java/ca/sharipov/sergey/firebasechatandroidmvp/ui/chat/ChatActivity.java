package ca.sharipov.sergey.firebasechatandroidmvp.ui.chat;

import android.support.v4.app.Fragment;

import ca.sharipov.sergey.firebasechatandroidmvp.ui.SingleFragmentActivity;

public class ChatActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ChatFragment();
    }

}
