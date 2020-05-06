package e.welcome.contactapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Phone extends AppCompatActivity {
    private TextView mTextMessage;
    private boolean loadfrag(Fragment fragment)
    {
        if(fragment!=null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentshow,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment=null;
            switch (item.getItemId()) {

                case R.id.phone:
                    fragment=new PhoneCall();
                    break;
                case R.id.contacts:
                    fragment=new Contacts();
                    break;
            }
            return loadfrag(fragment);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadfrag(new PhoneCall());
    }

}
