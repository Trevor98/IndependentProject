

import android.content.Context;
import android.graphics.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by weapo on 1/23/2017.
 */

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder mHolder;
    private android.hardware.Camera mCamera;
    public CameraView(Context context, android.hardware.Camera camera){
        super(context);
        
        mCamera = camera;
        mCamera.setDisplayOrientation(90);

        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
    }
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder){
        try{
            mCamera.setPreviewDisplay(surfaceHolder);
            mCamera.startPreview();
        }catch(IOException e){
            Log.d("ERROR", "Camera error on surfaceCreated" + e.getMessage());
        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3){
        if(mHolder.getSurface() == null){
            return;
        }
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        }catch(IOException e){
            Log.d("ERROR", "Error on surfaceChanged" + e.getMessage());
        }
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder){
        mCamera.stopPreview();
         mCamera.release();
    }
}
