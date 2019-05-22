package junedelmar.smu.nv.ph.sqlitedemo1;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.view.View;

public class DBHelper extends SQLiteOpenHelper {
    //constructor
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dbstudent";

    public DBHelper(Context context) {
        //database creation
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " +
                Student.TABLE + "(" +
                Student.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Student.KEY_name + " TEXT, " +
                Student.KEY_email + " TEXT, " +
                Student.KEY_age + " INTEGER);";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + Student.TABLE+";";
        db.execSQL(sql);
        onCreate(db);
    }
}
