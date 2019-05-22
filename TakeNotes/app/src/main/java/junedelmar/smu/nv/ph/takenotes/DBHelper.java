package junedelmar.smu.nv.ph.takenotes;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="dbnotes";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " +
                Notes.TABLE + "(" +
                Notes.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Notes.KEY_title + " TEXT, " +
                Notes.Key_content + " TEXT, " +
                Notes.KEY_date + " TEXT, " +
                Notes.KEY_status + " TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + Notes.TABLE+";";
        db.execSQL(sql);
        onCreate(db);
    }
}
