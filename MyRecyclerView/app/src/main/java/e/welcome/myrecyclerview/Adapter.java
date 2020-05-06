package e.welcome.myrecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder> {
    private String[] name;
    private int[] images;
    public Adapter(String[] name,int[] images)
    {
        this.name=name;
        this.images=images;
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
      String title=name[i];
      int im=images[i];
      viewholder.textView.setText(title);
      viewholder.imageView.setImageResource(im);
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public class viewholder extends RecyclerView.ViewHolder
    {   TextView textView;
    ImageView imageView;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageid);
            textView=itemView.findViewById(R.id.nameid);
        }
    }
}
