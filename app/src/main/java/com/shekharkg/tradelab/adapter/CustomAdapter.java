package com.shekharkg.tradelab.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shekharkg.tradelab.R;
import com.shekharkg.tradelab.dao.DataModel;

import java.util.List;
import java.util.Random;

/**
 * Created by ShekharKG on 10/31/2015.
 */
public class CustomAdapter extends BaseAdapter {

  private Context context;
  private List<DataModel> selectedDataModel;

  public CustomAdapter(Context context, List<DataModel> selectedDataModel) {
    this.context = context;
    this.selectedDataModel = selectedDataModel;
  }

  @Override
  public int getCount() {
    return selectedDataModel.size();
  }

  @Override
  public Object getItem(int position) {
    return selectedDataModel.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View v = LayoutInflater.from(context).inflate(R.layout.list_single_row, null);
    DataModel dataModel = selectedDataModel.get(position);
    TextView titleTV = (TextView) v.findViewById(R.id.textView);
    TextView ltpTV = (TextView) v.findViewById(R.id.textView2);
    titleTV.setText(dataModel.getUnderline());
    ltpTV.setText(String.valueOf(dataModel.getLtp()));
    if (position == selectedDataModel.size() - 1)
      new UpdateLtpValue().execute();
    return v;
  }

  class UpdateLtpValue extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params) {
      DataModel dataModel = selectedDataModel.get(randInt(0, selectedDataModel.size() - 1));
      dataModel.setLtp(dataModel.getLtp() + randFloat());
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      super.onPostExecute(aVoid);
      notifyDataSetChanged();
      new UpdateLtpValue().execute();
    }
  }

  public float randFloat() {
    Random rand = new Random();
    float randomNum = rand.nextInt((99 - 1) + 1) + 7;
    return randomNum / 100;
  }

  public int randInt(int min, int max) {
    return new Random().nextInt((max - min) + 1) + min;
  }
}
