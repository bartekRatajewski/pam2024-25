package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.myapplication.R;

public class DataFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);

        TextView emailLabel = view.findViewById(R.id.email_label);
        TextView firstNameLabel = view.findViewById(R.id.first_name_label);
        TextView lastNameLabel = view.findViewById(R.id.last_name_label);

        Bundle args = getArguments();
        if (args != null) {
            emailLabel.setText(args.getString("email"));
            firstNameLabel.setText(args.getString("firstName"));
            lastNameLabel.setText(args.getString("lastName"));
        }

        return view;
    }
}
