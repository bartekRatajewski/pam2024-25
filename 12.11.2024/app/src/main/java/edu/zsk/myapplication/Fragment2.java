package com.example.myapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    private TextView emailTextView, firstNameTextView, lastNameTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        // Inicjalizacja widoków
        emailTextView = view.findViewById(R.id.emailTextView);
        firstNameTextView = view.findViewById(R.id.firstNameTextView);
        lastNameTextView = view.findViewById(R.id.lastNameTextView);

        // Pobieranie danych z poprzedniego fragmentu
        Bundle bundle = getArguments();
        if (bundle != null) {
            String email = bundle.getString("email");
            String firstName = bundle.getString("first_name");
            String lastName = bundle.getString("last_name");

            emailTextView.setText("E-mail: " + email);
            firstNameTextView.setText("Imię: " + firstName);
            lastNameTextView.setText("Nazwisko: " + lastName);
        }

        return view;
    }
}
