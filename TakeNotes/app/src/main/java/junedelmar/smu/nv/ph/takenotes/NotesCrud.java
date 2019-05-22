package junedelmar.smu.nv.ph.takenotes;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
public class NotesCrud {
    private DBHelper dbHelper;

    public NotesCrud(Context context){dbHelper=new DBHelper(context);}

    public int Insert(Notes notes){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(Notes.KEY_title, notes.title);
        val.put(Notes.Key_content, notes.content);
        val.put(Notes.KEY_date, notes.date);
        val.put(Notes.KEY_status, notes.status);
        long sid = db.insert(Notes.TABLE, null, val);
        db.close();
        return (int) sid;
    }
    public void Update(Notes notes) {
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String sql="UPDATE "+Notes.TABLE+
                " SET "+Notes.KEY_title+"='"+notes.title+
                "',"+Notes.Key_content+"='"+notes.content+
                "',"+Notes.KEY_date+"='"+notes.date+
                "',"+Notes.KEY_status+"="+ notes.status+" WHERE "+Notes.KEY_ID+
                "="+ notes.noteID+";";
        db.execSQL(sql);
        db.close();
    }
    public boolean Delete(Integer id) {
        if(id>0){
            SQLiteDatabase db=dbHelper.getReadableDatabase();
            String sql="DELETE FROM "+Notes.TABLE+
                    " WHERE "+Notes.KEY_ID+
                    "="+id+";";
            db.execSQL(sql);
            db.close();
            return true;
        }
        return false;
    }
    public ArrayList<HashMap<String,String>>_getList(Integer id){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String sql="SELECT "+
                Notes.KEY_ID+","+
                Notes.KEY_title+","+
                Notes.Key_content+","+
                Notes.KEY_date+","+
                Notes.KEY_status+
                " FROM "+Notes.TABLE+" WHERE "+Notes.KEY_ID+"="+id+";";
        ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> notes=new HashMap<String,String>();
                notes.put("id",cursor.getString(cursor.getColumnIndex(Notes.KEY_ID)));
                notes.put("title",cursor.getString(cursor.getColumnIndex(Notes.KEY_title)));
                notes.put("content",cursor.getString(cursor.getColumnIndex(Notes.Key_content)));
                notes.put("date",cursor.getString(cursor.getColumnIndex(Notes.KEY_date)));
                notes.put("status",cursor.getString(cursor.getColumnIndex(Notes.KEY_status)));
                list.add(notes);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return list;
    }
    public ArrayList<HashMap<String,String>>getList(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String sql="SELECT "+
                Notes.KEY_ID+","+
                Notes.KEY_title+","+
                Notes.Key_content+","+
                Notes.KEY_date+","+
                Notes.KEY_status+
                " FROM "+Notes.TABLE+";";
        ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> notes=new HashMap<String,String>();
                notes.put("id",cursor.getString(cursor.getColumnIndex( Notes.KEY_ID)));
                notes.put("title",cursor.getString(cursor.getColumnIndex(Notes.KEY_title)));
                list.add(notes);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return list;
    }

}
