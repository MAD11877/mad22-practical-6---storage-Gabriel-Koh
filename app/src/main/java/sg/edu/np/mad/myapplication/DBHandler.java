package sg.edu.np.mad.myapplication;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.Console;
import java.util.ArrayList;
import java.util.Random;

public class DBHandler extends SQLiteOpenHelper{
    public DBHandler(@Nullable Context context) {
        super(context, "Gabriel_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS (NAME TEXT, DESCRIPTION TEXT, ID INTEGER, FOLLOWED BOOLEAN )");

        for (int i = 0 ; i < 20; i++)
        {
            ContentValues values = new ContentValues();
            values.put("name", "Name " + new Random().nextInt());
            values.put("description", "Description" + new Random().nextInt());
            values.put("id", i);
            values.put("followed", new Random().nextBoolean());

            db.insert("USERS", null, values);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USERS");
        onCreate(db);
    }

    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("description", user.getDescription());
        values.put("id", user.getId());
        values.put("followed", user.getFollowed());

        SQLiteDatabase db = getWritableDatabase();
        db.insert("USERS", null, values);
        db.close();
    }

    /**
     * select * from users where name = "xxxx";
     * @param name
     * @return
     */

    public ArrayList<User> getUser(String name)
    {
        SQLiteDatabase db = getWritableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM USERS WHERE name = \"" + name + "\"", null);
        Cursor cursor = db.rawQuery("select * from users", null);
        User u = null;
        ArrayList<User> list = new ArrayList<>();

        while (cursor.moveToNext())
        {
            u = new User(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3) > 0
            );

            list.add(u);
        }
        cursor.close();
        db.close();

        return list;
    }

    public void updateUser(User user)
    {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("description", user.getDescription());
        values.put("id", user.getId());
        values.put("followed", user.getFollowed());

        SQLiteDatabase db = getWritableDatabase();
        db.update("USERS", values,"id=?", new String[]{Integer.toString(user.getId())});

        db.close();
    }
}
