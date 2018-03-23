package ca.sharipov.sergey.firebasechatandroidmvp.ui.registration;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.sharipov.sergey.firebasechatandroidmvp.R;

import static ca.sharipov.sergey.firebasechatandroidmvp.AppConstants.MINIMUM_PASSWORD_LENGTH;
import static ca.sharipov.sergey.firebasechatandroidmvp.AppConstants.MINIMUM_USERNAME_LENGTH;

public class RegistrationFragment extends Fragment implements RegistrationContract.View {

    public RegistrationFragment() {
    } // Required empty public constructor

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        return view;
    }

    @Override
    public void showErrorEmailRequired() {
        getString(R.string.registration_error_field_required);
    }

    @Override
    public void showErrorEmailInvalid() {
        getString(R.string.registration_error_email_invalid);
    }

    @Override
    public void showErrorPasswordRequired() {
        getString(R.string.registration_error_field_required);
    }

    @Override
    public void showErrorPasswordTooShort() {
        getString(R.string.registration_error_password_too_short, MINIMUM_PASSWORD_LENGTH);
    }

    @Override
    public void showErrorConfirmPasswordRequired() {
        getString(R.string.registration_error_field_required);
    }

    @Override
    public void showErrorPasswordsNotSame() {
        getString(R.string.registration_error_password_not_same);
    }

    @Override
    public void showErrorUsernameRequired() {
        getString(R.string.registration_error_field_required);
    }

    @Override
    public void showErrorUsernameTooShort() {
        getString(R.string.registration_error_username_too_short, MINIMUM_USERNAME_LENGTH);
    }
}
