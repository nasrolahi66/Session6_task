package com.example.session6_task;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder> {
List<SQLiteHelper.Movie> movieList;
public  FavoriteAdapter(List<SQLiteHelper.Movie> movies)
{
    movieList=movies;
}
    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorite,viewGroup,false);
        FavoriteHolder favoriteHolder=new FavoriteHolder(view);
        return  favoriteHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHolder favoriteHolder, int i) {
        favoriteHolder.txtTitle.setText(movieList.get(i).title);
        favoriteHolder.txtType.setText(movieList.get(i).type);
        favoriteHolder.txtYear.setText(movieList.get(i).year);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public  class  FavoriteHolder extends  RecyclerView.ViewHolder{
        TextView txtTitle;
        TextView txtYear;
        TextView txtType;
        ImageView imgDelete;

        public FavoriteHolder(@NonNull final View itemView) {
            super(itemView);

            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtType=itemView.findViewById(R.id.txtType);
            txtYear=itemView.findViewById(R.id.txtYear);
            imgDelete=itemView.findViewById(R.id.imgDelete);
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteHelper helper=new SQLiteHelper(itemView.getContext(),"Favorites",null,1);
                    helper.DELETE_MOVIE(txtTitle.getText().toString(),txtYear.getText().toString());
                    movieList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });

        }
    }
}
