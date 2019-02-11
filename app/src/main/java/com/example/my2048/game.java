package com.example.my2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.app.Activity;
import android.widget.TextView;
import android.widget.LinearLayout;
public class game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        LinearLayout board1 =(LinearLayout) findViewById(R.id.board);
        final TextView in=(TextView) findViewById(R.id.index00);
        board1.setOnTouchListener(new OnSwipeTouchListener(game.this){
        public void onSwipeTop() {
            in.setText("245");
        }
        public void onSwipeRight() {
            in.setText("244");
        }
        public void onSwipeLeft() {
            in.setText("243");
        }
        public void onSwipeBottom() {
            in.setText("242");
        }
    });


}}
