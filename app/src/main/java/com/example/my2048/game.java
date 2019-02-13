package com.example.my2048;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
        final data score = new data();
        score.score1=0;
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
        updateGrid(grid);
//        final TextView in1=(TextView) findViewById(R.id.index02);
        final Button restart = (Button) findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(game.this);
                alertDialogBuilder.setMessage("Do you want to restart").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i=0;i<4;i++){
                            for(int j=0;j<4;j++){
                                grid[i][j].value=0;
                                TextView in1=(TextView) findViewById(grid[i][j].indexName);
                                in1.setText("");
                            }
                        }
                        score.score1=0;
                        TextView in2=(TextView) findViewById(R.id.score);
                        String ss=Integer.toString(score.score1);
                        ss="Score\n"+ss;
                        in2.setText(ss);


                        updateGrid(grid);
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog=alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        board1.setOnTouchListener(new OnSwipeTouchListener(game.this){

        public void onSwipeTop() {
            moveTiles(grid,2);
            merge(grid,2,score);
            assignRandom(grid,score);

        }

        public void onSwipeRight() {
            moveTiles(grid,1);
            merge(grid,1,score);
            assignRandom(grid,score);
        }

        public void onSwipeLeft() {
            moveTiles(grid,0);
            merge(grid,0,score);
            assignRandom(grid,score);

        }

        public void onSwipeBottom() {


            moveTiles(grid,3);
            merge(grid,3,score);
            assignRandom(grid,score);

        }


    });


    }
    public void merge(gridIndex[][] grid,int direction,data score){//0:left 1:right 2:up 3:down
        switch (direction){
            case 0:
                for(int i=0;i<4;i++){
                    for (int j=0;j<3;j++){
                        if(grid[i][j].value==grid[i][j+1].value){
                            grid[i][j].value=grid[i][j].value*2;
                            grid[i][j+1].value=0;
                            moveTiles(grid,direction);
                            score.score1=score.score1+grid[i][j].value;
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
                            score.score1=score.score1+grid[i][j].value;
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
                            score.score1=score.score1+grid[i][j].value;
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
                            score.score1=score.score1+grid[i][j].value;
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
    public void assignRandom(gridIndex[][] grid,data score){

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
        TextView in2=(TextView) findViewById(R.id.score);
        String ss=Integer.toString(score.score1);
        ss="Score\n"+ss;
        in2.setText(ss);
        updateGrid(grid);
    }
    public void updateGrid(gridIndex[][] grid){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                TextView in1=(TextView) findViewById(grid[i][j].indexName);
                setBackColor(grid[i][j]);
                if(grid[i][j].value!=0)
                    in1.setText(Integer.toString(grid[i][j].value));
                else
                    in1.setText("");

            }
        }

    }
    public void setBackColor(gridIndex cell){
        TextView in1=(TextView) findViewById(cell.indexName);
        switch (cell.value){
            case 0:
                in1.setBackgroundResource(R.color.num0);
                break;
            case 2:
                in1.setBackgroundResource(R.color.num2);
                break;
            case 4:
                in1.setBackgroundResource(R.color.num4);
                break;
            case 8:
                in1.setBackgroundResource(R.color.num8);
                break;
            case 16:
                in1.setBackgroundResource(R.color.num16);
                break;
            case 32:
                in1.setBackgroundResource(R.color.num32);
                break;
            case 64:
                in1.setBackgroundResource(R.color.num64);
                break;
            case 128:
                in1.setBackgroundResource(R.color.num128);
                break;
            case 256:
                in1.setBackgroundResource(R.color.num256);
                break;
            case 512:
                in1.setBackgroundResource(R.color.num512);
                break;
            case 1024:
                in1.setBackgroundResource(R.color.num1024);
                break;
            case 2048:
                in1.setBackgroundResource(R.color.num2048);
                break;
            case 4096:
                in1.setBackgroundResource(R.color.num4096);
                break;
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
    class data{
        public int score1;
    }
}
