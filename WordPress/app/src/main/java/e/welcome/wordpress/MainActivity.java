package e.welcome.wordpress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<User> user;
    String URL_DATA="https://www.simplifiedcoding.net/wp-json/wp/v2/posts/";
    RequestQueue reqQue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));



        user=new ArrayList<>();
        loadurl();
    }
    private void loadurl() {
        JsonArrayRequest stringRequest=new JsonArrayRequest(URL_DATA, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                getvalue(response);

            }
        },new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        reqQue= Volley.newRequestQueue(this);
        reqQue.add(stringRequest);

    }

    private void getvalue(JSONArray response) {
        for(int i=0;i< response.length(); i++)
        {
            User userlist=new User();
            JSONObject json=null;
            try
            {
                json=response.getJSONObject(i);
                JSONObject titleobj=json.getJSONObject("title");
                userlist.setId(json.getLong("id"));
                userlist.setTitle(titleobj.getString("rendered"));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            user.add(userlist);

        }
        adapter =new AdapterRecycler(user,this);
        recyclerView.setAdapter(adapter);
    }
}
