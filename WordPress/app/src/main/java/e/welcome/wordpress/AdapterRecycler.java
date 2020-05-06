package e.welcome.wordpress;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        login=data.getTitle();
        usertype=data.getDescription();
        username.setText(login);
        type.setText(usertype);
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
        username=itemView.findViewById(R.id.date);
            type=itemView.findViewById(R.id.name);
        }
    }
}
