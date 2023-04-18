package com.example.databindingtest;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class TwoWayBindingViewModel extends BaseObservable {
    private final LoginModel loginModel;
    public TwoWayBindingViewModel() {
        loginModel = new LoginModel();
        loginModel.userName = "Michael";
    }

    @Bindable
    public String getUserName() {
        return loginModel.userName;
    }

    public void setUserName(String userName) {
        if (userName != null && !userName.equals(loginModel.userName)) {
            loginModel.userName = userName;
            notifyPropertyChanged(BR.userName);
        }
    }
}
