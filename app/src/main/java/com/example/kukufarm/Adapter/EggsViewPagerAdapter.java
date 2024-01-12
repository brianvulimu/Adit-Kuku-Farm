package com.example.kukufarm.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.kukufarm.Fragment.BatchAdditionFragment;
import com.example.kukufarm.Fragment.BatchReductionFragment;
import com.example.kukufarm.Fragment.EggsAdditionFragment;
import com.example.kukufarm.Fragment.EggsReductionFragment;

public class EggsViewPagerAdapter extends FragmentStateAdapter {
    public EggsViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new EggsAdditionFragment();
            case 1:
                return new EggsReductionFragment();
            default:
                return new EggsAdditionFragment();
        }


    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
