package seenlabgames.smu.nv.ph.tanungero;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ScoreCRUD {
    private DBHelper dbHelper;

    public ScoreCRUD(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int Insert(Score sc) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(Score.KEY_SCORE, sc.bestscore);
        long sid = db.insert(Score.TABLE, null, val);
        db.close();
        return (int) sid;
    }
     public void DeleteAll() {
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String sql="DELETE FROM "+Score.TABLE+";";
        db.execSQL(sql);
        db.close();
     }
    public ArrayList<HashMap<String,String>> BestScore(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String sql="SELECT score AS 'score' FROM score;";
        ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> sc=new HashMap<String,String>();
                sc.put("score",cursor.getString(cursor.getColumnIndex("score")));
                list.add(sc);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return list;
    }
}
