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
            moveTiles(grid,2);
            merge(grid,2);
            assignRandom(grid);

        }

        public void onSwipeRight() {
            moveTiles(grid,1);
            merge(grid,1);
            assignRandom(grid);
        }

        public void onSwipeLeft() {
            moveTiles(grid,0);
            merge(grid,0);
            assignRandom(grid);

        }

        public void onSwipeBottom() {


            moveTiles(grid,3);
            merge(grid,3);
            assignRandom(grid);

        }


    });


    }
    public void merge(gridIndex[][] grid,int direction){//0:left 1:right 2:up 3:down
        switch (direction){
            case 0:
                for(int i=0;i<4;i++){
                    for (int j=0;j<3;j++){
                        if(grid[i][j].value==grid[i][j+1].value){
                            grid[i][j].value=grid[i][j].value*2;
                            grid[i][j+1].value=0;
                            moveTiles(grid,direction);
                        }
                    }
                }
                break;
            case 1:
                for(int i=0;i<4;i++){
                    for (int j=3;j>0;j--){
                        if(grid[i][j].value==grid[i][j-1].value){
                            grid[i][j].value=grid[i][j].value*2;
                            grid[i][j-1].value=0;
                            moveTiles(grid,direction);
                        }
                    }
                }
                break;
            case 2:
                for(int j=0;j<4;j++){
                    for (int i=0;i<3;i++){
                        if(grid[i][j].value==grid[i+1][j].value){
                            grid[i][j].value=grid[i][j].value*2;
                            grid[i+1][j].value=0;
                            moveTiles(grid,direction);
                        }
                    }
                }
                break;
            case 3:
                for(int j=0;j<4;j++){
                    for (int i=3;i>0;i--){
                        if(grid[i][j].value==grid[i-1][j].value){
                            grid[i][j].value=grid[i][j].value*2;
                            grid[i-1][j].value=0;
                            moveTiles(grid,direction);
                        }
                    }
                }
                break;
        }
    }
    public void moveTiles(gridIndex[][] grid, int direction){//0:left 1:right 2:up 3:down
        int i,j;
        switch(direction){
            case 0:
//                int j,i;

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
                break;
            case 1:
//                int j,i;

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
                break;
            case 2:
//                int j,i;

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
                break;
            case 3:
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
                break;


        }
    }
    public void assignRandom(gridIndex[][] grid){

        int q,w;
        Random rand = new Random();
        q = rand.nextInt(4);
        w = rand.nextInt(4);
        while(grid[q][w].value!=0){
            Random rand1 = new Random();
            q = rand1.nextInt(4);
            w = rand1.nextInt(4);
        }
        int selection=rand.nextInt(3);
        if(selection==2){
            grid[q][w].value=4;
        }
        else{
            grid[q][w].value=2;
        }
        TextView in1=(TextView) findViewById(grid[q][w].indexName);
        in1.setText(Integer.toString(grid[q][w].value));
        updateGrid(grid);
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
