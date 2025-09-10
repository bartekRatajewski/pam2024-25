package com.example.myapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Fragment1 extends Fragment {

    private EditText emailEditText, firstNameEditText, lastNameEditText;
    private Button submitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        // Inicjalizacja widoków
        emailEditText = view.findViewById(R.id.emailEditText);
        firstNameEditText = view.findViewById(R.id.firstNameEditText);
        lastNameEditText = view.findViewById(R.id.lastNameEditText);
        submitButton = view.findViewById(R.id.submitButton);

        // Ustawienie kliknięcia przycisku
        submitButton.setOnClickListener(v -> {
            // Walidacja danych
            String email = emailEditText.getText().toString().trim();
            String firstName = firstNameEditText.getText().toString().trim();
            String lastName = lastNameEditText.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)) {
                Toast.makeText(getActivity(), "Wszystkie pola muszą być wypełnione", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(getActivity(), "Niepoprawny adres e-mail", Toast.LENGTH_SHORT).show();
                return;
            }

            // Przejście do Fragment2 i przekazanie danych
            Fragment2 fragment2 = new Fragment2();

            Bundle bundle = new Bundle();
            bundle.putString("email", email);
            bundle.putString("first_name", firstName);
            bundle.putString("last_name", lastName);
            fragment2.setArguments(bundle);

            // Zmiana fragmentu
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment2);
            transaction.addToBackStack(null); // Opcjonalnie: umożliwia powrót do poprzedniego fragmentu
            transaction.commit();
        });

        return view;
    }
}
