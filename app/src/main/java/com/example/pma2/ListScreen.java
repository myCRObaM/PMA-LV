package com.example.pma2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.pma2.Adapters.CustomRecyclerAdapter;
import com.example.pma2.Classes.Student;
import com.example.pma2.Classes.StudentSummary;
import com.example.pma2.Classes.StudentiSingleton;

import java.util.ArrayList;
import java.util.Locale;

public class ListScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button oNextButton;
    RecyclerView oRecyclerView;
    Spinner oSpinner;
    Integer check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);
        setupViews();
    }

    private void setupViews()
    {
        oNextButton = findViewById(R.id.btnListDalje);
        oRecyclerView = findViewById(R.id.recyclerViewList);
        oSpinner = findViewById(R.id.lngSpinner);
        setupSpinner();
        setupRecView();
        setupButton();
    }

    private void setupSpinner()
    {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lang_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        oSpinner.setAdapter(adapter);
        oSpinner.setOnItemSelectedListener(this);
        Locale current = getResources().getConfiguration().locale;

        if(current.getLanguage() == "en") {
            oSpinner.setSelection(0);
        }
        if(current.getLanguage() == "hr") {
            oSpinner.setSelection(1);
        }
        if(current.getLanguage() == "hu") {
            oSpinner.setSelection(2);
        }
    }

    private void setupRecView()
    {
        StudentiSingleton oStudentiSingle = StudentiSingleton.getInstance();
        ArrayList<StudentSummary> oStudents = oStudentiSingle.GetStudents();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        oRecyclerView.setLayoutManager(mLayoutManager);
        CustomRecyclerAdapter oAdapter = new CustomRecyclerAdapter(oStudents);
        oRecyclerView.setAdapter(oAdapter);
    }

    private void setupButton()
    {
        oNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ListScreen.this, CreateNewRecordActivity.class);
                startActivity(myIntent);
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        setLocale(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        return;
    }


    public void setLocale(Integer pos) {
        ArrayList<String> strLocale = new ArrayList<String>();
        strLocale.add("en");
        strLocale.add("hr");
        strLocale.add("hu");

        setAppLocale(strLocale.get(pos));
        if (++check > 1)
        {
            finish();
            startActivity(getIntent());
        }



    }


    @SuppressWarnings("deprecation")
    private void setAppLocale (String localeCode)
    {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(new Locale(localeCode.toLowerCase()));
        }
        else {
            conf.locale = new Locale(localeCode.toLowerCase());
        }
        res.updateConfiguration(conf, dm);
    }
}