package com.example.kukufarm;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.kukufarm.Fragment.BatchAdditionFragment;
import com.example.kukufarm.Fragment.BatchReductionFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new BatchAdditionFragment();
            case 1:
                return new BatchReductionFragment();
            default:
                return new BatchAdditionFragment();
        }


    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
