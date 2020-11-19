package com.example.pma2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.example.pma2.Adapters.CreateNewRecordAdapter;
import com.example.pma2.Classes.Student;
import com.example.pma2.Classes.StudentSummary;
import com.example.pma2.Classes.StudentiSingleton;
import com.example.pma2.Classes.SubjectClass;
import com.example.pma2.Interfaces.PersonalInfoInterface;
import com.example.pma2.Interfaces.StudentInfoInterface;
import com.example.pma2.Interfaces.SummaryScreenInterface;

public class CreateNewRecordActivity extends AppCompatActivity implements PersonalInfoInterface, StudentInfoInterface, SummaryScreenInterface {

    CreateNewRecordAdapter newRecordAdapter;
    private ViewPager2 viewPager;
    private Student oStudent;
    private SubjectClass oSubject;
    private StudentSummary oSummary;
    SummaryScreenInterface iSummaryScreenInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_record);
        setupViewPager();

    }

    private void setupViewPager()
    {
        newRecordAdapter = new CreateNewRecordAdapter(this);

        PersonalInfoFragment personalInfoFragment = new PersonalInfoFragment();
        personalInfoFragment.iPersonalInfoInterface = this::didSetPersonalInfo;

        StudentInfoFragment studentInfoFragment = new StudentInfoFragment();
        studentInfoFragment.iStudentInfoInterface = this::didSetStudentInfo;

        SummaryFragment summaryFragment = new SummaryFragment();
        summaryFragment.iSummaryScreenInterface = this::didFinishSettingStudent;
        this.iSummaryScreenInterface = summaryFragment;

        newRecordAdapter.addFragment(personalInfoFragment);
        newRecordAdapter.addFragment(studentInfoFragment);
        newRecordAdapter.addFragment(summaryFragment);


        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(newRecordAdapter);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0)
        {
            super.onBackPressed();
        }
        else
        {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }


    public void donePressed()
    {
        StudentiSingleton oStudentSingleton = StudentiSingleton.getInstance();
        oStudentSingleton.AddStudent(new StudentSummary(oSummary.getName(), oSummary.getSurname(), oSummary.getDate(),
                oSummary.getpName(), oSummary.getpSurname(), oSummary.getSubject(), oSummary.getLab(), oSummary.getPred(), oSummary.getProfile(), oSummary.getAkGodina()));


        Intent intent = new Intent(CreateNewRecordActivity.this, ListScreen.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void setNextPage()
    {
        if (viewPager.getCurrentItem() == 1)
        {
            iSummaryScreenInterface.didFinishSettingStudent(new StudentSummary(oStudent.getName(), oStudent.getSurname(), oStudent.getDate(),
                    oSubject.getpName(), oSubject.getpSurname(), oSubject.getSubject(), oSubject.getLab(), oSubject.getPred(), oStudent.getBtmpProfile(), oSubject.getAkGodina()));
        }
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

    }

    @Override
    public void didSetPersonalInfo(Student oStudent) {
        this.oStudent = oStudent;
        setNextPage();
    }

    @Override
    public void didSetStudentInfo(SubjectClass oSubject) {
        this.oSubject = oSubject;
        setNextPage();
    }

    @Override
    public void didFinishSettingStudent(StudentSummary oStudent) {
        this.oSummary = oStudent;
        donePressed();
    }
}