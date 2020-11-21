package com.example.pma2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.pma2.Classes.StudentSummary;
import com.example.pma2.Enum.FragmentEnum;
import com.example.pma2.Interfaces.ButtonPressedInterface;
import com.example.pma2.Interfaces.DataReadyInterface;
import com.example.pma2.Interfaces.GetDataInterface;

public class SummaryFragment extends Fragment implements View.OnClickListener, DataReadyInterface {


    GetDataInterface iGetDataInterface;
    ButtonPressedInterface iButtonPressedInterface;
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
        setupButton();

        return view;
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
        inptImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
    }

    void setupData(StudentSummary oSummary)
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

    static final int REQUEST_IMAGE_CAPTURE = 1;
    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            inptImg.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getView() != null)
        {

            iGetDataInterface.viewReadyForData(FragmentEnum.SummaryFragment);
            setupButton();
        }
    }

    private static Bitmap getBitmap(VectorDrawable vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }

    @Override
    public void onPause() {
        super.onPause();
        btnDone.setOnClickListener(null);
        if (inptImg.getDrawable().getClass() == VectorDrawable.class)
        {
            inptImg.setImageBitmap(getBitmap((VectorDrawable) inptImg.getDrawable()));
        }
        iGetDataInterface.viewReturningData(new StudentSummary(inptIme.getText().toString(), inptPrezime.getText().toString(), inptDatum.getText().toString(),
                inptImeProfesora.getText().toString(), inptPrezimeProfesora.getText().toString(), inptPredmet.getText().toString(), inptLabos.getText().toString(),
                inptPredavanja.getText().toString(), ((BitmapDrawable) inptImg.getDrawable()).getBitmap(), inptAkGodina.getText().toString()), FragmentEnum.SummaryFragment);
    }

    @Override
    public void onClick(View view) {
        iButtonPressedInterface.didPressButton(FragmentEnum.SummaryFragment);
    }

    @Override
    public void pushData(Object object) {
        setupData((StudentSummary) object);
    }
}