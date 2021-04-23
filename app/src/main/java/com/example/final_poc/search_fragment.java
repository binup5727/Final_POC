package com.example.final_poc;

import android.text.Editable;
import android.view.View;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.Inflater;

public class search_fragment extends Fragment {

    EditText input;
    Button search_btn;
    private RequestQueue queue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.nav_search, container, false);

        queue = Volley.newRequestQueue(getContext());
        search_btn = view.findViewById(R.id.searchbtn);
        input = view.findViewById(R.id.stock);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stock = String.valueOf(input.getText());
                System.out.println(stock);
                getstock(stock);


            }
        });




        return view;
    }


    public void getstock(String stock){

        String url = getString(R.string.URL) + "q=" + stock;
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



                            JSONArray news_prot = response.getJSONArray("news");
                            JSONObject news = (JSONObject)news_prot.get(0);
                            String title = news.getString("title");
                            String link = news.getString("link");




                            System.out.println(name + "|"
                            + index + "|" + score + "|" + title + "|" + link + "|");




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
