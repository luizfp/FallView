package br.com.luizfp.fall_view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by luiz on 10/6/15.
 */
public class FallView {

    private Context context;
    private String rotationSide;
    private long duration;
    private float screenHeight;
    private View view;
    public static final String TO_LEFT = "LEFT";
    public static final String TO_RIGHT = "RIGHT";
    private static final long DEFAULT_DURATION = 1000;

    public FallView(Context context) {
        this.context = context;
    }



    public FallView fallThisView(View view) {
        this.view = view;
        this.screenHeight = getScreenHeight();
        return this;
    }

    public void start() {
        // Se a duração passada conter qualquer valor maior que 0 ela vai sobrepor a duração default
        long duration = ((this.duration > 0) ? this.duration : DEFAULT_DURATION);

        // Assim o default será a rotação para a esquerda
        if (rotationSide != null && rotationSide.equals(TO_RIGHT)) {
            ObjectAnimator animRotate = ObjectAnimator.ofFloat(view, "rotation", 0f, 40f);
            // Sempre desce screenHeight no eixo y. Assume que a view está no local mais alto da
            // tela possível
            ObjectAnimator animY = ObjectAnimator.ofFloat(view, "translationY", 0f, screenHeight);
            AnimatorSet anim = new AnimatorSet();
            anim.setDuration(duration);
            anim.playTogether(animRotate, animY);
            anim.start();
        } else {
            ObjectAnimator animRotate = ObjectAnimator.ofFloat(view, "rotation", 0f, -40f);
            ObjectAnimator animY = ObjectAnimator.ofFloat(view, "translationY", 0f, screenHeight);
            AnimatorSet anim = new AnimatorSet();
            anim.setDuration(duration);
            anim.playTogether(animRotate, animY);
            anim.start();

        }
        // Para não usar sempre a mesma duração e nem sentido de rotação
        this.duration = -1;
        this.rotationSide = "";
    }

    public FallView setFallRotationSide(String rotationSide) {
        this.rotationSide = rotationSide;
        return this;
    }

    public FallView setFallDuration(long duration) {
        this.duration = duration;
        return this;
    }

    private float getScreenHeight() {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.density * dm.heightPixels;
    }

    private float getScreenWidth() {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.density * dm.widthPixels;
    }

}