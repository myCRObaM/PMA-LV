package com.example.pma2.Repository;

import android.util.Log;

import com.example.pma2.Classes.ProfesorClass;
import com.example.pma2.Classes.SpinnerSubjectClass;
import com.example.pma2.Interfaces.GetDataError;
import com.example.pma2.Interfaces.SpinnerDataReady;
import com.example.pma2.Model.InstructorsModel;
import com.example.pma2.Model.SubjectApiModel;
import com.example.pma2.Model.SubjectModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitRepository {

    SpinnerDataReady spinnerDataReady;
    GetDataError getDataError;


    public void getSubject()
    {
        RetrofitManager retrofitManager = RetrofitManager.getInstance();
        Retrofit retrofit = retrofitManager.getRetrofit();

        RetrofitGetDataInterface getInterface = retrofit.create(RetrofitGetDataInterface.class);
        Call<SubjectApiModel> call = getInterface.getSubjects();
        call.enqueue(new Callback<SubjectApiModel>() {
            @Override
            public void onResponse(Call<SubjectApiModel> call, Response<SubjectApiModel> response) {
                ArrayList<SpinnerSubjectClass> responseData = new ArrayList<>();
                ArrayList<ProfesorClass> profesorData = new ArrayList<>();
                if (!response.isSuccessful())
                {
                    getDataError.errorPopUp(Integer.toString(response.code()));
                }
                else
                {
                    SubjectApiModel subjectResponse = response.body();
                    Integer profesorId = 0;
                    Integer subjectId = 0;
                    for (SubjectModel subject: subjectResponse.courses)
                    {
                        if (subject.getInstructors() != null)
                        {
                            for (InstructorsModel instructorsModel: subject.getInstructors())
                            {
                                if (instructorsModel.getName() != "")
                                {
                                    profesorData.add(new ProfesorClass(profesorId, instructorsModel.getName()));
                                    profesorId = profesorId + 1;
                                }
                            }
                        }

                        if (subject.getTitle() != "" && !subject.getTitle().isEmpty())
                        {
                            responseData.add(new SpinnerSubjectClass(subjectId, subject.getTitle(), new ArrayList<ProfesorClass>(profesorData)));
                            subjectId = subjectId + 1;
                            profesorData.clear();
                        }

                    }
                    spinnerDataReady.subjectDataReady(responseData);
                }
            }

            @Override
            public void onFailure(Call<SubjectApiModel> call, Throwable t) {
                getDataError.errorPopUp("Error: " + t.getMessage());
                Log.e("HTTP ERROR",  t.getMessage());
            }
        });
    }

    public RetrofitRepository(SpinnerDataReady spinnerDataReady, GetDataError dataError) {
        this.spinnerDataReady = spinnerDataReady;
        this.getDataError = dataError;
    }
}
