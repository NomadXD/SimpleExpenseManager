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
                "    " + COLUMN_ACCOUNT_NUMBER + " STRING NOT NULL CONSTRAINT account_pk PRIMARY KEY,\n" +
                "    " + COLUMN_BANK_NAME + " VARCHAR(15) NOT NULL,\n" +
                "    " + COLUMN_ACCOUNT_HOLDER + " DATETIME NOT NULL,\n" +
                "    " + COLUMN_INITIAL_BALANCE + " DOUBLE NOT NULL\n" +
                ");\n";



        String sql2 = "CREATE TABLE " + TABLE_NAME_ONE + " (\n" +
                "    " + COLUMN_ACCOUNT_NUMBER + " STRING NOT NULL,\n" +
                "    " + COLUMN_EXPENSE_TYPE + " VARCHAR(15) NOT NULL,\n" +
                "    " + COLUMN_AMOUNT + " VARCHAR(15) NOT NULL,\n" +
                "    " + COLUMN_DATE + " DATETIME NOT NULL,\n" +
                "    " + "FOREIGN KEY"+ "("+ COLUMN_ACCOUNT_NUMBER+")"+" REFERENCES "+TABLE_NAME_TWO+"("+COLUMN_ACCOUNT_NUMBER+")"+"ON DELETE CASCADE ON UPDATE CASCADE"+
                ");\n";



        try {
            db.execSQL(sql1);
            db.execSQL(sql2);
        }catch (Exception e){
            Log.d("Errrrrrrrrrorrrrrrrrr",e.toString());
        }



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


    public boolean addEmployee(String accountId,String bank,String accountHolder,String balance){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ACCOUNT_NUMBER,accountId);
        contentValues.put(COLUMN_BANK_NAME,bank);
        contentValues.put(COLUMN_ACCOUNT_HOLDER,accountHolder);
        contentValues.put(COLUMN_INITIAL_BALANCE,balance);

        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_NAME_TWO,null,contentValues) != -1;


    }



    public static String getDatabase() {
        return DATABASE_NAME;
    }

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }

    public static String getTableNameOne() {
        return TABLE_NAME_ONE;
    }

    public static String getColumnAccountNumber() {
        return COLUMN_ACCOUNT_NUMBER;
    }

    public static String getColumnExpenseType() {
        return COLUMN_EXPENSE_TYPE;
    }

    public static String getColumnAmount() {
        return COLUMN_AMOUNT;
    }

    public static String getColumnDate() {
        return COLUMN_DATE;
    }

    public static String getTableNameTwo() {
        return TABLE_NAME_TWO;
    }

    public static String getColumnBankName() {
        return COLUMN_BANK_NAME;
    }

    public static String getColumnAccountHolder() {
        return COLUMN_ACCOUNT_HOLDER;
    }

    public static String getColumnInitialBalance() {
        return COLUMN_INITIAL_BALANCE;
    }
}
