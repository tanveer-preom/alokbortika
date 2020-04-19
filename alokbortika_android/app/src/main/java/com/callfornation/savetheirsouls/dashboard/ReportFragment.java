package com.callfornation.savetheirsouls.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.callfornation.savetheirsouls.R;

public class ReportFragment extends Fragment {

    String[] complains = { "ডেলিভারি বিলম্বিত সঙ্ক্রান্ত", "ওজনে গরমিল ও দুর্নীতি", "অনাকাঙ্ক্ষিত আচার ব্যবহার", "অন্যান্য"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_report, container, false);
        initializeComponent(fragmentView);
        return fragmentView;
    }

    private void initializeComponent(View fragmentView) {
        Spinner spComplain = (Spinner) fragmentView.findViewById(R.id.spComplain);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,complains);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spComplain.setAdapter(adapter);

        fragmentView.findViewById(R.id.btnSend).setOnClickListener(v -> {
            Toast.makeText(getContext(), R.string.reportSuccessfullySubmitted, Toast.LENGTH_LONG).show();
        });
    }
}
