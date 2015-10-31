package com.shekharkg.tradelab.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;

import com.iangclifton.android.floatlabel.FloatLabel;
import com.shekharkg.tradelab.R;
import com.shekharkg.tradelab.adapter.CustomAdapter;
import com.shekharkg.tradelab.dao.DataModel;
import com.shekharkg.tradelab.db.DataSource;

import java.util.List;

/**
 * Created by ShekharKG on 10/31/2015.
 */
public class HomeFragment extends Fragment {

  private ListView listView;
  private List<DataModel> selectedDataModel;
  private DataSource dataSource;
  private LinearLayout emptyView;
  private CustomAdapter customAdapter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_home, container, false);
    listView = (ListView) v.findViewById(R.id.listView);
    emptyView = (LinearLayout) v.findViewById(R.id.emptyView);
    dataSource = DataSource.singleton(getActivity());
    return v;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    populateData();
  }

  public void populateData() {
    selectedDataModel = dataSource.selectAllSelectedItems();
    if (selectedDataModel.size() > 0) {
      emptyView.setVisibility(View.GONE);
      customAdapter = new CustomAdapter(getActivity(), selectedDataModel);
      listView.setAdapter(customAdapter);
      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          try {
            showDialog(position);
          } catch (Exception ignored) {
          }
        }
      });
    } else {
      emptyView.setVisibility(View.VISIBLE);
      listView.setAdapter(null);
    }
  }

  private void showDialog(final int position) {
    final Dialog dialog = new Dialog(getActivity());
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.list_item_dialog);
    dialog.setCancelable(false);
    final float value = selectedDataModel.get(position).getLtp();
    ImageView cancel = (ImageView) dialog.findViewById(R.id.cancelImage);
    final RadioButton rbSell = (RadioButton) dialog.findViewById(R.id.radioSell);
    RadioButton rbBuy = (RadioButton) dialog.findViewById(R.id.radioBuy);
    final FloatLabel quantity = (FloatLabel) dialog.findViewById(R.id.quantity);
    final FloatLabel amount = (FloatLabel) dialog.findViewById(R.id.amount);
    Button confirm = (Button) dialog.findViewById(R.id.confirm);
    Button delete = (Button) dialog.findViewById(R.id.delete);

    cancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialog.dismiss();
      }
    });

    amount.getEditText().setText(String.valueOf(value));
    amount.getEditText().setFocusable(false);
    amount.getEditText().setClickable(false);
    quantity.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
    quantity.getEditText().addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        try {
          amount.getEditText().setText(String.valueOf(value * (Integer.parseInt(quantity.getEditText()
              .getText().toString().trim()))));
        } catch (Exception e) {
          e.printStackTrace();
          amount.getEditText().setText(String.valueOf(value));
        }
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });


    delete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        selectedDataModel.remove(position);
        dataSource.deleteSelectedData(selectedDataModel.get(position).getId());
        customAdapter.notifyDataSetChanged();
        dialog.dismiss();
      }
    });

    confirm.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Snackbar.make(v, "You have " + (rbSell.isChecked() ? "Sell " : "Buy ") +
            quantity.getEditText().getText().toString() + " units of " +
            selectedDataModel.get(position).getUnderline() + " stocks at " +
            amount.getEditText().getText().toString(), Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
        dialog.dismiss();
      }
    });

    dialog.show();
  }
}
