package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.database.DatabaseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;

public class PersistentAccountDAO implements AccountDAO {

    private DatabaseManager databaseManager;

    public PersistentAccountDAO(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public List<String> getAccountNumbersList() {
        List<String> accountNumberList = new ArrayList<>();
        String[] columns = {this.databaseManager.getColumnAccountNumber()};
        SQLiteDatabase db = this.databaseManager.getWritableDatabase();
        Cursor accountNumbers = db.query(this.databaseManager.getTableNameTwo(),columns,null,null,null,null,null);

        while(accountNumbers.moveToNext()) {
            Log.d("QQQQQQQQQQQQQQQ",accountNumbers.getString(accountNumbers.getColumnIndex(databaseManager.getColumnAccountNumber())));
            accountNumberList.add(accountNumbers.getString(accountNumbers.getColumnIndex(databaseManager.getColumnAccountNumber()))); //add the item
        }

        return accountNumberList;


    }

    @Override
    public List<Account> getAccountsList() {
        return null;
    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
        return null;
    }

    @Override
    public void addAccount(Account account) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseManager.getColumnAccountNumber(),account.getAccountNo());
        contentValues.put(databaseManager.getColumnBankName(),account.getBankName());
        contentValues.put(databaseManager.getColumnAccountHolder(),account.getAccountHolderName());
        contentValues.put(databaseManager.getColumnInitialBalance(),account.getBalance());
        SQLiteDatabase db = this.databaseManager.getWritableDatabase();
        db.insert(databaseManager.getTableNameTwo(),null,contentValues);

    }

    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {
        SQLiteDatabase db = this.databaseManager.getWritableDatabase();
        db.delete(databaseManager.getTableNameOne(),databaseManager.getColumnAccountNumber()+"=?",new String[]{String.valueOf(accountNo)});

    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {
        SQLiteDatabase db = this.databaseManager.getWritableDatabase();
        String[] colums = {this.databaseManager.getColumnInitialBalance()};
        Cursor cursor = db.rawQuery("SELECT initial_balance FROM account_detail WHERE account_number=?", new String[] {accountNo + ""});
        String balance = cursor.toString();
        Double val = Double.parseDouble(balance);
        switch (expenseType) {
            case EXPENSE:
                val = val - amount;
                break;
            case INCOME:
                val = val + amount;
                break;
        }

        ContentValues cv = new ContentValues();
        cv.put(databaseManager.getColumnInitialBalance(),val);
        db.update(databaseManager.getTableNameTwo(), cv, "account_number = ?", new String[]{accountNo});



    }
}
