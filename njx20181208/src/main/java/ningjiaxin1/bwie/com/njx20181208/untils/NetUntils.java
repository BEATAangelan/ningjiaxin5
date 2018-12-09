package ningjiaxin1.bwie.com.njx20181208.untils;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetUntils {
    private Gson gson;
    private static NetUntils instance;
    private NetUntils(){
        gson=new Gson();
    }
    public static NetUntils getInstance(){
        if(instance==null){
            instance=new NetUntils();
        }
        return instance;
    }
    public interface CallBack<T>{
        void onSuccess(T t);
    }
    public void getRequest(final String urlstr,final Class clazz,final CallBack callBack){
        new AsyncTask<String,Void,Object>(){

            @Override
            protected Object doInBackground(String... strings) {
                return getRequest(urlstr,clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                callBack.onSuccess(o);
            }
        }.execute(urlstr);
    }
    public  <T> T  getRequest(String urlstr, Class clazz) {
        T t = (T) gson.fromJson(getRequest(urlstr), clazz);
        return t;
    }

    public String getRequest(String urlstr) {
        String result="";
        try {
            URL url = new URL(urlstr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            urlConnection.setRequestMethod("GET");
            int responseCode = urlConnection.getResponseCode();

            if(responseCode==200){
                result = stream(urlConnection.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String stream(InputStream inputStream) throws IOException {
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder stringBuilder = new StringBuilder();
        for(String tmp=br.readLine();tmp!=null;tmp=br.readLine()){
            stringBuilder.append(tmp);
        }
        return stringBuilder.toString();
    }
}
