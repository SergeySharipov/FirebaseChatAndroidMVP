package ca.sharipov.sergey.firebasechatandroidmvp.ui.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import ca.sharipov.sergey.firebasechatandroidmvp.R;
import ca.sharipov.sergey.firebasechatandroidmvp.ui.main.MainActivity;

import static ca.sharipov.sergey.firebasechatandroidmvp.AppConstants.MINIMUM_PASSWORD_LENGTH;
import static ca.sharipov.sergey.firebasechatandroidmvp.AppConstants.MINIMUM_USERNAME_LENGTH;

public class RegistrationFragment extends Fragment implements RegistrationContract.View {

    private RegistrationContract.Presenter presenter;

    private EditText etEmail;
    private EditText etPassword;
    private EditText etPasswordConfirm;
    private EditText etUsername;
    private ProgressBar progressBar;

    public RegistrationFragment() {
    } // Required empty public constructor

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RegistrationPresenter();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_registration, container, false);

        etEmail = v.findViewById(R.id.et_email);
        etPassword = v.findViewById(R.id.et_password);
        etPasswordConfirm = v.findViewById(R.id.et_password_confirm);
        etUsername = v.findViewById(R.id.et_username);

        progressBar = v.findViewById(R.id.progress_bar);

        v.findViewById(R.id.btn_registration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String passwordConfirm = etPasswordConfirm.getText().toString();

                presenter.attemptRegistration(username, email, password, passwordConfirm);
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.takeView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.dropView();
    }

    @Override
    public void showErrorEmailRequired() {
        etEmail.setError(getString(R.string.registration_error_field_required));
        etEmail.requestFocus();
    }

    @Override
    public void showErrorEmailInvalid() {
        etEmail.setError(getString(R.string.registration_error_email_invalid));
        etEmail.requestFocus();
    }

    @Override
    public void showErrorEmailExist() {
        etEmail.setError(getString(R.string.registration_error_email_exist));
        etEmail.requestFocus();
    }

    @Override
    public void showErrorUsernameRequired() {
        etUsername.setError(getString(R.string.registration_error_field_required));
        etUsername.requestFocus();
    }

    @Override
    public void showErrorPasswordConfirmRequired() {
        etPasswordConfirm.setError(getString(R.string.registration_error_field_required));
        etPasswordConfirm.requestFocus();
    }

    @Override
    public void showErrorPasswordsNotSame() {
        Toast.makeText(getContext(), R.string.registration_error_passwords_not_same, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorUsernameTooShort() {
        etUsername.setError(getString(R.string.registration_error_username_too_short, MINIMUM_USERNAME_LENGTH));
        etUsername.requestFocus();
    }

    @Override
    public void showErrorPasswordTooShort() {
        etPassword.setError(getString(R.string.registration_error_password_too_short, MINIMUM_PASSWORD_LENGTH));
        etPassword.requestFocus();
    }

    @Override
    public void showErrorPasswordRequired() {
        etPassword.setError(getString(R.string.registration_error_field_required));
        etPassword.requestFocus();
    }

    @Override
    public void showErrorRegistration() {
        Toast.makeText(getContext(), R.string.registration_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideErrors() {
        etEmail.setError(null);
        etPassword.setError(null);
    }

    @Override
    public void showProgress() {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.animate().setDuration(shortAnimTime).alpha(1);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        progressBar.animate().cancel();
    }

    @Override
    public void launchMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
