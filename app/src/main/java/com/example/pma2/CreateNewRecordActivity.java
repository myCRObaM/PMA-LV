package com.example.pma2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.example.pma2.Adapters.CreateNewRecordAdapter;
import com.example.pma2.Classes.Student;
import com.example.pma2.Classes.StudentSummary;
import com.example.pma2.Classes.StudentiSingleton;
import com.example.pma2.Classes.SubjectClass;
import com.example.pma2.Enum.FragmentEnum;
import com.example.pma2.Interfaces.ButtonPressedInterface;
import com.example.pma2.Interfaces.DataReadyInterface;
import com.example.pma2.Interfaces.GetDataInterface;
import com.example.pma2.Repository.RetrofitRepository;

import java.util.ArrayList;

public class CreateNewRecordActivity extends AppCompatActivity implements GetDataInterface, ButtonPressedInterface {

    CreateNewRecordAdapter newRecordAdapter;
    private ViewPager2 viewPager;
    private Student oStudent = new Student("","","",null);
    private SubjectClass oSubject = new SubjectClass("", "","","","","");
    private StudentSummary oSummary = new StudentSummary("","","","","","","","",null,"");
    DataReadyInterface iDataReadyInterface;
    Boolean isStudentDataLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_record);
        setupViewPager();

    }

    private void setupViewPager()
    {
        newRecordAdapter = new CreateNewRecordAdapter(this);
        ArrayList<Fragment> fragments = new ArrayList<>();

        PersonalInfoFragment personalInfoFragment = new PersonalInfoFragment();
        personalInfoFragment.iGetDataInterface = this;
        personalInfoFragment.iButtonPressedInterface = this;

        StudentInfoFragment studentInfoFragment = new StudentInfoFragment();
        studentInfoFragment.iGetDataInterface = this;
        studentInfoFragment.iButtonPressedInterface = this;

        SummaryFragment summaryFragment = new SummaryFragment();
        summaryFragment.iGetDataInterface = this;
        summaryFragment.iButtonPressedInterface = this;

        fragments.add(personalInfoFragment);
        fragments.add(studentInfoFragment);
        fragments.add(summaryFragment);

        newRecordAdapter.addFragments(fragments);


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
        oStudentSingleton.AddStudent(oSummary);


        Intent intent = new Intent(CreateNewRecordActivity.this, ListScreen.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void setNextPage()
    {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }

    @Override
    public void viewReadyForData(FragmentEnum type) {
            switch (type)
            {
                case PersonalFragment:
                    iDataReadyInterface = (PersonalInfoFragment) ((CreateNewRecordAdapter) viewPager.getAdapter()).returnFragment(type);
                    iDataReadyInterface.pushData(oStudent);
                    break;
                case StudentFragment:
                    StudentInfoFragment studentInfoFragment = (StudentInfoFragment) ((CreateNewRecordAdapter) viewPager.getAdapter()).returnFragment(type);
                    iDataReadyInterface = studentInfoFragment;
                    iDataReadyInterface.pushData(oSubject);
                    if (!isStudentDataLoaded)
                    {
                        RetrofitRepository retrofitRepository = new RetrofitRepository(studentInfoFragment, studentInfoFragment);
                        retrofitRepository.getSubject();
                        isStudentDataLoaded = true;
                    }

                    break;
                case SummaryFragment:
                    oSummary = new StudentSummary(oStudent.getName(), oStudent.getSurname(), oStudent.getDate(),
                            oSubject.getpName(), oSubject.getpSurname(), oSubject.getSubject(), oSubject.getLab(), oSubject.getPred(), oStudent.getBtmpProfile(), oSubject.getAkGodina());
                    iDataReadyInterface = (SummaryFragment) ((CreateNewRecordAdapter) viewPager.getAdapter()).returnFragment(type);
                    iDataReadyInterface.pushData(oSummary);
                    break;
            }
    }

    @Override
    public void viewReturningData(Object object, FragmentEnum type) {
        switch (type)
        {
            case PersonalFragment:
                this.oStudent = (Student) object;
                break;
            case StudentFragment:
                this.oSubject = (SubjectClass) object;
                break;
            case SummaryFragment:
                StudentSummary temp = (StudentSummary) object;
                this.oStudent = new Student(temp.getName(), temp.getSurname(), temp.getDate(), temp.getProfile());
                this.oSubject = new SubjectClass(temp.getpName(), temp.getpSurname(), temp.getSubject(), temp.getPred(), temp.getLab(), temp.getAkGodina());
                break;
        }
    }

    @Override
    public void didPressButton(FragmentEnum type) {
        switch (type)
        {
            case PersonalFragment:
            case StudentFragment:
                setNextPage();
                break;
            case SummaryFragment:
                donePressed();
                break;
        }
    }
}