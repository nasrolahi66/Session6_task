package com.example.session6_task;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.session6_task.Movie.Search;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    List<Search>  movieList;
    public  RecyclerViewAdapter(List<Search> searches)
    {
        movieList=searches;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie,viewGroup,false);
        RecyclerViewHolder holder=new RecyclerViewHolder(v);
        return  holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.txtTitle.setText(movieList.get(i).getTitle());
        recyclerViewHolder.txtYear.setText(movieList.get(i).getYear());
        recyclerViewHolder.txtType.setText(movieList.get(i).getType());

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public  class RecyclerViewHolder extends  RecyclerView.ViewHolder{
        TextView txtTitle;
        TextView txtYear;
        TextView txtType;
        ImageView imgFavorite;

        public RecyclerViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtType=itemView.findViewById(R.id.txtType);
            txtYear=itemView.findViewById(R.id.txtYear);
            imgFavorite=itemView.findViewById(R.id.imgFavorite);
            imgFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteHelper helper=new SQLiteHelper(itemView.getContext(),"Favorites",null,1);
                    helper.INSERT_MOVIE(txtTitle.getText().toString(),txtYear.getText().toString(),txtType.getText().toString());
                    Toast.makeText(itemView.getContext(),"Your Movie is Saved!",Toast.LENGTH_LONG).show();

                }
            });
        }
    }
}
