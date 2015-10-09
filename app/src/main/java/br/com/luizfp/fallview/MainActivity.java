package br.com.luizfp.fallview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.luizfp.fall_view.FallView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FallView fallView = new FallView(this);

        final ImageView img = (ImageView)findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fallView.fallThisView(img).setFallDuration(2500).start();
            }
        });

        final TextView txt = (TextView)findViewById(R.id.txt);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fallView.fallThisView(txt);
                fallView.setFallRotationSide(FallView.TO_RIGHT);
                fallView.start();
            }
        });

        final TextView txt1 = (TextView)findViewById(R.id.txt1);
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fallView.fallThisView(txt1);
                fallView.setFallRotationSide(FallView.TO_LEFT);
                fallView.start();
            }
        });

        final TextView txt2 = (TextView)findViewById(R.id.txt2);
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fallView.fallThisView(txt2);
                fallView.setFallRotationSide(FallView.TO_RIGHT);
                fallView.start();
            }
        });


        final EditText edt = (EditText)findViewById(R.id.edt);

        final Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fallView.fallThisView(btn)
                        .setFallDuration(2500)
                        .setFallRotationSide(FallView.TO_LEFT)
                        .start();
                fallView.fallThisView(edt)
                        .setFallDuration(1500)
                        .setFallRotationSide(FallView.TO_RIGHT)
                        .start();
            }
        });
    }
}

