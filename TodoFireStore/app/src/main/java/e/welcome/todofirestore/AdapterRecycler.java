package e.welcome.todofirestore;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.viewholder> {
    List<ListData> notelist;
    private Context context1;
    public AdapterRecycler(List<ListData> noteslist, Context context)
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
        ListData data=notelist.get(i);
        viewholder.title.setText(data.getTitle());
        viewholder.description.setText(data.getDescription());
    }

    @Override
    public int getItemCount() {
        return notelist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder
    {   TextView title,description;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title_recycler);
            description=itemView.findViewById(R.id.description_recycler);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ListData listData =notelist.get(getAdapterPosition());
                    Intent i=new Intent(context1, ViewDetails.class);
                    i.putExtra("title",listData.title);
                    i.putExtra("descr",listData.description);
                    context1.startActivity(i);

                }
            });
        }
    }
}
