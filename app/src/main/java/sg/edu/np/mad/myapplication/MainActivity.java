package sg.edu.np.mad.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button mButtonFollow;
    private Boolean follow_state = true;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButtonFollow =findViewById(R.id.btn_follow);
        DBHandler db = new DBHandler(this);

        Intent in = getIntent();
        String name = in.getStringExtra("name");
        String description = in.getStringExtra("description");
        int id = in.getIntExtra("id",0);
        boolean value = in.getBooleanExtra("value", false);
        user = new User(name, description, id, value);

        TextView nameTxt = findViewById(R.id.helloWorldTv);
        String temp1 = user.getName() + " ";
        String temp2 = String.valueOf(user.getId());
        nameTxt.setText(temp1+temp2);

        TextView desTxt = findViewById(R.id.textView1);
        desTxt.setText(user.getDescription());

        mButtonFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (follow_state) {
                    mButtonFollow.setText("Unfollow");
                    follow_state = false;
                    db.updateUser(user);
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_LONG).show();
                } else{
                    mButtonFollow.setText("Follow");
                    follow_state = true;
                    db.updateUser(user);
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}