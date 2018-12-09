package ningjiaxin1.bwie.com.njx20181208.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;
import ningjiaxin1.bwie.com.njx20181208.R;
import ningjiaxin1.bwie.com.njx20181208.adapter.MyBase;
import ningjiaxin1.bwie.com.njx20181208.bean.News;
import ningjiaxin1.bwie.com.njx20181208.persenter.IPersentermpl;
import ningjiaxin1.bwie.com.njx20181208.view.IView;

public class Fragmentone extends Fragment implements IView,View.OnClickListener {
    private Banner banner;
    private XListView xlist;
    private int Page=1;
    private MyBase adapter;
    private String path="http://www.xieast.com/api/news/news.php";
    IPersentermpl iPersentermpl;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentone,container,false);
        banner = view.findViewById(R.id.banner);
        xlist = view.findViewById(R.id.xlist);
        //轮播

        adapter=new MyBase(getActivity());
        xlist.setPullLoadEnable(true);
        xlist.setPullRefreshEnable(true);
        iPersentermpl = new IPersentermpl(this);
        xlist.setAdapter(adapter);
        Page=1;
        xlist.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                Page=1;
                initp();
            }

            @Override
            public void onLoadMore() {
               Page++;
               initp();
            }
        });
        initp();
        banner.setImageLoader(new ImageBanner());
        ArrayList<String> list=new ArrayList<>();
        list.add("http://img16.3lian.com/gif2016/q16/60/42.jpg");
        list.add("http://img16.3lian.com/gif2016/q34/11/63.jpg");
        list.add("http://img16.3lian.com/gif2016/q34/11/62.jpg");
        banner.setImages(list);
        banner.start();
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void getonSuccess(Object data) {
        News bean= (News) data;
        List<News.DataBean> data1 = bean.getData();
        Log.i("TAG",data1.size()+"");
        if(Page==1){
            adapter.setList(data1);
        }else
        {
            adapter.addList(data1);
        }
        Page++;
        xlist.stopLoadMore();
        xlist.stopRefresh();

    }

    private class ImageBanner extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            com.nostra13.universalimageloader.core.ImageLoader instance = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
            instance.displayImage((String) path,imageView);
        }
    }
    private void initp(){
        iPersentermpl.startRequest(path,News.class);
    }
}
