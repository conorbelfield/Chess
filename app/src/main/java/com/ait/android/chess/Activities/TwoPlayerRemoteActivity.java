package com.ait.android.chess.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ait.android.chess.Adapters.GamesAdapter;
import com.ait.android.chess.Data.ChessModel;
import com.ait.android.chess.Data.Game;
import com.ait.android.chess.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TwoPlayerRemoteActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText opponentUid;
    Button btnNew;
    private GamesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player_remote);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        opponentUid = (EditText) findViewById(R.id.etOpponentUid);
        btnNew = (Button) findViewById(R.id.newGameBtn);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFormValid()){
                    return;
                }
                else{
                   uploadGame();
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView tvUserEmail = navigationView.getHeaderView(0).findViewById(R.id.tvUserEmail);
        tvUserEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewGames);
        adapter = new GamesAdapter(this,
                FirebaseAuth.getInstance().getCurrentUser().getUid());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        initGamesListener();
    }

    private void initGamesListener() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("games");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Game game = dataSnapshot.getValue(Game.class);
                adapter.addGame(game, dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //edit it here i think, do local things
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adapter.removeGameByKey(dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void uploadGame() {
        String key = FirebaseDatabase.getInstance().getReference().child("games").push().getKey();
        Game newGame = new Game(
                FirebaseAuth.getInstance().getCurrentUser().getUid(),
                opponentUid.getText().toString(), ChessModel.getInstance().getInitChessModel());

        FirebaseDatabase.getInstance().getReference().child("games").child(key).setValue(newGame).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(TwoPlayerRemoteActivity.this, "Game created", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private boolean isFormValid() {
        if (TextUtils.isEmpty(opponentUid.getText())) {
            opponentUid.setError("The email can not be empty");
            return false;
        }


        return true;
    }
}

