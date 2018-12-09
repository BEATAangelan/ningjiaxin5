package ningjiaxin1.bwie.com.njx20181208;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import ningjiaxin1.bwie.com.njx20181208.bean.User;
import ningjiaxin1.bwie.com.njx20181208.persenter.IPersentermpl;
import ningjiaxin1.bwie.com.njx20181208.view.IView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {
    private EditText edit_phone,edit_pwd;
    private CheckBox checkBox1,checkBox2;
    private Button deng_but,zhu_but,san_but;
    IPersentermpl iPersentermpl;
    private String path1="http://www.zhaoapi.cn/user/login?moblie=%s&Password=%s";
    SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private UMAuthListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取资源id
        edit_phone = findViewById(R.id.edit_phone);
        edit_pwd = findViewById(R.id.edit_pwd);
        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        deng_but = findViewById(R.id.deng_but);
        zhu_but = findViewById(R.id.zhu_but);
        san_but = findViewById(R.id.san_but);
        iPersentermpl = new IPersentermpl(this);
        deng_but.setOnClickListener(this);
        san_but.setOnClickListener(this);


        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */ /**
          * @desc 授权成功的回调
          * @param platform 平台名称
          * @param action 行为序号，开发者用不上
          * @param data 用户资料返回
          */ /**
           * @desc 授权失败的回调
           * @param platform 平台名称
           * @param action 行为序号，开发者用不上
           * @param t 错误原因
           */ /**
            * @desc 授权取消的回调
            * @param platform 平台名称
            * @param action 行为序号，开发者用不上
            */authListener = new UMAuthListener() {
                /**
                 * @desc 授权开始的回调
                 * @param platform 平台名称
                 */
                @Override
                public void onStart(SHARE_MEDIA platform) {

                }

                /**
                 * @desc 授权成功的回调
                 * @param platform 平台名称
                 * @param action 行为序号，开发者用不上
                 * @param data 用户资料返回
                 */
                @Override
                public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

                    Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();

                }

                /**
                 * @desc 授权失败的回调
                 * @param platform 平台名称
                 * @param action 行为序号，开发者用不上
                 * @param t 错误原因
                 */
                @Override
                public void onError(SHARE_MEDIA platform, int action, Throwable t) {

                    Toast.makeText(MainActivity.this,"失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
                }

                /**
                 * @desc 授权取消的回调
                 * @param platform 平台名称
                 * @param action 行为序号，开发者用不上
                 */
                @Override
                public void onCancel(SHARE_MEDIA platform, int action) {
                    Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
                }
            };


       // UMShareAPI.get(this).deleteOauth(Activity, Platform, authListener);

        sharedPreferences= getSharedPreferences("User", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        findViewById(R.id.deng_but).setOnClickListener(this);
        sharedPreferences= getSharedPreferences("Use", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        boolean r_ischeck = sharedPreferences.getBoolean("r_ischeck", false);
        if(r_ischeck) {
            String pho = sharedPreferences.getString("phone", null);
            String pwd = sharedPreferences.getString("pwd", null);
            edit_phone.setText(pho);
            edit_pwd.setText(pwd);
            checkBox1.setChecked(true);
            deng_but.setOnClickListener(this);
        }
        boolean v_ischeck = sharedPreferences.getBoolean("v_ischeck", false);
        if(v_ischeck){
            Intent intent = new Intent(MainActivity.this,ShowActivity.class);
            startActivity(intent);
            finish();
        }
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox2.isChecked()){
                    checkBox1.setChecked(true);
                }
            }
        });
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!checkBox1.isChecked()){
                    checkBox2.setChecked(false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.deng_but:
                String phone = edit_phone.getText().toString();
                String pwd = edit_pwd.getText().toString();

                if (checkBox1.isChecked()){
                    editor.putString("phone", phone);
                    editor.putString("pwd", pwd);
                    editor.putBoolean("r_ischeck", true);
                }else {
                    editor.putBoolean("r_ischeck", false);
                }
                if (checkBox2.isChecked()){
                    editor.putBoolean("v_ischeck",true);
                }else {
                    editor.putBoolean("v_ischeck",false);
                }
                editor.commit();
                iPersentermpl.startRequest(String.format(path1,phone,pwd),User.class);

                if (checkBox2.isChecked()){
                    editor.putBoolean("c_login",true);
                    editor.commit();
                }
                Intent intent=new Intent(MainActivity.this,ShowActivity.class);
                intent.putExtra("phone",phone);
                startActivity(intent);
                iPersentermpl.startRequest(String.format(path1,phone,pwd),User.class);
                break;
            case R.id.san_but:

                UMShareAPI.get(MainActivity.this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, authListener);
                break;
            default:
                break;
        }
    }

    @Override
    public void getonSuccess(Object data) {
        User bean= (User) data;
        int code = bean.getCode();
        String msg = bean.getMsg();
        String phone = bean.getData().getMobile();
        if(code==0){
            Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this,ShowActivity.class);
            intent.putExtra("phone",phone);
            startActivity(intent);
        }else{
            Toast.makeText(this,msg , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
