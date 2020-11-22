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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.pma2.Classes.SpinnerSubjectClass;
import com.example.pma2.Classes.Student;
import com.example.pma2.Enum.FragmentEnum;
import com.example.pma2.Interfaces.ButtonPressedInterface;
import com.example.pma2.Interfaces.DataReadyInterface;
import com.example.pma2.Interfaces.GetDataInterface;
import com.example.pma2.Interfaces.SpinnerDataReady;

import java.util.ArrayList;
import java.util.List;


public class PersonalInfoFragment extends Fragment implements View.OnClickListener, DataReadyInterface {

    GetDataInterface iGetDataInterface;
    ButtonPressedInterface iButtonPressedInterface;
    ImageView inptImage;
    Button btnNext;
    EditText inptIme;
    EditText inptPrezime;
    EditText inptDatum;
    Bitmap btmpImg = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_personal_info, container, false);
        setupViews(view);
        setupButton();

        return view;
    }

    void setupViews(View view)
    {
        btnNext = view.findViewById(R.id.btnPersonalDaljeFragment);
        inptIme = view.findViewById(R.id.txtImeFragment);
        inptPrezime = view.findViewById(R.id.txtPrezimeFragment);
        inptDatum = view.findViewById(R.id.txtDatumFragment);
        inptImage = view.findViewById(R.id.imgProfileViewFragment);
    }

    void setupButton()
    {
        btnNext.setOnClickListener(this::onClick);

        inptImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
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
            btmpImg = imageBitmap;
            inptImage.setImageBitmap(imageBitmap);
        }
    }

    private void setupData(Student oStudent)
    {
        if (oStudent.getBtmpProfile() != null)
        {
            inptImage.setImageBitmap(oStudent.getBtmpProfile());
        }
        inptIme.setText(oStudent.getName());
        inptPrezime.setText(oStudent.getSurname());
        inptDatum.setText(oStudent.getDate());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getView() != null)
        {
            iGetDataInterface.viewReadyForData(FragmentEnum.PersonalFragment);
            setupButton();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        btnNext.setOnClickListener(null);
        inptImage.setOnClickListener(null);
        if (inptImage.getDrawable().getClass() == VectorDrawable.class)
        {
            inptImage.setImageBitmap(getBitmap((VectorDrawable) inptImage.getDrawable()));
        }
        iGetDataInterface.viewReturningData(new Student(inptIme.getText().toString(), inptPrezime.getText().toString(), inptDatum.getText().toString(), ((BitmapDrawable) inptImage.getDrawable()).getBitmap()), FragmentEnum.PersonalFragment);
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
    public void onClick(View view) {
        iButtonPressedInterface.didPressButton(FragmentEnum.PersonalFragment);
    }

    @Override
    public void pushData(Object object) {
        setupData((Student) object);
    }

}