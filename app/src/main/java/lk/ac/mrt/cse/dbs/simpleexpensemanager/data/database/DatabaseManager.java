package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "expense_manager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME_ONE = "account";
    private static final String COLUMN_ACCOUNT_NUMBER = "account_number";
    private static final String COLUMN_EXPENSE_TYPE = "expense_type";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_DATE = "date";
    private static final String TABLE_NAME_TWO = "account_detail";
    private static final String COLUMN_BANK_NAME = "bank_name";
    private static final String COLUMN_ACCOUNT_HOLDER = "account_holder";
    private static final String COLUMN_INITIAL_BALANCE = "initial_balance";



    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        System.out.println("Cons");
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql1 = "CREATE TABLE "+TABLE_NAME_TWO+" (\n"+
                "    " + COLUMN_ACCOUNT_NUMBER + " STRING NOT NULL,\n" +
                "    " + COLUMN_BANK_NAME + " VARCHAR(15) NOT NULL,\n" +
                "    " + COLUMN_ACCOUNT_HOLDER + " DATETIME NOT NULL,\n" +
                "    " + COLUMN_INITIAL_BALANCE + " DOUBLE NOT NULL\n" +
                ");\n";



        String sql2 = "CREATE TABLE " + TABLE_NAME_ONE + " (\n" +
                "    " + COLUMN_ACCOUNT_NUMBER + " STRING NOT NULL CONSTRAINT account_pk PRIMARY KEY,\n" +
                "    " + COLUMN_EXPENSE_TYPE + " VARCHAR(15) NOT NULL,\n" +
                "    " + COLUMN_AMOUNT + " VARCHAR(15) NOT NULL,\n" +
                "    " + COLUMN_DATE + " DATETIME NOT NULL,\n" +
                "    " + "FOREIGN KEY"+ "("+ COLUMN_ACCOUNT_NUMBER+")"+" REFERENCES "+TABLE_NAME_TWO+"("+COLUMN_ACCOUNT_NUMBER+")"+"ON DELETE CASCADE ON UPDATE CASCADE"+
                ");\n";



        Log.d("Inside onCreate","YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");


        try {
            db.execSQL(sql1);
            db.execSQL(sql2);
            Log.d("In try","TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
        }catch (Exception e){
            Log.d("Errrrrrrrrrorrrrrrrrr",e.toString());
        }



        Log.d("ADebugTag", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql1 = "DROP TABLE IF EXISTS " + TABLE_NAME_ONE + ";";
        String sql2 = "DROP TABLE IF EXISTS " + TABLE_NAME_TWO + ";";
        db.execSQL(sql1);
        db.execSQL(sql2);
        onCreate(db);

    }

    public Cursor getAllRows(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(TABLE_NAME_ONE,null,null,null,null,null,null);
    }


}
