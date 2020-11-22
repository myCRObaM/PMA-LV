package com.example.pma2.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pma2.Classes.Student;
import com.example.pma2.Classes.StudentSummary;
import com.example.pma2.R;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<StudentSummary> oStudenti;



    public CustomRecyclerAdapter(ArrayList<StudentSummary> oStudenti)
    {
        this.oStudenti = oStudenti;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_header_layout, parent, false);

            return new HeaderViewHolder(view);
        }
        else
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_student_layout, parent, false);
            return new StudentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0)
        {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.txtHeader.setText("Student");
        }
        else
        {
            StudentViewHolder studentViewHolder = (StudentViewHolder) holder;
            studentViewHolder.txtName.setText(oStudenti.get(position).getName());
            studentViewHolder.txtSurname.setText(oStudenti.get(position).getSurname());
            studentViewHolder.txtSubject.setText(oStudenti.get(position).getSubject());
        }
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount()
    {
        return oStudenti.size();
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtHeader;
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHeader = itemView.findViewById(R.id.txtHeaderText);
        }
    }


    class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtSurname;
        TextView txtSubject;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtStudentNameList);
            txtSurname = itemView.findViewById(R.id.txtStudentSurnameList);
            txtSubject = itemView.findViewById(R.id.txtStudentSubject);
        }
    }
}
