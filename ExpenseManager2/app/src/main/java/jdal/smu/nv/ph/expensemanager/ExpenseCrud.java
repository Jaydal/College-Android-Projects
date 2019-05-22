package jdal.smu.nv.ph.expensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpenseCrud {
    private DBHelper dbHelper;

    public ExpenseCrud(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int Insert(Expense ex) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(Expense.KEY_item, ex.item);
        val.put(Expense.KEY_amount, ex.amount);
        long sid = db.insert(Expense.TABLE, null, val);
        db.close();
        return (int) sid;
    }
    public ArrayList<HashMap<String,String>> getList(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String sql="SELECT "+
                Expense.KEY_ID+","+
                Expense.KEY_item+","+
                Expense.KEY_amount+
                " FROM "+Expense.TABLE+";";
        ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> expense=new HashMap<String,String>();
                expense.put("item",cursor.getString(cursor.getColumnIndex(Expense.KEY_item)));
                expense.put("amount",cursor.getString(cursor.getColumnIndex(Expense.KEY_amount)));
                list.add(expense);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return list;
    }
    public boolean DeleteAll() {
            SQLiteDatabase db=dbHelper.getReadableDatabase();
            String sql="DELETE FROM "+Expense.TABLE+";";
            db.execSQL(sql);
            db.close();
            return true;
    }
    public ArrayList<HashMap<String,String>> GetExpenses(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String sql="SELECT SUM(amount) AS 'total' FROM expense;";
        ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> student=new HashMap<String,String>();
                student.put("total",cursor.getString(cursor.getColumnIndex("total")));
                list.add(student);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return list;
    }
}
