package com.shekharkg.tradelab.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.iangclifton.android.floatlabel.FloatLabel;
import com.shekharkg.tradelab.R;

/**
 * Created by ShekharKG on 10/31/2015.
 */
public class AddNewEntryFragment extends Fragment {

  private FloatLabel exchange, product, underline, expiry, type, strike, lta;
  private Button addButton;

  public AddNewEntryFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_add_new_entry, container, false);
    exchange = (FloatLabel) v.findViewById(R.id.exchange);
    product = (FloatLabel) v.findViewById(R.id.product);
    underline = (FloatLabel) v.findViewById(R.id.underline);
    expiry = (FloatLabel) v.findViewById(R.id.expiry);
    type = (FloatLabel) v.findViewById(R.id.type);
    strike = (FloatLabel) v.findViewById(R.id.strike);
    lta = (FloatLabel) v.findViewById(R.id.lta);
    addButton = (Button) v.findViewById(R.id.addButton);
    return v;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }
}