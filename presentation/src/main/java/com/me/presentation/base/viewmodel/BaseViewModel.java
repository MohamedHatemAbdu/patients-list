package com.me.presentation.base.viewmodel;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }

}
