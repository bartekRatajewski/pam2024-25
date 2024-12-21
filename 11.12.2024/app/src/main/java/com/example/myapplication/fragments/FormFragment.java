package com.example.myapplication.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.myapp.MainActivity;
import com.example.myapplication.R;

public class FormFragment extends Fragment {

    private EditText emailField, firstNameField, lastNameField;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        emailField = view.findViewById(R.id.email_field);
        firstNameField = view.findViewById(R.id.first_name_field);
        lastNameField = view.findViewById(R.id.last_name_field);
        Button submitButton = view.findViewById(R.id.submit_button);

        submitButton.setOnClickListener(v -> validateAndSubmit());

        return view;
    }

    private void validateAndSubmit() {
        String email = emailField.getText().toString().trim();
        String firstName = firstNameField.getText().toString().trim();
        String lastName = lastNameField.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)) {
            Toast.makeText(getActivity(), "Wszystkie pola muszą być wypełnione!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getActivity(), "Nieprawidłowy adres e-mail!", Toast.LENGTH_SHORT).show();
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        bundle.putString("firstName", firstName);
        bundle.putString("lastName", lastName);

        com.example.myapplication.fragments.DataFragment DataFragment = new com.example.myapplication.fragments.DataFragment();

        ((MainActivity) getActivity()).loadFragment(DataFragment);
    }
}
