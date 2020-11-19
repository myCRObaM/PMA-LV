package com.example.pma2.Adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class CreateNewRecordAdapter extends FragmentStateAdapter {
    public ArrayList<Fragment> fragmentList = new ArrayList<>();

    public CreateNewRecordAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    public void addFragment(Fragment fragment)
    {
        fragmentList.add(fragment);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

}
