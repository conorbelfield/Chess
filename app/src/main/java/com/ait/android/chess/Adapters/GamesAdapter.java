package com.ait.android.chess.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ait.android.chess.Data.ChessModel;
import com.ait.android.chess.Data.Game;
import com.ait.android.chess.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Peter on 2017. 11. 23..
 */

public class GamesAdapter
        extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    private Context context;
    private List<Game> gameList;
    private List<String> gameKeys;
    private String uId;
    private int lastPosition = -1;
    private DatabaseReference gamesRef;

    public GamesAdapter(Context context, String uId) {
        this.context = context;
        this.uId = uId;

        gameList = new ArrayList<Game>();
        gameKeys = new ArrayList<String>();

        gamesRef = FirebaseDatabase.getInstance().getReference();
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_games, parent, false);

        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Game game = gameList.get(position);

        holder.tvUidB.setText(game.getUidB());
        holder.tvUidW.setText(game.getUidW());
/*
        if (uId.equals(game.getUid())) {
            holder.btnDelete.setVisibility(View.VISIBLE);
        } else {
            holder.btnDelete.setVisibility(View.INVISIBLE);
        }*/

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeGame(holder.getAdapterPosition());
            }
        });
    }


    public void removeGame(int index) {
        //gamesRef = FirebaseDatabase.getInstance().getReference();

        gamesRef.child("games").child(gameKeys.get(index)).removeValue();

        gameList.remove(index);
        gameKeys.remove(index);
        notifyItemRemoved(index);
    }

    public void removeGameByKey(String key) {
        int index = gameKeys.indexOf(key);
        if (index != -1) {
            gameList.remove(index);
            gameKeys.remove(index);
            notifyItemRemoved(index);
        }
    }



    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public void addGame(Game game, String key) {
        gameList.add(game);
        gameKeys.add(key);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvUidB;
        public TextView tvUidW;
        public Button btnDelete;
        public ImageView ivGameImg;


        public ViewHolder(View itemView) {
            super(itemView);

            tvUidB = itemView.findViewById(R.id.tvUidB);
            tvUidW = itemView.findViewById(R.id.tvUidW);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            ivGameImg = itemView.findViewById(R.id.ivGameImg);
        }
    }


}
