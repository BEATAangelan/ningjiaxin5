package ningjiaxin1.bwie.com.njx20181208.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
import ningjiaxin1.bwie.com.njx20181208.LoginActivity;
import ningjiaxin1.bwie.com.njx20181208.R;
import ningjiaxin1.bwie.com.njx20181208.ShowActivity;

public class Fragmenttwo extends Fragment {
    ImageView  imageView;
    Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenttwo,container,false);
        imageView=view.findViewById(R.id.erweima);
        button = view.findViewById(R.id.sao);
        String phone = ((ShowActivity) getActivity()).intentzhi();
        init(phone);
       // Log.i("TAG",phone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             initView();
            }
        });
        return view;
    }
    private void init(String phone) {
        QRTask qrTask = new QRTask(getActivity(),imageView);
        qrTask.execute(phone);
    }

    private class QRTask extends AsyncTask<String,Void,Bitmap> {
        private WeakReference<Context> mContext;
        private WeakReference<ImageView> mImageView;

        public QRTask(Context context, ImageView imageView) {
            mContext=new WeakReference<>(context);
            mImageView = new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String str=strings[0];

            Log.i("TAG",str);
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            int size=mContext.get().getResources().getDimensionPixelOffset(R.dimen.ewm_pzq);
            return QRCodeEncoder.syncEncodeQRCode(str, size);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                mImageView.get().setImageBitmap(bitmap);
            } else {
                Toast.makeText(mContext.get(), "生成失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void initView(){
//        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},100);
            }else{
                startActivity(new Intent(getActivity(),LoginActivity.class));
            }
//        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //如果requestCode匹配，切权限申请通过
        if (requestCode==100&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            startActivity(new Intent(getActivity(),LoginActivity.class));
        }
    }

}
