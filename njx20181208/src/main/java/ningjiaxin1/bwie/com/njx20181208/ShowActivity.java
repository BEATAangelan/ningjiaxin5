package ningjiaxin1.bwie.com.njx20181208;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import ningjiaxin1.bwie.com.njx20181208.fragment.Fragmentone;
import ningjiaxin1.bwie.com.njx20181208.fragment.Fragmenttwo;

public class ShowActivity extends AppCompatActivity {
    private ViewPager pager;
    private RadioGroup group;
    private RadioButton shou_button,my_button;
    private String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        pager=findViewById(R.id.pager);
        group = findViewById(R.id.group);
        shou_button = findViewById(R.id.shou_button);
        my_button = findViewById(R.id.my_button);
        final ArrayList<Fragment> list=new ArrayList<>();
        list.add(new Fragmentone());
        list.add(new Fragmenttwo());
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        Log.i("TAG",phone);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.shou_button:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.my_button:
                        pager.setCurrentItem(1);
                        break;
                    default:
                        break;
                }
            }
        });
    }
    public String intentzhi()
    {
        return phone;

    }
}
