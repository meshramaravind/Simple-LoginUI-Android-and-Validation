package com.arvind.loginui.viewmodel;

import android.content.Context;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.arvind.loginui.model.User;
import com.arvind.loginui.view.MainActivity;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private User usermodel;
    private Context context;
    private AppCompatTextView textViewerroremail;
    private AppCompatTextView textViewerrorpassword;
    private TextInputEditText editTextemail;
    private TextInputEditText editTextpassword;

    public LoginViewModelFactory(Context context, AppCompatTextView tverrorEmailViewlogin, AppCompatTextView tverrorPasswordViewlogin, TextInputEditText editTextemail, TextInputEditText edPasswordLogin, User user) {
        this.context = context;
        this.usermodel = user;
        this.textViewerroremail = tverrorEmailViewlogin;
        this.textViewerrorpassword = tverrorPasswordViewlogin;
        this.editTextemail = editTextemail;
        this.editTextpassword = edPasswordLogin;
    }


    @NotNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new LoginViewModel(context, textViewerroremail, textViewerrorpassword, editTextemail, editTextpassword, usermodel);
    }
}
