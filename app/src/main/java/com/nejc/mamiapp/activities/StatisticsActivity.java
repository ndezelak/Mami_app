package com.nejc.mamiapp.activities;
/**
 * @author Nejc
 * <p>
 * Description:
 * Central UI module for displaying calendar statistics
 */
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nejc.mamiapp.R;
import com.nejc.mamiapp.adapters.statisticsActivity.GraphViewPagerAdapter;
import com.nejc.mamiapp.fragments.statisticsActivity.SettingsFragment;


/*********** REVISION HISTORY *****************
 * 31/03/2017
 *          + Defined basic structure of the UI
 *
 *
 *
 *
 /***********************************************/

public class StatisticsActivity extends AppCompatActivity {
    int state;
    ImageView image, image2;
    final String LOG_ERROR = "Statistics error";
    SettingsFragment mSettingsFragment;
    LinearLayout rootView ;
    FrameLayout settingsFrame;
    boolean settingsVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_activity);
        settingsVisibility =false;
        // GUI element initialization
        ViewPager graph = (ViewPager) findViewById(R.id.pager);
        graph.setOffscreenPageLimit(1);
        graph.setAdapter(new GraphViewPagerAdapter(getSupportFragmentManager()));

        // OnClickListener for month/year selection
        LinearLayout upperFrame = (LinearLayout) findViewById(R.id.title_frame);
        upperFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.month_statistics_button:
                        break;
                    case R.id.year_statistics_button:
                        break;
                    default:
                        Log.e(LOG_ERROR, "Unexpected clicked view!");
                }
            }
        });
        // Settings Fragement stuff
        mSettingsFragment = new SettingsFragment();
        mSettingsFragment.setParent(this);
        // Reference to Background Layout in order to perform bluring
        rootView = (LinearLayout) findViewById(R.id.root_layout);
        // Settingsbutton configuration
        ImageButton settingsButton = (ImageButton) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(settingsVisibility){
                   // rootView.animate().alpha((float)1.0).setDuration(2000);
                    settingsVisibility=false;
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.show,R.anim.hide)
                            .remove(mSettingsFragment).commit();
                    AlphaAnimation anim = new AlphaAnimation((float)0.1,(float)1.0);
                    anim.setDuration(1000);
                    rootView.setAlpha((float)1.0);
                    rootView.startAnimation(anim);
                }else{
                    getSupportFragmentManager().beginTransaction() .setCustomAnimations(R.anim.show,R.anim.hide)
                            .add(R.id.settingsFrame,mSettingsFragment,null).commit();
                    //rootView.animate().alpha((float)0.5).setDuration(1000);
                    settingsVisibility=true;
                    AlphaAnimation anim = new AlphaAnimation((float)1.0,(float)0.1);
                    anim.setDuration(1000);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                                rootView.setAlpha((float)0.1);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    rootView.startAnimation(anim);
                }
            }
        });
    }

}
