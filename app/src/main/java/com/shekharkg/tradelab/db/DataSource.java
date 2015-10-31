package com.shekharkg.tradelab.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shekharkg.tradelab.dao.DataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShekharKG on 10/31/2015.
 */
public class DataSource extends SQLiteOpenHelper {

  /**
   * Database name, database version, table name
   */
  private final static String DATABASE_NAME = "dbMapData";
  private final static int DATABASE_VERSION = 1;
  private final static String TABLE_NAME = "tblMapData";

  /**
   * Different columns of table
   */
  private final static String _id = "id";
  private final static String EXCHANGE = "exchange";
  private final static String PRODUCT = "product";
  private final static String UNDERLINE = "underline";
  private final static String EXPIRY = "expiry";
  private final static String TYPE = "type";
  private final static String STRIKE = "strike";
  private final static String LTP = "ltp";

  /**
   * Private variables of this class
   */
  private SQLiteDatabase sqLiteDatabase;
  private static DataSource dataSource;

  /**
   * Private constructor used to make this class singleton
   */
  private DataSource(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    sqLiteDatabase = getWritableDatabase();
  }

  /**
   * Static method used to return the single instance of class
   */
  public static DataSource singleton(Context context) {
    if (dataSource == null)
      dataSource = new DataSource(context);
    return dataSource;
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    //Create table query
    String CREATE_READER_TABLE = "CREATE TABLE IF NOT EXISTS "
        + TABLE_NAME + " (" + _id
        + " INTEGER PRIMARY KEY autoincrement, "
        + EXCHANGE + " TEXT, " + PRODUCT + " TEXT, "
        + UNDERLINE + " TEXT, " + EXPIRY + " TEXT, "
        + TYPE + " TEXT, " + STRIKE + " TEXT, " + LTP + " FLOAT);";
    db.execSQL(CREATE_READER_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //Drop table query
    String DROP_READER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    db.execSQL(DROP_READER_TABLE);
    onCreate(db);
  }

  /**
   * Adding a row to table
   */
  public long addData(DataModel dataModel) {
    ContentValues contentValues = new ContentValues();
    contentValues.put(EXCHANGE, dataModel.getExchange());
    contentValues.put(PRODUCT, dataModel.getProduct());
    contentValues.put(UNDERLINE, dataModel.getUnderline());
    contentValues.put(EXPIRY, dataModel.getExpiry());
    contentValues.put(TYPE, dataModel.getType());
    contentValues.put(STRIKE, dataModel.getStrike());
    contentValues.put(LTP, dataModel.getLtp());
    return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
  }

  /**
   * Select all row from table and return as a list of FileModel
   */
  public List<DataModel> selectAll() {
    List<DataModel> mapData = new ArrayList<>();
    Cursor cursor = sqLiteDatabase.query(TABLE_NAME,
        new String[]{_id, EXCHANGE, PRODUCT, UNDERLINE, EXPIRY, TYPE, STRIKE, LTP},
        null, null, null, null, null);
    while (cursor.moveToNext()) {
      mapData.add(new DataModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
          cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
          cursor.getFloat(7)));
    }
    cursor.close();
    return mapData;
  }

}


