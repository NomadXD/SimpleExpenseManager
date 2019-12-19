package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

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
        return null;
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
        db.insert(databaseManager.getTableNameOne(),null,contentValues);

    }

    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {
        SQLiteDatabase db = this.databaseManager.getWritableDatabase();
        db.delete(databaseManager.getTableNameOne(),databaseManager.getColumnAccountNumber()+"=?",new String[]{String.valueOf(accountNo)});

    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {

    }
}
