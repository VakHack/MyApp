package async.example.com.myapp;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 27/11/2017.
 */

public class ItemsGetter {
    private static final ItemsGetter ourInstance = new ItemsGetter();

    public static ItemsGetter getInstance() {
        return ourInstance;
    }

    private ArrayList<DataItem> items;
    final String jsonURL = "http://freetexthost.in/paste.php?raw&id=162010";

    private ItemsGetter() {

        items = new ArrayList<>();

        JSONURLReader reader = new JSONURLReader(jsonURL);

        try {
            items = JSONToDataParser.parseJSON(reader.execute().get(1000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DataItem> get() {
        return items;
    }

}
