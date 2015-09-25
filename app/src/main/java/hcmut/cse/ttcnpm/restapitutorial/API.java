package hcmut.cse.ttcnpm.restapitutorial;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by hungphongbk on 9/25/15.
 */
public class API {
    private static Reader reader=null;
    public static Reader getData(String urlString) throws IOException {
        InputStream is=null;

        URL url=new URL(urlString);
        HttpURLConnection conn=(HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();

        if(conn.getResponseCode()==200){
            is=conn.getInputStream();
            reader = new InputStreamReader(is);
        }
        return reader;
    }
}
