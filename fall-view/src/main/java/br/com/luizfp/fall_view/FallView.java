package br.com.luizfp.fall_view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by luiz on 10/6/15.
 */
public class FallView implements Animator.AnimatorListener {
    private static final long DEFAULT_DURATION = 1000;
    public static final String TO_RIGHT = "RIGHT";
    public static final String TO_LEFT = "LEFT";
    private Animator.AnimatorListener mListener;
    private String mRotationSide;
    private long mDuration;
    private float mScreenHeight;
    private View mView;
    private volatile static FallView sInstance;

    private FallView(View view) {
        this.mView = view;
        // Só calcula altura da tela se já não tiver calculado
        this.mScreenHeight = mScreenHeight == 0 ? getmScreenHeight(mView.getContext()) : mScreenHeight;
    }

    public static FallView fall(View view) {
        if (sInstance == null) {
            synchronized (FallView.class) {
                if (sInstance == null) {
                    sInstance = new FallView(view);
                }
            }
        }

        sInstance.mView = view;
        return sInstance;
    }

    public void start() {
        if (mView == null)
            return;

        // Se a duração passada conter qualquer valor maior que 0 ela vai sobrepor a duração default
        long duration = ((this.mDuration > 0) ? this.mDuration : DEFAULT_DURATION);

        ObjectAnimator rotation = ObjectAnimator.ofFloat(mView, "rotation", 0f, 0f);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(mView, "translationY", 0f, mScreenHeight);

        // Assim o default será a rotação para a esquerda
        if (mRotationSide != null && mRotationSide.equals(TO_RIGHT)) {
            rotation.setFloatValues(0f, 40f);
        } else {
            rotation.setFloatValues(0f, -40f);
        }

        AnimatorSet anim = new AnimatorSet();
        anim.setDuration(duration);
        anim.playTogether(rotation, translationY);
        anim.addListener(this);
        anim.start();

        // Para não usar sempre a mesma duração e nem sentido de rotação
        this.mDuration = -1;
        this.mRotationSide = null;
    }

    public FallView setFallRotationSide(String rotationSide) {
        sInstance.mRotationSide = rotationSide;
        return sInstance;
    }

    public FallView setFallDuration(long duration) {
        sInstance.mDuration = duration;
        return sInstance;
    }

    public FallView setListener(Animator.AnimatorListener listener) {
        sInstance.mListener = listener;
        return sInstance;
    }

    private float getmScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.density * dm.heightPixels;
    }

    @Override
    public void onAnimationStart(Animator animation) {
        if (mListener != null)
            mListener.onAnimationStart(animation);
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (mListener != null)
            mListener.onAnimationEnd(animation);

        // Se a animação acabou, não precisamos mais do listener e é preciso setar para null
        // senão outras animações vão usar o mesmo listener
        this.mListener = null;
    }

    @Override
    public void onAnimationCancel(Animator animation) {
        if (mListener != null)
            mListener.onAnimationCancel(animation);
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
        if (mListener != null)
            mListener.onAnimationRepeat(animation);
    }
}