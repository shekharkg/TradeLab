package com.shekharkg.tradelab.fragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shekharkg.tradelab.R;

/**
 * Created by ShekharKG on 10/31/2015.
 */
public class AddNewEntryFragment extends Fragment {

  public AddNewEntryFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_add_new_entry, container, false);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }
}