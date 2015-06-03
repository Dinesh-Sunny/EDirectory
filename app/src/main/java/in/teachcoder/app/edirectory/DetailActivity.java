package in.teachcoder.app.edirectory;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class DetailActivity extends ActionBarActivity {

    private TextView tvName;
    private TextView tvDept;
    private TextView tvLoc;
    private TextView tvDesg;
    private TextView tvMob;
    private TextView tvEmail;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        user = new User();
//        user.setId(1);
//        user.setName("Sunny");
//        user.setDepartment("Aero");
//        user.setDesignation("Student");
//        user.setMobile("9987242336");
//        user.setEmail_id("dineshiitb.ae18@gmail.com");
//        user.setLocation("Hostel-9");
        Bundle b = getIntent().getExtras();
        user = b.getParcelable(".User");
        refreshDisplay();


    }

    private void refreshDisplay() {
        tvName = (TextView) findViewById(R.id.textViewName);
        tvName.setText(user.getName());
        tvDept = (TextView) findViewById(R.id.textViewDepartment);
        tvDept.setText(user.getDepartment());
        tvDesg = (TextView) findViewById(R.id.textViewDesignation);
        tvDesg.setText(user.getDesignation());
        tvLoc = (TextView) findViewById(R.id.textViewLocation);
        tvLoc.setText(user.getLocation());
        tvMob = (TextView) findViewById(R.id.textViewMobile);
        tvMob.setText(user.getMobile());
        tvEmail = (TextView) findViewById(R.id.textViewEmail);
        tvEmail.setText(user.getEmail_id());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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

    public void process(View v){
        Intent intent=null, chooser = null;
        if(v.getId()==R.id.textViewEmail){
            intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, user.getEmail_id());
            intent.putExtra(Intent.EXTRA_SUBJECT, "Mail from E-Directory Android App");
            intent.setType("message/rfc822");
            chooser = Intent.createChooser(intent, "Send Email");
            startActivity(chooser);


            Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show();
        }
        if(v.getId()==R.id.textViewMobile){
            Intent i = new Intent(Intent.ACTION_DIAL);
            String p = "tel:" + user.getMobile();
            i.setData(Uri.parse(p));

            chooser = Intent.createChooser(i, "Call Through");
            startActivity(chooser);


            Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show();
        }
    }
}
