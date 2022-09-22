package br.com.up.listadepresentes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import br.com.up.listadepresentes.adapter.GiftAdapter;
import br.com.up.listadepresentes.model.Gift;
import br.com.up.listadepresentes.repository.GiftRepository;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabAddGift;
    private RecyclerView recyclerViewGifts;
    private TextView textViewGiftsNotFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAddGift = findViewById(R.id.fab_add_gift);
        recyclerViewGifts = findViewById(R.id.recycler_gifts);
        textViewGiftsNotFound = findViewById(R.id.text_gifts_not_found);

        recyclerViewGifts.setLayoutManager(
                new LinearLayoutManager(this,
                        RecyclerView.VERTICAL,
                        false)
                //new GridLayoutManager(this,8)
        );


        fabAddGift.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        getApplicationContext(),
                        AddGiftActivity.class
                );
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Gift> gifts = GiftRepository.getInstance().getAll();

        if(gifts.size() > 0){
            textViewGiftsNotFound.setVisibility(View.INVISIBLE);
        }else{
            textViewGiftsNotFound.setVisibility(View.VISIBLE);
        }

        recyclerViewGifts.setAdapter(new GiftAdapter(gifts));
    }
}