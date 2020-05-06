package e.welcome.myrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        String[] names={"anshul","akash","anant","gourav","sahil","harsh","devang","annesh",
        "yashpal","himanshu","gouravlohiya"};
        int[] pics={R.drawable.amazon,R.drawable.androidhacking,R.drawable.be,R.drawable.bk1
        ,R.drawable.devloperback,R.drawable.bkkk,R.drawable.dummy,R.drawable.ec_council
        ,R.drawable.fbicon,R.drawable.images,R.drawable.bkbietimages};
        recyclerView.setAdapter(new Adapter(names,pics));
    }
}
