package ningjiaxin1.bwie.com.njx20181208.model;

import ningjiaxin1.bwie.com.njx20181208.untils.MCallBack;
import ningjiaxin1.bwie.com.njx20181208.untils.NetUntils;

public class IModelmpl implements IModel{
    MCallBack mCallBack;
    @Override
    public void setRequest(String url, Class clazz, MCallBack callBack) {
        mCallBack=callBack;
        NetUntils.getInstance().getRequest(url, clazz, new NetUntils.CallBack() {
            @Override
            public void onSuccess(Object o) {
                mCallBack.getdata(o);
            }
        });
    }
}
