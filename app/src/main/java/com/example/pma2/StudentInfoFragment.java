package com.example.pma2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pma2.Classes.SpinnerSubjectClass;
import com.example.pma2.Classes.SubjectClass;
import com.example.pma2.Enum.FragmentEnum;
import com.example.pma2.Interfaces.ButtonPressedInterface;
import com.example.pma2.Interfaces.DataReadyInterface;
import com.example.pma2.Interfaces.GetDataInterface;
import com.example.pma2.Interfaces.SpinnerDataReady;

import java.util.ArrayList;
import java.util.List;

public class StudentInfoFragment extends Fragment implements View.OnClickListener, DataReadyInterface, SpinnerDataReady {

    GetDataInterface iGetDataInterface;
    ButtonPressedInterface iButtonPressedInterface;
    Button btnNext;
    EditText inptImeProfesora;
    EditText inptPrezimeProfesora;
    EditText inptAkGodina;
    EditText inptLabos;
    EditText inptPredavanja;
    EditText inptImePredmeta;
    Spinner subjectSpinner;

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
        inptImeProfesora = view.findViewById(R.id.txtImeProfesoraFragment);
        inptPrezimeProfesora = view.findViewById(R.id.txtPrezimeProfesoraFragment);
        inptAkGodina = view.findViewById(R.id.inptAkGodinaFragment);
        inptLabos = view.findViewById(R.id.inptSatiLabosaFragment);
        inptPredavanja = view.findViewById(R.id.inptSatiPredavanjaFragment);
        inptImePredmeta = view.findViewById(R.id.inptPredmetFragment);
        subjectSpinner = view.findViewById(R.id.spinnerSubject);
    }

    void setupData(SubjectClass oSubject)
    {
        inptImeProfesora.setText(oSubject.getpName());
        inptPrezimeProfesora.setText(oSubject.getpSurname());
        inptImePredmeta.setText(oSubject.getSubject());
        inptAkGodina.setText(oSubject.getAkGodina());
        inptLabos.setText(oSubject.getLab());
        inptPredavanja.setText(oSubject.getPred());
    }

    void setupButton()
    {
        btnNext.setOnClickListener(this::onClick);
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
        iGetDataInterface.viewReturningData(new SubjectClass(inptImeProfesora.getText().toString(), inptPrezimeProfesora.getText().toString(),
                inptImePredmeta.getText().toString(), inptPredavanja.getText().toString(), inptLabos.getText().toString(), inptAkGodina.getText().toString()), FragmentEnum.StudentFragment);
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
    public void subjectDataReady(List<SpinnerSubjectClass> subjects) {
        ArrayList<String> spinnerStrings = new ArrayList<>();
        for (SpinnerSubjectClass subject: subjects)
        {
            spinnerStrings.add(subject.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerStrings);
        subjectSpinner.setAdapter(adapter);
    }

    @Override
    public void teacherDataReady(List<SpinnerSubjectClass> teachers) {

    }
}