package br.com.luizfp.fallview;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.luizfp.fall_view.FallView;


public class MainActivity extends AppCompatActivity implements Animator.AnimatorListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView img = (ImageView)findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FallView
                        .fall(img)
                        .setFallDuration(2500)
                        .setListener(MainActivity.this)
                        .start();
            }
        });

        final TextView txt = (TextView)findViewById(R.id.txt);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FallView
                        .fall(txt)
                        .setFallRotationSide(FallView.TO_RIGHT)
                        .start();
            }
        });

        final TextView txt1 = (TextView)findViewById(R.id.txt1);
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FallView
                        .fall(txt1)
                        .setFallRotationSide(FallView.TO_LEFT)
                        .start();
            }
        });

        final TextView txt2 = (TextView)findViewById(R.id.txt2);
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FallView
                        .fall(txt2)
                        .setFallRotationSide(FallView.TO_RIGHT)
                        .start();
            }
        });


        final EditText edt = (EditText)findViewById(R.id.edt);

        final Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FallView.fall(btn)
                        .setFallDuration(2500)
                        .setFallRotationSide(FallView.TO_LEFT)
                        .start();
                FallView.fall(edt)
                        .setFallDuration(1500)
                        .setFallRotationSide(FallView.TO_RIGHT)
                        .start();
            }
        });
    }

    @Override
    public void onAnimationStart(Animator animation) {
        Log.d(TAG, "onAnimationStart()");
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        Log.d(TAG, "onAnimationEnd()");
    }

    @Override
    public void onAnimationCancel(Animator animation) {
        Log.d(TAG, "onAnimationCancel()");
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
        Log.d(TAG, "onAnimationRepeat()");
    }
}

