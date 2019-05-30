package com.example.scanqr.ui.CreateQR;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.scanqr.R;
import com.google.zxing.WriterException;

public class CreateQR extends AppCompatActivity {
    ImageView imageView;
    EditText editText;
    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_qr);
        imageView = findViewById(R.id.image);
        editText = findViewById(R.id.edit);
        helper = new Helper();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                Bitmap(editText.getText().toString());
            }
        });


    }


    public void Bitmap(String text) {

        Bitmap bitmap = null;
        try {
            bitmap = helper.textToImage(text, 500, 500);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bitmap);

    }


}
