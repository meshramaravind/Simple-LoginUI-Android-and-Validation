package com.arvind.loginui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import androidx.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.arvind.loginui.R;
import com.arvind.loginui.databinding.ActivityMainBinding;
import com.arvind.loginui.model.User;
import com.arvind.loginui.viewmodel.LoginViewModel;
import com.arvind.loginui.viewmodel.LoginViewModelFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doinitilization();
    }

    private void doinitilization() {
        LoginViewModel loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory(this, binding.tverrorEmailViewlogin, binding.tverrorPasswordViewlogin, binding.edEmailLogin, binding.edPasswordLogin, new User())).get(LoginViewModel.class);
        binding.setLogin(loginViewModel);
        binding.setLifecycleOwner(this);

        binding.edEmailLogin.addTextChangedListener(new MyTextWatcher(binding.edEmailLogin));
        binding.edPasswordLogin.addTextChangedListener(new MyTextWatcher(binding.edPasswordLogin));


    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @SuppressLint("NonConstantResourceId")
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.ed_email_login:
                    validateUseemail();
                    break;
                case R.id.ed_password_login:
                    validateUserpassword();
                    break;

            }
        }
    }

    private boolean validateUserpassword() {
        String password = binding.edPasswordLogin.getText().toString().trim();
        if (password.isEmpty() && isValidPassword(password)) {
            binding.tverrorPasswordViewlogin.setError(binding.tverrorPasswordViewlogin.getError());
            binding.tverrorPasswordViewlogin.setVisibility(View.VISIBLE);
            return false;
        } else {
            binding.tverrorPasswordViewlogin.setEnabled(false);
            binding.tverrorPasswordViewlogin.setVisibility(View.GONE);
            binding.tverrorPasswordViewlogin.setError(null);
        }

        return true;
    }

    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }

    private boolean validateUseemail() {
        String email = binding.edEmailLogin.getText().toString().trim();
        if (email.isEmpty() || isValidEmailaddress(email) && validmobilenumber(email)) {
            binding.tverrorEmailViewlogin.setError(binding.tverrorEmailViewlogin.getError());
            binding.tverrorEmailViewlogin.setVisibility(View.VISIBLE);
            return false;
        } else {
            binding.tverrorEmailViewlogin.setEnabled(false);
            binding.tverrorEmailViewlogin.setVisibility(View.GONE);
            binding.tverrorEmailViewlogin.setError(null);
        }

        return true;
    }

    private boolean validmobilenumber(String email) {
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");

        Matcher m = p.matcher(email);
        return (m.find() && m.group().equals(email));
    }

    private boolean isValidEmailaddress(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}