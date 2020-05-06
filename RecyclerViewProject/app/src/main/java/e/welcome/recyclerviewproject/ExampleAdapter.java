package e.welcome.recyclerviewproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
      private ArrayList<ExampleItem> exampleItems;
    public ExampleAdapter(ArrayList<ExampleItem> exampleList)
    {
        exampleItems=exampleList;
    }
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.example,viewGroup
        ,false);
        ExampleViewHolder evh=new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        ExampleItem currebtitem=exampleItems.get(i);

        exampleViewHolder.mImageView.setImageResource(currebtitem.getmImageResource());
        exampleViewHolder.mtextview2.setText(currebtitem.getmText2());
        exampleViewHolder.mtextView1.setText(currebtitem.getmText1());
    }

    @Override
    public int getItemCount() {
        return exampleItems.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder
    {   public ImageView mImageView;
        public TextView mtextView1;
        public TextView mtextview2;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView=itemView.findViewById(R.id.imageview);
            mtextView1=itemView.findViewById(R.id.textview);
            mtextview2=itemView.findViewById(R.id.textview2);


        }
    }


}
