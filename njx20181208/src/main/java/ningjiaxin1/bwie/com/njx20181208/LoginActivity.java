package ningjiaxin1.bwie.com.njx20181208;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class LoginActivity extends AppCompatActivity implements QRCodeView.Delegate {
    ZXingView zXingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        zXingView = findViewById(R.id.zXing);
        zXingView.setDelegate((QRCodeView.Delegate) this);
        //动态权限
        String[] p = new String[]{Manifest.permission.CAMERA};
        ActivityCompat.requestPermissions(LoginActivity.this, p, 1);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Toast.makeText(LoginActivity.this, result, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0) {
            int result = grantResults[0];
            if (result == PackageManager.PERMISSION_GRANTED) {
                //权限同意
                zXingView.startSpot();
            } else {
                //权限拒绝
                Toast.makeText(LoginActivity.this, "请同意打开摄像头权限", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
