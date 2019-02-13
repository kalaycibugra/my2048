package com.example.my2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Random;
import android.view.MotionEvent;
import android.view.View;
import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;
public class game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Random rand = new Random();
        int n = rand.nextInt(4);
        int m = rand.nextInt(4);
        gridIndex[][] grid = new gridIndex[4][4];
        for (int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                grid[i][j]=new gridIndex();
            }
        }
        grid[m][n].value= 4;
        int id =0;
        id = setTextId(m,n);
        System.out.print(id);
        grid[n][m].indexName=id;

        setContentView(R.layout.activity_game);
        LinearLayout board1 =(LinearLayout) findViewById(R.id.board);
        final TextView in=(TextView) findViewById(id);
        in.setText(Integer.toString(grid[m][n].value));
//        final TextView in1=(TextView) findViewById(R.id.index02);
        final Button restart = (Button) findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                in.setText("");
//                in1.setText("");
            }
        });
        board1.setOnTouchListener(new OnSwipeTouchListener(game.this){

        public void onSwipeTop() {

            in.setText("245");
        }

        public void onSwipeRight() {
//            in1.setText("244");
        }

        public void onSwipeLeft() {
            in.setText("243");
        }

        public void onSwipeBottom() {
//            in1.setText("242");
        }


    });


    }
    public int setTextId(int index1,int index2){
        int id=0;
        if(index1==0){
            switch (index2){
                case 0:
                    id=R.id.index00;
                    break;
                case 1:
                    id=R.id.index01;
                    break;
                case 2:
                    id=R.id.index02;
                    break;
                case 3:
                    id=R.id.index03;
                    break;
            }
        }
        else if(index1==1){
            switch (index2){
                case 0:
                    id=R.id.index10;
                    break;
                case 1:
                    id=R.id.index11;
                    break;
                case 2:
                    id=R.id.index12;
                    break;
                case 3:
                    id=R.id.index13;
                    break;
            }
        }
        else if(index1==2){
            switch (index2){
                case 0:
                    id=R.id.index20;
                    break;
                case 1:
                    id=R.id.index21;
                    break;
                case 2:
                    id=R.id.index22;
                    break;
                case 3:
                    id=R.id.index23;
                    break;
            }
        }
        else if(index1==3){
            switch (index2){
                case 0:
                    id=R.id.index30;
                    break;
                case 1:
                    id=R.id.index31;
                    break;
                case 2:
                    id=R.id.index32;
                    break;
                case 3:
                    id=R.id.index33;
                    break;
            }
        }
        return id;
    }
class gridIndex {
        public int indexName;
        public int value;
};
}
