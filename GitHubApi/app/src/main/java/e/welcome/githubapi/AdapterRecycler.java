package e.welcome.githubapi;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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
    List<User> notelist;
    private Context context1;
    TextView username,type;
    ImageView img;
    public AdapterRecycler(List<User> noteslist, Context context)
    {
        this.context1=context;
        this.notelist=noteslist;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflate =LayoutInflater.from(viewGroup.getContext());
        View view=inflate.inflate(R.layout.item,viewGroup,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {
        User data=notelist.get(i);
        String login,image,usertype;
        login=data.getLogin();
        usertype=data.getType();
        image=data.getAvatarUrl();
        username.setText(login);
        type.setText(usertype);
     Picasso.get().load(image).into(img);
    }

    @Override
    public int getItemCount() {
        return notelist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder
    {
        public viewholder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.image1);
        username=itemView.findViewById(R.id.idof);
            type=itemView.findViewById(R.id.name);
        }
    }
}
