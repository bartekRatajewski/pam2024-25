package com.example.myapplication;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ImageDialogFragment extends DialogFragment {

    private static final String ARG_IMAGE_RES_ID = "imageResId";

    public static ImageDialogFragment newInstance(int resId) {
        ImageDialogFragment fragment = new ImageDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE_RES_ID, resId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int imageResId = getArguments() != null ? getArguments().getInt(ARG_IMAGE_RES_ID) : 0;
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_image, null);
        ImageView imageView = view.findViewById(R.id.dialogImageView);
        imageView.setImageResource(imageResId);

        return new AlertDialog.Builder(requireContext())
                .setView(view)
                .setPositiveButton("Zamknij", (dialog, which) -> dialog.dismiss())
                .create();
    }
}
