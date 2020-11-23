package com.example.pma2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pma2.Classes.ProfesorClass;
import com.example.pma2.Classes.SpinnerSubjectClass;
import com.example.pma2.Classes.SubjectClass;
import com.example.pma2.Enum.FragmentEnum;
import com.example.pma2.Interfaces.ButtonPressedInterface;
import com.example.pma2.Interfaces.DataReadyInterface;
import com.example.pma2.Interfaces.GetDataError;
import com.example.pma2.Interfaces.GetDataInterface;
import com.example.pma2.Interfaces.SpinnerDataReady;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class StudentInfoFragment extends Fragment implements View.OnClickListener, DataReadyInterface, SpinnerDataReady, GetDataError {

    GetDataInterface iGetDataInterface;
    ButtonPressedInterface iButtonPressedInterface;
    Button btnNext;
    EditText inptAkGodina;
    EditText inptLabos;
    EditText inptPredavanja;
    Spinner subjectSpinner;
    Spinner teacherSpinner;
    ArrayList<SpinnerSubjectClass> subjects;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_info, container, false);
        setupViews(view);
        setupButton();

        return view;
    }

    void setupViews(View view)
    {
        btnNext = view.findViewById(R.id.btnStudentDaljeFragment);
        inptAkGodina = view.findViewById(R.id.inptAkGodinaFragment);
        inptLabos = view.findViewById(R.id.inptSatiLabosaFragment);
        inptPredavanja = view.findViewById(R.id.inptSatiPredavanjaFragment);
        subjectSpinner = view.findViewById(R.id.spinnerSubject);
        teacherSpinner = view.findViewById(R.id.spinnerTeacher);
    }

    void setupData(SubjectClass oSubject)
    {
        inptAkGodina.setText(oSubject.getAkGodina());
        inptLabos.setText(oSubject.getLab());
        inptPredavanja.setText(oSubject.getPred());
    }

    void setupButton()
    {
        btnNext.setOnClickListener(this::onClick);
        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setupTeacherSpinner(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    void setupTeacherSpinner(Integer i)
    {
        ArrayList<String> spinnerStrings = new ArrayList<>();
        if (this.subjects.get(i).getTeachers().isEmpty())
        {
            spinnerStrings.add("-");
        }
        else
        {
            for (ProfesorClass teacher: this.subjects.get(i).getTeachers())
            {
                spinnerStrings.add(teacher.getName());
            }
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerStrings);
        teacherSpinner.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getView() != null)
        {
            iGetDataInterface.viewReadyForData(FragmentEnum.StudentFragment);
            setupButton();
        }
    }



    @Override
    public void onPause() {
        super.onPause();
        String[] teacherName = new String[]{"", ""};
        if (teacherSpinner.getSelectedItem() != null)
        {
            if (teacherSpinner.getSelectedItem().toString() != "-")
            {
                teacherName = teacherSpinner.getSelectedItem().toString().split(" ");
            }
            else
            {
                teacherName = new String[]{"-", "-"};
            }

        }
        String subjectName = "";
        if (subjectSpinner.getSelectedItem() != null)
        {
            subjectName = (String) subjectSpinner.getSelectedItem();
        }
        iGetDataInterface.viewReturningData(new SubjectClass(teacherName[0], teacherName[1],
                subjectName, inptPredavanja.getText().toString(), inptLabos.getText().toString(), inptAkGodina.getText().toString()), FragmentEnum.StudentFragment);
        btnNext.setOnClickListener(null);
    }

    @Override
    public void onClick(View view) {
        iButtonPressedInterface.didPressButton(FragmentEnum.StudentFragment);
    }

    @Override
    public void pushData(Object object) {
        setupData((SubjectClass) object);
    }

    @Override
    public void subjectDataReady(ArrayList<SpinnerSubjectClass> subjects) {
        this.subjects = subjects;
        ArrayList<String> spinnerStrings = new ArrayList<>();
        for (SpinnerSubjectClass subject: subjects)
        {
                spinnerStrings.add(subject.getTitle());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerStrings);
        subjectSpinner.setAdapter(adapter);
    }

    @Override
    public void errorPopUp(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }
}