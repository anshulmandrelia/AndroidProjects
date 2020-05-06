package e.welcome.recyclerviewproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mlayoutmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);
        ArrayList<ExampleItem> exampleItems=new ArrayList<>();
        exampleItems.add(new ExampleItem(R.drawable.ic_launcher_background,"Line 1","Line 2"));
        exampleItems.add(new ExampleItem(R.drawable.ic_launcher_foreground,"Line 2","Line 3"));
        exampleItems.add(new ExampleItem(R.drawable.ic_launcher_background,"Line 3","Line 1"));
        exampleItems.add(new ExampleItem(R.drawable.ic_launcher_background,"Line 1","Line 2"));
        exampleItems.add(new ExampleItem(R.drawable.ic_launcher_foreground,"Line 2","Line 3"));
        exampleItems.add(new ExampleItem(R.drawable.ic_launcher_background,"Line 3","Line 1"));
        exampleItems.add(new ExampleItem(R.drawable.ic_launcher_background,"Line 1","Line 2"));
        exampleItems.add(new ExampleItem(R.drawable.ic_launcher_foreground,"Line 2","Line 3"));
        exampleItems.add(new ExampleItem(R.drawable.ic_launcher_background,"Line 3","Line 1"));
        exampleItems.add(new ExampleItem(R.drawable.ic_launcher_background,"Line 1","Line 2"));
        exampleItems.add(new ExampleItem(R.drawable.ic_launcher_foreground,"Line 2","Line 3"));
        exampleItems.add(new ExampleItem(R.drawable.ic_launcher_background,"Line 3","Line 1"));
        exampleItems.add(new ExampleItem(R.drawable.ic_launcher_background,"Line 1","Line 2"));
        exampleItems.add(new ExampleItem(R.drawable.ic_launcher_foreground,"Line 2","Line 3"));
        exampleItems.add(new ExampleItem(R.drawable.ic_launcher_background,"Line 3","Line 1"));
        exampleItems.add(new ExampleItem(R.drawable.ic_launcher_background,"Line 1","Line 2"));
        exampleItems.add(new ExampleItem(R.drawable.ic_launcher_foreground,"Line 2","Line 3"));
        exampleItems.add(new ExampleItem(R.drawable.ic_launcher_background,"Line 3","Line 1"));
        mlayoutmanager=new  LinearLayoutManager(this);
       mAdapter =new ExampleAdapter(exampleItems);
       recyclerView.setLayoutManager(mlayoutmanager);
       recyclerView.setAdapter(mAdapter);


    }
}
