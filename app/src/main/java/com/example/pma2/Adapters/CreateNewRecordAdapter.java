package com.example.pma2.Adapters;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.pma2.Enum.FragmentEnum;
import com.example.pma2.PersonalInfoFragment;
import com.example.pma2.StudentInfoFragment;
import com.example.pma2.SummaryFragment;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class CreateNewRecordAdapter extends FragmentStateAdapter {
    public Map<FragmentEnum, Fragment> fragments = new EnumMap<FragmentEnum, Fragment>(FragmentEnum.class);

    public CreateNewRecordAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new ArrayList<>(fragments.values()).get(position);
    }

    public void addFragments(ArrayList<Fragment> fragments)
    {
        for (Fragment fragment: fragments) {
            if (fragment.getClass() == PersonalInfoFragment.class)
            {
                this.fragments.put(FragmentEnum.PersonalFragment, fragment);
            }
            else if (fragment.getClass() == StudentInfoFragment.class)
            {
                this.fragments.put(FragmentEnum.StudentFragment, fragment);
            }
            else if (fragment.getClass() == SummaryFragment.class)
            {
                this.fragments.put(FragmentEnum.SummaryFragment, fragment);
            }
        }
    }

    public Fragment returnFragment(FragmentEnum type)
    {
        return this.fragments.get(type);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

}
