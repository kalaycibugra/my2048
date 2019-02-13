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
        final gridIndex[][] grid = new gridIndex[4][4];
        for (int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                grid[i][j]=new gridIndex();
                grid[i][j].value=0;
                grid[i][j].indexName=setTextId(i,j);
            }
        }
        grid[m][n].value= 2;

        setContentView(R.layout.activity_game);
        LinearLayout board1 =(LinearLayout) findViewById(R.id.board);
        final TextView in=(TextView) findViewById(grid[n][m].indexName);
        in.setText(Integer.toString(grid[m][n].value));
//        final TextView in1=(TextView) findViewById(R.id.index02);
        final Button restart = (Button) findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        grid[i][j].value=0;
                        TextView in1=(TextView) findViewById(grid[i][j].indexName);
                        in1.setText("");
                    }
                }
            }
        });
        board1.setOnTouchListener(new OnSwipeTouchListener(game.this){

        public void onSwipeTop() {
            int j,i;

            for(int k=0;k<4;k++){
                i=0;
                for(j=0;j<4;j++){
                    if(grid[j][k].value!=0){
                        if(i<j){
                            int tmp=grid[i][k].value;
                            grid[i][k].value=grid[j][k].value;
                            grid[j][k].value=tmp;
                        }
                        i++;
                    }
                }
            }


            Random rand = new Random();
            int q = rand.nextInt(4);
            int w = rand.nextInt(4);
            grid[q][w].indexName=setTextId(q,w);
            grid[q][w].value=2;
            TextView in1=(TextView) findViewById(grid[q][w].indexName);
            in1.setText(Integer.toString(grid[q][w].value));
            updateGrid(grid);
        }

        public void onSwipeRight() {
            int j,i;

            for(int k=0;k<4;k++){
                i=3;
                for(j=3;j>=0;j--){
                    if(grid[k][j].value!=0){
                        if(i>j){
                            int tmp=grid[k][i].value;
                            grid[k][i].value=grid[k][j].value;
                            grid[k][j].value=tmp;
                        }
                        i--;
                    }
                }
            }

            Random rand = new Random();
            int q = rand.nextInt(4);
            int w = rand.nextInt(4);
            grid[q][w].indexName=setTextId(q,w);
            grid[q][w].value=2;
            TextView in1=(TextView) findViewById(grid[q][w].indexName);
            in1.setText(Integer.toString(grid[q][w].value));
            updateGrid(grid);
        }

        public void onSwipeLeft() {
            int j,i;

            for(int k=0;k<4;k++){
                i=0;
                for(j=0;j<4;j++){
                    if(grid[k][j].value!=0){
                        if(i<j){
                            int tmp=grid[k][i].value;
                            grid[k][i].value=grid[k][j].value;
                            grid[k][j].value=tmp;
                        }
                        i++;
                    }
                }
            }

            Random rand = new Random();
            int q = rand.nextInt(4);
            int w = rand.nextInt(4);
            grid[q][w].indexName=setTextId(q,w);
            grid[q][w].value=4;
            TextView in1=(TextView) findViewById(grid[q][w].indexName);
            in1.setText(Integer.toString(grid[q][w].value));
            updateGrid(grid);
        }

        public void onSwipeBottom() {
            int j,i;

            for(int k=0;k<4;k++){
                i=3;
                for(j=3;j>=0;j--){
                    if(grid[j][k].value!=0){
                        if(i>j){
                            int tmp=grid[i][k].value;
                            grid[i][k].value=grid[j][k].value;
                            grid[j][k].value=tmp;
                        }
                        i--;
                    }
                }
            }


            Random rand = new Random();
            int q = rand.nextInt(4);
            int w = rand.nextInt(4);
            grid[q][w].indexName=setTextId(q,w);
            grid[q][w].value=2;
            TextView in1=(TextView) findViewById(grid[q][w].indexName);
            in1.setText(Integer.toString(grid[q][w].value));
            updateGrid(grid);
        }


    });


    }

    public void updateGrid(gridIndex[][] grid){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                TextView in1=(TextView) findViewById(grid[i][j].indexName);
                if(grid[i][j].value!=0)
                    in1.setText(Integer.toString(grid[i][j].value));
                else
                    in1.setText("");

            }
        }

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
