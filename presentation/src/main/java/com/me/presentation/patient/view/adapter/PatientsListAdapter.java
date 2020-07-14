package com.me.presentation.patient.view.adapter;

import androidx.recyclerview.widget.DiffUtil;

import com.me.domain.patient.entity.PatientEntity;
import com.me.presentation.R;
import com.me.presentation.base.adapter.DataBindingAdapter;

public class PatientsListAdapter extends DataBindingAdapter<PatientEntity> {

    public PatientsListAdapter() {
        super(DIFF_CALLBACK);
    }

    private static DiffUtil.ItemCallback<PatientEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<PatientEntity>() {

                @Override
                public boolean areItemsTheSame(PatientEntity oldItem, PatientEntity newItem) {
                    return oldItem.getPatientId().equals(newItem.getPatientId());
                }

                @Override
                public boolean areContentsTheSame(PatientEntity oldItem, PatientEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @Override
    public int getItemViewType(int position) {
        return R.layout.patient_list_item;
    }

}
