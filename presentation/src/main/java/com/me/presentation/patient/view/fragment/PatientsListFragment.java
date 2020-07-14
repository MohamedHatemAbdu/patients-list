package com.me.presentation.patient.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.me.domain.patient.entity.PatientEntity;
import com.me.presentation.R;
import com.me.presentation.base.model.Resource;
import com.me.presentation.base.model.ResourceState;
import com.me.presentation.base.utils.Helpers;
import com.me.presentation.base.viewmodel.ViewModelFactory;
import com.me.presentation.patient.view.adapter.PatientsListAdapter;
import com.me.presentation.patient.view.viewmodel.PatientsViewModel;
import com.me.presentation.setting.view.viewmodel.SettingsViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

public class PatientsListFragment extends Fragment {

    @BindView(R.id.tidPatientFullName)
    TextInputEditText tidPatientFullName;

    @BindView(R.id.tidPatientAge)
    TextInputEditText tidPatientAge;

    @BindView(R.id.tidPatientEmail)
    TextInputEditText tidPatientEmail;

    @BindView(R.id.rgGender)
    RadioGroup rgGender;

    @BindView(R.id.rvPatientsList)
    RecyclerView rvPatientsList;

    @BindView(R.id.tvPatientError)
    TextView tvPatientError;

    @Inject
    public PatientsListAdapter adapter;


    @Inject
    public ViewModelFactory<PatientsViewModel> patientsVmFactory;

    private PatientsViewModel patientsVM = null;

    public synchronized PatientsViewModel getPatientsVM() {
        if (patientsVM == null) {
            patientsVM = ViewModelProviders.of(this, patientsVmFactory)
                    .get(PatientsViewModel.class);
        }
        return patientsVM;
    }

    @Inject
    public ViewModelFactory<SettingsViewModel> settingsVmFactory;

    private SettingsViewModel settingsVM = null;

    public synchronized SettingsViewModel getSettingsVM() {
        if (settingsVM == null) {
            settingsVM = ViewModelProviders.of(this, settingsVmFactory)
                    .get(SettingsViewModel.class);
        }
        return settingsVM;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_patient_list, container, false);
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

        rvPatientsList.setAdapter(adapter);

        if (!getSettingsVM().getCurrUserName().isEmpty())
            Helpers.showToast(getContext(), "Hi Again, " + getSettingsVM().getCurrUserName());


        getPatientsVM().getLdPatientsList().observe(this, patientListResource -> {
            updatePatientsList(patientListResource);
        });

        if (savedInstanceState == null) {
            getPatientsVM().getPatientList();
        }
    }


    private void updatePatientsList(Resource<List<PatientEntity>> resource) {

        if (resource != null) {

            if (resource.getState() == ResourceState.SUCCESS) {
                if (resource.getData().isEmpty()) {
                    tvPatientError.setVisibility(View.VISIBLE);
                } else {
                    tvPatientError.setVisibility(View.GONE);
                }
                adapter.submitList(resource.getData());
            } else if (resource.getState() == ResourceState.ERROR) {
                if (resource.getMessage() != null && !resource.getMessage().isEmpty()) {
                    Helpers.showToast(getContext(), resource.getMessage());
                }
            }
        }

    }

    @OnClick(R.id.fabAddPatient)
    public void onAddPatientClick(FloatingActionButton button) {
        String patientName = tidPatientFullName.getText().toString();
        String patientAge = tidPatientAge.getText().toString();
        String patientEmail = tidPatientEmail.getText().toString();
        char gender = ' ';

        int selectedId = rgGender.getCheckedRadioButtonId();
        if (selectedId == R.id.rbMale) {
            gender = 'm';
        } else if (selectedId == R.id.rbFemale) {
            gender = 'f';
        } else if (selectedId == R.id.rbOther) {
            gender = 'o';
        }

        if (isFieldsValid(patientName, patientAge, patientEmail, gender)) {
            getPatientsVM().addPatient(new PatientEntity(patientName, patientEmail,
                    Integer.parseInt(patientAge), gender));
        }


    }

    private boolean isFieldsValid(String patientName,
                                  String patientAge,
                                  String patientEmail,
                                  char gender) {
        boolean isValid = true;

        if (patientName == null || patientName.isEmpty()) {
            Helpers.showToast(getContext(), "patientName shall not be empty");
            isValid = false;
        } else if (patientAge == null || patientAge.isEmpty()) {
            Helpers.showToast(getContext(), "patientAge shall not be empty");
            isValid = false;

        } else if (patientEmail == null || patientEmail.isEmpty()) {
            Helpers.showToast(getContext(), "patientEmail shall not be empty");
            isValid = false;

        } else if (gender == ' ') {
            Helpers.showToast(getContext(), "gender shall not be empty");
            isValid = false;

        } else {
            try {
                Integer.parseInt(patientAge);

                if (Integer.parseInt(patientAge) <= 0) {
                    Helpers.showToast(getContext(), "age shall be > 0");
                    isValid = false;
                }
            } catch (Exception ex) {
                Helpers.showToast(getContext(), "patientAge shall  be integer");

            }
        }
        return isValid;
    }

}
