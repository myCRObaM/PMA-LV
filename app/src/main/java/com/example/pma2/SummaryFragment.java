package com.example.pma2;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.pma2.Classes.StudentSummary;
import com.example.pma2.Interfaces.SummaryScreenInterface;

public class SummaryFragment extends Fragment implements View.OnClickListener, SummaryScreenInterface {


    Button btnDone;
    EditText inptIme;
    EditText inptImeProfesora;
    EditText inptPrezime;
    EditText inptPrezimeProfesora;
    EditText inptAkGodina;
    EditText inptPredavanja;
    EditText inptLabos;
    EditText inptDatum;
    EditText inptPredmet;
    ImageView inptImg;
    Bitmap btmpImg = null;

    StudentSummary oSummary;
    SummaryScreenInterface iSummaryScreenInterface;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        setupViews(view);

        return view;
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        setupButton();
    }

    void setupViews(View view)
    {
        btnDone = view.findViewById(R.id.btnSummaryDoneFragment);
        inptImeProfesora = view.findViewById(R.id.txtImeProfesoraSummaryFragment);
        inptPrezimeProfesora = view.findViewById(R.id.txtPrezimeProfesoraSummaryFragment);
        inptAkGodina = view.findViewById(R.id.inptAkGodinaSummaryFragment);
        inptLabos = view.findViewById(R.id.inptSatiLabosaSummaryFragment);
        inptPredavanja = view.findViewById(R.id.inptSatiPredavanjaSummaryFragment);
        inptIme = view.findViewById(R.id.txtImeSummaryFragment);
        inptPrezime = view.findViewById(R.id.txtPrezimeSummaryFragment);
        inptDatum = view.findViewById(R.id.txtDatumSummaryFragment);
        inptImg = view.findViewById(R.id.imgProfileViewSummaryFragment);
        inptPredmet = view.findViewById(R.id.inptPredmetFragmentSummary);
    }

    void setupButton()
    {
        btnDone.setOnClickListener(this::onClick);
    }

    void setupView()
    {
        inptIme.setText(oSummary.getName());
        inptPrezime.setText(oSummary.getSurname());
        inptDatum.setText(oSummary.getDate());
        inptAkGodina.setText(oSummary.getAkGodina());
        inptImeProfesora.setText(oSummary.getpName());
        inptPrezimeProfesora.setText(oSummary.getpSurname());
        inptPredavanja.setText(oSummary.getPred());
        inptLabos.setText(oSummary.getLab());
        inptPredmet.setText(oSummary.getSubject());
        if (oSummary.getProfile() != null)
        {
            inptImg.setImageBitmap(oSummary.getProfile());
        }
    }

    void setupData()
    {
        oSummary.setName(inptIme.getText().toString());
        oSummary.setSurname(inptPrezime.getText().toString());
        oSummary.setDate(inptDatum.getText().toString());
        oSummary.setpName(inptImeProfesora.getText().toString());
        oSummary.setpSurname(inptPrezimeProfesora.getText().toString());
        oSummary.setPred(inptPredavanja.getText().toString());
        oSummary.setLab(inptLabos.getText().toString());
        oSummary.setSubject(inptPredmet.getText().toString());
        oSummary.setAkGodina(inptAkGodina.getText().toString());
        if (btmpImg != null)
        {
            oSummary.setProfile(((BitmapDrawable) inptImg.getDrawable()).getBitmap());
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (oSummary == null)
        {
            oSummary = new StudentSummary("", "", "","","","","","",null, "");
        }
        setupView();
    }

    @Override
    public void onClick(View view) {
        setupData();
        iSummaryScreenInterface.didFinishSettingStudent(oSummary);
        btnDone.setOnClickListener(null);
    }

    @Override
    public void didFinishSettingStudent(StudentSummary oStudent) {
        this.oSummary = oStudent;
        setupView();
    }
}