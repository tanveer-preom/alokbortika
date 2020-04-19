package com.callfornation.savetheirsouls.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.callfornation.savetheirsouls.R;

public class RequestFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_request, container, false);
        initViewComponents(fragmentView);
        return fragmentView;
    }

    private void initViewComponents(View fragmentView) {
        fragmentView.findViewById(R.id.btnMakeRequest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                fragmentView.findViewById(R.id.tvEmptyRequest).setVisibility(View.GONE);
                fragmentView.findViewById(R.id.llDetails).setVisibility(View.VISIBLE);

            }
        });

        fragmentView.findViewById(R.id.btnGetQrCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), QRCodeActivity.class));
            }
        });
    }
}
