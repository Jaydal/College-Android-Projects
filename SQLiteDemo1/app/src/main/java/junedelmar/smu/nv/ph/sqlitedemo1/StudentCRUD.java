package junedelmar.smu.nv.ph.sqlitedemo1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentCRUD {
    private DBHelper dbHelper;

    public StudentCRUD(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int Insert(Student st) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(Student.KEY_name, st.name);
        val.put(Student.KEY_email, st.email);
        val.put(Student.KEY_age, st.age);
        long sid = db.insert(Student.TABLE, null, val);
        db.close();
        return (int) sid;
    }
    public void Update(Student st) {
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String sql="UPDATE "+Student.TABLE+
                " SET "+Student.KEY_name+"='"+st.name+
                "',"+Student.KEY_email+"='"+st.email+
                "',"+Student.KEY_age+"="+st.age+" WHERE "+Student.KEY_ID+
                "="+st.studentID+";";
        db.execSQL(sql);
        db.close();
    }
    public boolean Delete(Integer id) {
        if(id>0){
            SQLiteDatabase db=dbHelper.getReadableDatabase();
            String sql="DELETE FROM "+Student.TABLE+
                    " WHERE "+Student.KEY_ID+
                    "="+id+";";
            db.execSQL(sql);
            db.close();
            return true;
        }
        return false;
    }
    public ArrayList<HashMap<String,String>>getList(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String sql="SELECT "+
                Student.KEY_ID+","+
                Student.KEY_name+","+
                Student.KEY_email+","+
                Student.KEY_age+
                " FROM "+Student.TABLE+";";
        ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> student=new HashMap<String,String>();
                student.put("id",cursor.getString(cursor.getColumnIndex(Student.KEY_ID)));
                student.put("name",cursor.getString(cursor.getColumnIndex(Student.KEY_name)));
                list.add(student);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return list;
    }
    public ArrayList<HashMap<String,String>>_getList(Integer id){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String sql="SELECT "+
                Student.KEY_ID+","+
                Student.KEY_name+","+
                Student.KEY_email+","+
                Student.KEY_age+
                " FROM "+Student.TABLE+" WHERE "+Student.KEY_ID+"="+id+";";
        ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> student=new HashMap<String,String>();
                student.put("id",cursor.getString(cursor.getColumnIndex(Student.KEY_ID)));
                student.put("name",cursor.getString(cursor.getColumnIndex(Student.KEY_name)));
                student.put("email",cursor.getString(cursor.getColumnIndex(Student.KEY_email)));
                student.put("age",cursor.getString(cursor.getColumnIndex(Student.KEY_age)));
                list.add(student);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return list;
    }
}
