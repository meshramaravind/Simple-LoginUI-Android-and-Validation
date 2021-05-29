package com.arvind.loginui.viewmodel;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.arvind.loginui.model.User;
import com.arvind.loginui.utils.AppNetworkStatus;
import com.google.android.material.textfield.TextInputEditText;

public class LoginViewModel extends ViewModel {
    private static final String TAG = "LoginViewModel";
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    private TextView textViewerroremail;
    private TextView textViewerrorpassword;
    private TextInputEditText editTextemail;
    private TextInputEditText editTextpassword;
    private User user;
    private Context context;

    private MutableLiveData<Integer> isLoading;

    public LoginViewModel(Context context, TextView textViewerroremailormobile, TextView textViewerrorpassword, TextInputEditText editTextemail, TextInputEditText editTextpassword, User userLogin) {
        this.user = userLogin;
        this.context = context;
        this.textViewerroremail = textViewerroremailormobile;
        this.textViewerrorpassword = textViewerrorpassword;
        this.editTextemail = editTextemail;
        this.editTextpassword = editTextpassword;

    }

    public MutableLiveData<Integer> getIsLoading() {
        if (isLoading == null) {
            isLoading = new MutableLiveData<>();
            isLoading.setValue(8);

        }
        return isLoading;
    }

    public void onLoginClick() {

        user.setEmail(email.getValue());
        user.setPassword(password.getValue());
        if (!validateUseremail() | !validateUserpassword()) {
            return;

        } else {
            getLogin();
        }
    }

    private void getLogin() {
        if (AppNetworkStatus.isNetworkConnected(context)) {
            getIsLoading().setValue(0);
            //call your api
        } else {
            Toast.makeText(context, "No Internet connection.!", Toast.LENGTH_LONG).show();
        }

    }

    private boolean validateUseremail() {
        if (!user.isValidEmail()) {
            textViewerroremail.setError(textViewerroremail.getError());
            textViewerroremail.setVisibility(View.VISIBLE);
            return false;
        } else {
            textViewerroremail.setEnabled(false);
            textViewerroremail.setVisibility(View.GONE);
            textViewerroremail.setError(null);
        }

        return true;
    }

    private boolean validateUserpassword() {
        if (!user.isValidPassword()) {
            textViewerrorpassword.setError(textViewerrorpassword.getError());
            textViewerrorpassword.setVisibility(View.VISIBLE);
            return false;
        } else {
            textViewerrorpassword.setEnabled(false);
            textViewerrorpassword.setVisibility(View.GONE);
            textViewerrorpassword.setError(null);
        }

        return true;
    }

}
