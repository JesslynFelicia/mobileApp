package com.example.kepo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomDialog extends BottomSheetDialogFragment {
    @Override
    public void setCancelable(boolean cancelable) {
        super.setCancelable(cancelable);
    }

    private BottomDialog mListener;
    public String a;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_dialog,container,false);
        BottomDialog bottomDialog = new BottomDialog();
        TextView tvok = v.findViewById(R.id.ok);
        TextView tv_message;
        tv_message = v.findViewById(R.id.message_error);
        tv_message.setText(a);
        setCancelable(false);
        tvok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mListener.onButtonClicked("Button 1 clicked");
                dismiss();
            }
        });
        return v;
    }




}
