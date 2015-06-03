package in.teachcoder.app.edirectory;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import model.DataSoure;
import model.DbHelper;


public class MainActivity extends Activity {


    public static final String LOGTAG = "DbHelper";
    List<User> users;

    //db
    DataSoure ds;
    ListView lv;
    //db ends
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.listView);


        ds = new DataSoure(this);
        ds.open();
        List<User> users = ds.findAll();
        if(users.size()==0){
            createData();
            users = ds.findAll();
        }



        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,android.R.layout.simple_list_item_1,users);
        lv.setAdapter(adapter);

        final List<User> finalUsers = users;
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final User item = (User) finalUsers.get(position);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(".User",item);
                 startActivity(intent);
            }
        });

    }




//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//
//        User user = users.get(position);
//
//        Intent intent = new Intent(this, DetailActivity.class);
//        intent.putExtra(".User",user);
//        startActivity(intent);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ds.open(); //Opens the database

    }

    @Override
    protected void onPause() {
        super.onPause();
        ds.close(); //closes the database
    }

    private void createData(){

        UsersPullParser parser = new UsersPullParser();
        users = parser.parseXML(this);

        for(User user:users){
            ds.create(user);
        }

    }
}
