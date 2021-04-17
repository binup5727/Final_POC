package com.example.final_poc;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class favorite_fragment extends Fragment {
    ListView list;
    ArrayList<String> stock_list = new ArrayList<String>();
    private RequestQueue queue;
    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.nav_favorite, container, false);


        queue = Volley.newRequestQueue(getContext());
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                writelist(view);
            }
        }, 5000);
        getstock2();

        System.out.println(stock_list.size() + " list size");
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======







>>>>>>> 567e7959d1dfdf8f1e8cbffb4bfff9880a92aca2
>>>>>>> 23bd78077fdf7613f52707854d1d15536ae946f7
>>>>>>> ed9491132a2dcd4309250d962f6b396418cb2a3c
        return view;
    }

    public void writelist(View view){

        System.out.println("stock list below");
<<<<<<< HEAD
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,
                stock_list);
=======
<<<<<<< HEAD
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,
                stock_list);
=======
<<<<<<< HEAD
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,
                stock_list);
=======
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, stock_list);
>>>>>>> 567e7959d1dfdf8f1e8cbffb4bfff9880a92aca2
>>>>>>> 23bd78077fdf7613f52707854d1d15536ae946f7
>>>>>>> ed9491132a2dcd4309250d962f6b396418cb2a3c
        list = (ListView)view.findViewById(R.id.stock_list);
        list.setAdapter(itemsAdapter);
        System.out.println("stock list above");
    }
    public void getstock2(){
        System.out.println("here for second stock");
        String url = getString(R.string.URL) + "q=tesla";
        System.out.println(url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray stock = null;
                            stock = response.getJSONArray("quotes");
                            JSONObject usstock = (JSONObject) stock.get(0);
                            String name = usstock.getString("longname");
                            String index = usstock.getString("index");
                            String score = usstock.getString("score");

                            stock_list.add("Name: " + name);
                            stock_list.add("index: " + index);
                            stock_list.add("score: " + score);


<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 23bd78077fdf7613f52707854d1d15536ae946f7
>>>>>>> ed9491132a2dcd4309250d962f6b396418cb2a3c
                            JSONArray news_prot = response.getJSONArray("news");
                            JSONObject news = (JSONObject)news_prot.get(0);
                            String title = news.getString("title");
                            String link = news.getString("link");

                            stock_list.add("News: " + title);
                            stock_list.add("link: " + link);

<<<<<<< HEAD
                            System.out.println(usstock.getString("shortname")+"|||||");


=======
<<<<<<< HEAD
                            System.out.println(usstock.getString("shortname")+"|||||");


=======
                            System.out.println(usstock.getString("shortname")+"|||||");


=======
                            System.out.println(usstock.getString("shortname")+"|||||");
>>>>>>> 567e7959d1dfdf8f1e8cbffb4bfff9880a92aca2
>>>>>>> 23bd78077fdf7613f52707854d1d15536ae946f7
>>>>>>> ed9491132a2dcd4309250d962f6b396418cb2a3c
                            getstock3();
                        } catch (JSONException e) {
                            System.out.println("ERROR WITH call");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR WITH VOLLEY");
            }
        });
        queue.add(jsonObjectRequest);



    }

    public void getstock3(){
        String url = getString(R.string.URL) + "q=walmart";
        System.out.println(url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray stock = null;
                            stock = response.getJSONArray("quotes");
                            System.out.println(stock+"|||||");
                            JSONObject usstock = (JSONObject) stock.get(0);
                            String name = usstock.getString("longname");
                            String index = usstock.getString("index");
                            String score = usstock.getString("score");

                            stock_list.add("Name: " + name);
                            stock_list.add("index: " + index);
                            stock_list.add("score: " + score);


<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 23bd78077fdf7613f52707854d1d15536ae946f7
>>>>>>> ed9491132a2dcd4309250d962f6b396418cb2a3c
                            JSONArray news_prot = response.getJSONArray("news");
                            JSONObject news = (JSONObject)news_prot.get(0);
                            String title = news.getString("title");
                            String link = news.getString("link");

                            stock_list.add("News: " + title);
                            stock_list.add("link: " + link);

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
>>>>>>> 567e7959d1dfdf8f1e8cbffb4bfff9880a92aca2
>>>>>>> 23bd78077fdf7613f52707854d1d15536ae946f7
>>>>>>> ed9491132a2dcd4309250d962f6b396418cb2a3c

                            getstock4();
                        } catch (JSONException e) {
                            System.out.println("ERROR WITH call");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR WITH VOLLEY");
            }
        });
        queue.add(jsonObjectRequest);



    }

    public void getstock4(){
        String url = getString(R.string.URL) + "q=toyota";
        System.out.println(url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray stock = null;
                            stock = response.getJSONArray("quotes");
                            System.out.println(stock+"|||||");
                            JSONObject usstock = (JSONObject) stock.get(0);
                            String name = usstock.getString("longname");
                            String index = usstock.getString("index");
                            String score = usstock.getString("score");

                            stock_list.add("Name: " + name);
                            stock_list.add("index: " + index);
                            stock_list.add("score: " + score);

                            System.out.println(stock_list);


<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 23bd78077fdf7613f52707854d1d15536ae946f7
>>>>>>> ed9491132a2dcd4309250d962f6b396418cb2a3c
                            JSONArray news_prot = response.getJSONArray("news");
                            JSONObject news = (JSONObject)news_prot.get(0);
                            String title = news.getString("title");
                            String link = news.getString("link");

                            stock_list.add("News: " + title);
                            stock_list.add("link: " + link);

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
>>>>>>> 567e7959d1dfdf8f1e8cbffb4bfff9880a92aca2
>>>>>>> 23bd78077fdf7613f52707854d1d15536ae946f7
>>>>>>> ed9491132a2dcd4309250d962f6b396418cb2a3c

                        } catch (JSONException e) {
                            System.out.println("ERROR WITH call");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR WITH VOLLEY");
            }
        });
        queue.add(jsonObjectRequest);



    }


}
