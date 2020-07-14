package com.me.presentation.setting.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.me.presentation.R;
import com.me.presentation.base.utils.Helpers;
import com.me.presentation.base.viewmodel.ViewModelFactory;
import com.me.presentation.setting.view.viewmodel.SettingsViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

public class UsersSettingsFragment extends Fragment {

    @BindView(R.id.tidUserName)
    TextInputEditText tidUserName;

    @BindView(R.id.tidCurrNumberPatients)
    TextInputEditText tidCurrNumberPatients;

    @BindView(R.id.tidMaxPatients)
    TextInputEditText tidMaxPatients;


    @Inject
    public ViewModelFactory<SettingsViewModel> vmFactory;

    private SettingsViewModel vm = null;

    public synchronized SettingsViewModel getVm() {
        if (vm == null) {
            vm = ViewModelProviders.of(this, vmFactory)
                    .get(SettingsViewModel.class);
        }
        return vm;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, root);
        return root;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUiWidgets();
    }


    private void initUiWidgets() {

        tidUserName.setText(getVm().getCurrUserName());
        tidCurrNumberPatients.setText(String.valueOf(getVm().getPatientsCount()));
        tidMaxPatients.setText(String.valueOf(getVm().getMaxNumberPatients()));
    }

    @OnClick(R.id.btnSave)
    public void onSaveClick(Button button) {
        String curerName = tidUserName.getText().toString();
        String maxNumberPatients = tidMaxPatients.getText().toString();



        getVm().setCurrUserName(curerName);
        try {
            getVm().setMaxNumberPatients(Integer.parseInt(maxNumberPatients));

        } catch (Exception ex) {
            Helpers.showToast(getContext(), "Enter a valid max number of patients");
        }
        Helpers.showToast(getContext(), "Max :" + maxNumberPatients+" username: "+ curerName);

    }




}
