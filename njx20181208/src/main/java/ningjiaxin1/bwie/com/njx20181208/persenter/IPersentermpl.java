package ningjiaxin1.bwie.com.njx20181208.persenter;

import ningjiaxin1.bwie.com.njx20181208.model.IModelmpl;
import ningjiaxin1.bwie.com.njx20181208.untils.MCallBack;
import ningjiaxin1.bwie.com.njx20181208.untils.NetUntils;
import ningjiaxin1.bwie.com.njx20181208.view.IView;

public class IPersentermpl implements IPersenter{
    IModelmpl iModelmpl;
    private IView miView;
    public IPersentermpl(IView iView){
       miView=iView;
       iModelmpl=new IModelmpl();
    }
    @Override
    public void startRequest(String url, Class clazz) {
        iModelmpl.setRequest(url, clazz, new MCallBack() {
            @Override
            public void getdata(Object o) {
                miView.getonSuccess(o);
            }
        });
    }
}
