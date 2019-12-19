package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception.ExpenseManagerException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.database.DatabaseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.InMemoryAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.InMemoryTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

public class PersistentDemoExpenseManager extends ExpenseManager {

    private DatabaseManager databaseManager;

    public PersistentDemoExpenseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        setup();
    }

    @Override
    public void setup(){

        TransactionDAO inMemoryTransactionDAO = new InMemoryTransactionDAO();
        setTransactionsDAO(inMemoryTransactionDAO);

        AccountDAO persistentAccountDAO = new PersistentAccountDAO(databaseManager);
        setAccountsDAO(persistentAccountDAO);



    }
}
