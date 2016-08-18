package com.nejc.mamiapp;
/**
 * @author Nejc
 *
 * Description:
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;


/*********** REVISION HISTORY *****************
 *
 *
 *
 *
 *
 /***********************************************/




public class OverviewActivity extends AppCompatActivity {
    int state;
    ImageView image, image2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        state=0;

        image=(ImageView) findViewById(R.id.imageView);
        image2=(ImageView) findViewById(R.id.imageView2);


        image.animate().scaleX(3.0f).setDuration(2000).start();
        image2.animate().scaleX(1.5f).setDuration(2000).start();
/*
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state++;
                if (state > 1) state = 0;

                if (state == 0) {
                    image.animate()
                            .scaleX((float) 3)
                            .setDuration(2000)
                            .setInterpolator(new AccelerateDecelerateInterpolator())
                            .rotation((float) 90)
                            .setDuration(2000)
                            .setInterpolator(new AccelerateDecelerateInterpolator())
                            .start();

                    image2.animate()
                            .scaleX((float) 3)
                            .setDuration(2000)
                            .setInterpolator(new AccelerateDecelerateInterpolator())
                            .rotation((float) 90)
                            .setDuration(2000)
                            .setInterpolator(new AccelerateDecelerateInterpolator())
                            .start();
                }
                else{
                    image.animate()
                            .scaleX((float) 1)
                            .setDuration(2000)
                            .setInterpolator(new AccelerateDecelerateInterpolator())
                            .rotation((float) -90)
                            .setDuration(2000)
                            .setInterpolator(new AccelerateDecelerateInterpolator())
                            .start();

                    image2.animate()
                            .scaleX((float) 1)
                            .setDuration(2000)
                            .setInterpolator(new AccelerateDecelerateInterpolator())
                            .rotation((float) -90)
                            .setDuration(2000)
                            .setInterpolator(new AccelerateDecelerateInterpolator())
                            .start();
                }
            }
        });

*/
    }

}
