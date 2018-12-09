package ningjiaxin1.bwie.com.njx20181208.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import ningjiaxin1.bwie.com.njx20181208.R;
import ningjiaxin1.bwie.com.njx20181208.bean.News;

public class MyBase extends BaseAdapter {
    private Context context;
    private List<News.DataBean> list;
    private final int ONEIMAGE=0;
    private final int THREEIMAGE=1;
    private final int COUNT=2;

    public MyBase(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    //刷新
    public void setList(List<News.DataBean> list) {
        this.list .clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    //加载
    public void addList(List<News.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getViewTypeCount() {
        return COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).hasImage()?ONEIMAGE:THREEIMAGE;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public News.DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(getItemViewType(position) == ONEIMAGE ? R.layout.item1 : R.layout.item, parent, false);
            holder = new ViewHolder();
            holder.image1 = convertView.findViewById(R.id.img1);
            holder.image2 = convertView.findViewById(R.id.img2);
            holder.image3 = convertView.findViewById(R.id.img3);
            holder.text = convertView.findViewById(R.id.text1);
            convertView.setTag(holder);
        }
        else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.text.setText(list.get(position).getTitle());
        ImageLoader.getInstance().displayImage(getItem(position).getThumbnail_pic_s(),holder.image1);
        if(getItemViewType(position)!=ONEIMAGE){
            ImageLoader.getInstance().displayImage(getItem(position).getThumbnail_pic_s02(),holder.image2);
            ImageLoader.getInstance().displayImage(getItem(position).getThumbnail_pic_s03(),holder.image3);
        }
        return convertView;
    }
    class ViewHolder{
        ImageView image3,image1,image2;
        TextView text;
    }
}
