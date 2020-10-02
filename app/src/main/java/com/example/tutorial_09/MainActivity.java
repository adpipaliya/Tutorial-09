package com.example.tutorial_09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    final String FILE_ASSETS="data.json";
    final String FILE_INTERNAL="data.txt";
    EditText edtTextData;
    TextView txtDisplayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTextData = findViewById(R.id.edtTextData);
        txtDisplayData = findViewById(R.id.txtDisplayData);
    }

    public void readAssets(View view) {
        try {
            InputStream inputStream = getAssets().open(FILE_ASSETS);
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            StringBuilder sb = new StringBuilder();
            String temp = "";
            while ((temp = bufferedReader.readLine())!=null){
                sb.append(temp);
            }

            txtDisplayData.setText(sb.toString());
            inputStream.close();
            reader.close();
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFiles(View view) {
        try {
            FileOutputStream fout =  openFileOutput(FILE_INTERNAL, Context.MODE_PRIVATE);;
            String data = edtTextData.getText().toString();
            fout.write(data.getBytes());
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readFiles(View view) {
        try {
            FileInputStream fin = openFileInput(FILE_INTERNAL);
            int c;
            String temp = "";
            while ((c = fin.read()) != -1){
                temp = temp + String.valueOf((char) c);
            }
            txtDisplayData.setText(temp);
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}