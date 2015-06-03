package model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbHelper extends SQLiteOpenHelper {

    private final static String LOGTAG = "DbHelper";
    //database
    private static final String DATABASE_NAME = "Mantralaya";
    public static final String TABLE_NAME = "FINANCE_DEPARTMENT";
    private static final int DATABASE_VERSION = 1;

    //columns
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_DEPARTMNET = "Department";
    public static final String COLUMN_DESIGNATION = "Designation";
    public static final String COLUMN_LOCATION = "Location";
    public static final String COLUMN_MOBILE = "Mobile";
    public static final String COLUMN_EMAIL_ID = "Email_Id";
    public static final String IMAGE = "Image";

    //query

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" (" +
            " "+COLUMN_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
            " "+COLUMN_NAME+" TEXT," +
            " "+COLUMN_DEPARTMNET+" TEXT," +
            " "+COLUMN_DESIGNATION+" TEXT," +
            " "+COLUMN_LOCATION+" VARCHAR(255)," +
            " "+COLUMN_MOBILE+" BIGINT," +
            " "+COLUMN_EMAIL_ID+" INTEGER" +
            ");";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);



    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.i(LOGTAG, "Table has been created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
