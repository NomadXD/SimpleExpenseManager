package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.database.DatabaseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

public class PersistentTransactionDAO implements TransactionDAO {

    private DatabaseManager databaseManager;

    public PersistentTransactionDAO(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public void logTransaction(Date date, String accountNo, ExpenseType expenseType, double amount) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseManager.getColumnAccountNumber(),accountNo);
        contentValues.put(databaseManager.getColumnExpenseType(),expenseType.toString());
        contentValues.put(databaseManager.getColumnAmount(),amount);
        contentValues.put(databaseManager.getColumnDate(),date.toString());

        SQLiteDatabase db = this.databaseManager.getWritableDatabase();
        db.insert(databaseManager.getTableNameOne(),null,contentValues);




    }

    @Override
    public List<Transaction> getAllTransactionLogs() {
        return null;
    }

    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {
        List<Transaction> paginatedTransactions = new ArrayList<Transaction>();
        SQLiteDatabase db = this.databaseManager.getWritableDatabase();
        Cursor cursor = db.query(this.databaseManager.getTableNameOne(),null,null,null,null,null,null);

        while(cursor.moveToNext()) {
            String accountNumber = cursor.getString(cursor.getColumnIndex(databaseManager.getColumnAccountNumber()));
            String expensetype = cursor.getString(cursor.getColumnIndex(databaseManager.getColumnExpenseType()));
            String date = cursor.getString(cursor.getColumnIndex(databaseManager.getColumnDate()));
            String amount = cursor.getString(cursor.getColumnIndex(databaseManager.getColumnAmount()));
            try {
//                date = (new SimpleDateFormat("yyyy-MM-dd")).parse(date);
            }catch (Exception e){

            }

            Double var = Double.parseDouble(amount);

            paginatedTransactions.add(new Transaction(new Date(),accountNumber.toString(),ExpenseType.EXPENSE,var));


        }


        return paginatedTransactions;
    }
}
