package e.welcome.contactapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.viewholder> {
    String url,name,age,email;
    private Context context1;
    public AdapterRecycler(String url,String name,String email,String age, Context context)
    {
        this.context1=context;
        this.url=url;
        this.name=name;
        this.email=email;
        this.age=age;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflate =LayoutInflater.from(viewGroup.getContext());
        View view=inflate.inflate(R.layout.contactitem,viewGroup,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {

        viewholder.name.setText(name);
        viewholder.email.setText(email);
        Picasso.get().load(url).into(viewholder.image);

    }

    @Override
    public int getItemCount() {

        return name.length();
    }

    public class viewholder extends RecyclerView.ViewHolder
    {   TextView name,email;
    ImageView image;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.contactname);
            email=itemView.findViewById(R.id.contactemail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
    }
}
