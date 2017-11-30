package com.example.administrator.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.sql.BatchUpdateException;

public class MainActivity extends AppCompatActivity {
   private static final int NUM_ROWS=5;
    private static final int NUM_COLS=5;

    Button buttons[][]=new Button[NUM_COLS][NUM_ROWS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateButtons();
    }

    private void populateButtons(){
        TableLayout tableLayout=findViewById(R.id.tableForButtons);
        for(int row=0;row<NUM_ROWS;row++){
            TableRow tableRow=new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            tableLayout.addView(tableRow);
            for(int col=0;col<NUM_COLS;col++){
                final int FINAL_COL=col;
                final int FINAL_ROW=row;
                Button button=new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                button.setText(""+row+","+col);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gridButtonClick(FINAL_COL,FINAL_ROW);
                    }
                });
                tableRow.addView(button);
                buttons[row][col]=button;
            }
        }
    }

    public void gridButtonClick(int col,int row){
        Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_LONG).show();
        Button button=buttons[row][col];
        button.setBackgroundResource(R.drawable.business);

        //lock Button sizes
        lockButtonSizes();
        //Scale image to button
        int newWidth=button.getWidth();
        int newHeight=button.getHeight();
        Bitmap originalBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.business);
        Bitmap scaleBitmap=Bitmap.createScaledBitmap(originalBitmap,newWidth,newHeight,true);
        Resources resources=getResources();
        button.setBackground(new BitmapDrawable(resources,scaleBitmap));
    }

    public void lockButtonSizes(){
        for(int row=0;row<NUM_ROWS;row++){
            for(int col=0;col<NUM_COLS;col++){
                Button button=buttons[row][col];
                int width=button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height=button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

}
