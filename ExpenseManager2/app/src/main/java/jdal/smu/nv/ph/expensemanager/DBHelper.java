package jdal.smu.nv.ph.expensemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dbstudent";

    public DBHelper(Context context) {
        //database creation
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " +
                Expense.TABLE + "(" +
                Expense.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Expense.KEY_item + " TEXT, " +
                Expense.KEY_amount + " DOUBLE);";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + Expense.TABLE+";";
        db.execSQL(sql);
        onCreate(db);
    }
}
