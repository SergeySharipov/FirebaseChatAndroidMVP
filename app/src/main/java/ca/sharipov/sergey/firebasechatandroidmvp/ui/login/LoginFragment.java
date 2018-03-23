package ca.sharipov.sergey.firebasechatandroidmvp.ui.login;


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
import ca.sharipov.sergey.firebasechatandroidmvp.ui.registration.RegistrationActivity;

public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter presenter;

    private EditText etEmail;
    private EditText etPassword;
    private ProgressBar progressBar;

    public LoginFragment() {
    } // Required empty public constructor

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        etEmail = v.findViewById(R.id.et_email);
        etPassword = v.findViewById(R.id.et_password);

        progressBar = v.findViewById(R.id.progress_bar);

        v.findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                presenter.attemptLogin(email, password);
            }
        });
        v.findViewById(R.id.btn_registration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchRegistrationActivity();
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
        etEmail.setError(getString(R.string.login_error_field_required));
        etEmail.requestFocus();
    }

    @Override
    public void showErrorEmailInvalid() {
        etEmail.setError(getString(R.string.login_error_email_invalid));
        etEmail.requestFocus();
    }

    @Override
    public void showErrorEmailNotExist() {
        etEmail.setError(getString(R.string.login_error_email_not_exist));
        etEmail.requestFocus();
    }

    @Override
    public void showErrorPasswordRequired() {
        etPassword.setError(getString(R.string.login_error_field_required));
        etPassword.requestFocus();
    }

    @Override
    public void showErrorWrongPassword() {
        etPassword.setError(getString(R.string.login_error_wrong_password));
        etPassword.requestFocus();
    }

    @Override
    public void showErrorAuthorization() {
        Toast.makeText(getContext(), R.string.login_error_authorization, Toast.LENGTH_SHORT).show();
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

    @Override
    public void launchRegistrationActivity() {
        startActivity(new Intent(getContext(), RegistrationActivity.class));
    }

}
