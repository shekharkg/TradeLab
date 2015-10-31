package com.shekharkg.tradelab.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.iangclifton.android.floatlabel.FloatLabel;
import com.shekharkg.tradelab.R;
import com.shekharkg.tradelab.dao.DataModel;
import com.shekharkg.tradelab.db.DataSource;

/**
 * Created by ShekharKG on 10/31/2015.
 */
public class AddNewEntryFragment extends Fragment {

  private FloatLabel exchange, product, underline, expiry, type, strike, lta;
  private Button addButton;
  private DataSource dataSource;

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
    dataSource = DataSource.singleton(getActivity());

    addButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (validateForm()) {
          dataSource.addData(new DataModel(-1, exchange.getEditText().getText().toString().trim(),
              product.getEditText().getText().toString().trim(),
              underline.getEditText().getText().toString().trim(),
              expiry.getEditText().getText().toString().trim(),
              type.getEditText().getText().toString().trim(),
              strike.getEditText().getText().toString().trim(),
              Float.parseFloat(lta.getEditText().getText().toString().trim())));

          exchange.getEditText().setText("");
          product.getEditText().setText("");
          underline.getEditText().setText("");
          expiry.getEditText().setText("");
          type.getEditText().setText("");
          strike.getEditText().setText("");
          lta.getEditText().setText("");

          Snackbar.make(v, "Details added successfully. Count is : " + dataSource.selectAll().size(), Snackbar.LENGTH_LONG)
              .setAction("Action", null).show();
        }
      }
    });
  }

  private boolean validateForm() {
    boolean isValid = true;
    if (exchange.getEditText().getText().toString().isEmpty()) {
      showError(exchange.getEditText());
      isValid = false;
    }

    if (product.getEditText().getText().toString().isEmpty()) {
      showError(product.getEditText());
      isValid = false;
    }

    if (underline.getEditText().getText().toString().isEmpty()) {
      showError(underline.getEditText());
      isValid = false;
    }

    if (expiry.getEditText().getText().toString().isEmpty()) {
      showError(expiry.getEditText());
      isValid = false;
    }

    if (type.getEditText().getText().toString().isEmpty()) {
      showError(type.getEditText());
      isValid = false;
    }

    if (strike.getEditText().getText().toString().isEmpty()) {
      showError(strike.getEditText());
      isValid = false;
    }

    if (lta.getEditText().getText().toString().isEmpty()) {
      showError(lta.getEditText());
      isValid = false;
    }

    return isValid;
  }

  private void showError(EditText editText) {
    editText.setError("required Filed!");
  }
}