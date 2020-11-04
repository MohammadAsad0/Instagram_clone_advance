package com.example.instagram_clone;

import android.content.Context;
import android.graphics.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class showCamera extends SurfaceView implements SurfaceHolder.Callback {
    Camera camera;
    SurfaceHolder surfaceHolder;

    public showCamera(Context context, Camera camera) {
        super(context);

        this.camera=camera;
        surfaceHolder=getHolder();
        surfaceHolder.addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
