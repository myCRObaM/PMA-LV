package com.example.pma2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.pma2.Classes.Student;
import com.example.pma2.Interfaces.PersonalInfoInterface;


public class PersonalInfoFragment extends Fragment implements View.OnClickListener {

    PersonalInfoInterface iPersonalInfoInterface;
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

        return view;
    }

    void setupViews(View view)
    {
        btnNext = view.findViewById(R.id.btnPersonalDaljeFragment);
        inptIme = view.findViewById(R.id.txtImeFragment);
        inptPrezime = view.findViewById(R.id.txtPrezimeFragment);
        inptDatum = view.findViewById(R.id.txtDatumFragment);
        inptImage = view.findViewById(R.id.imgProfileViewFragment);

        inptImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        btnNext.setOnClickListener(this::onClick);
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


    @Override
    public void onClick(View view) {
        iPersonalInfoInterface.didSetPersonalInfo(new Student(inptIme.getText().toString(), inptPrezime.getText().toString(), inptDatum.getText().toString(), btmpImg));
        btnNext.setOnClickListener(null);
    }
}