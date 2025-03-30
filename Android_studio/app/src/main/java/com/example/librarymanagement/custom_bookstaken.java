package com.example.librarymanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class custom_bookstaken extends BaseAdapter {
    private Context context;
    ArrayList<String> a;
    ArrayList<String> b;
    ArrayList<String> c;
    ArrayList<String> d;
    ArrayList<String> e;
    ArrayList<String> f;
//    ArrayList<String> g;
    ArrayList<String> h;
    SharedPreferences sh;
    public custom_bookstaken(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c, ArrayList<String> d, ArrayList<String> e, ArrayList<String> f, ArrayList<String> h) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.a=a;
        this.b=b;//ul
        this.c=c;
        this.d=d;
        this.e=e;
        this.f=f;
//        this.g=g;
        this.h=h;



        sh=PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return a.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public int getItemViewType(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(context);
            gridView=inflator.inflate(R.layout.activity_custom_bookstaken, null);

        }
        else
        {
            gridView=(View)convertview;

        }
        ///////////////////////
        if(android.os.Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        /////////////////////////////////
        ImageView i1=(ImageView) gridView.findViewById(R.id.imgaprtmnt);
        TextView tv2=(TextView)gridView.findViewById(R.id.n);
        TextView tv3=(TextView)gridView.findViewById(R.id.b);
        TextView tv4=(TextView)gridView.findViewById(R.id.a);
        TextView tv5=(TextView)gridView.findViewById(R.id.o);
        TextView tv6=(TextView)gridView.findViewById(R.id.d);
//        TextView tv7=(TextView)gridView.findViewById(R.id.c);
//

        java.net.URL thumb_u;
        try {

            thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000"+a.get(position));
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            i1.setImageDrawable(thumb_d);
        }
        catch (Exception e)
        {
            Log.d("errsssssssssssss",""+e);
        }

        tv2.setText(b.get(position));
        tv3.setText(c.get(position));
        tv4.setText(d.get(position));
        tv5.setText(e.get(position));
        tv6.setText(f.get(position));
//        tv7.setText(g.get(position));
        Button b1=(Button) gridView.findViewById(R.id.not);
        Button b2=(Button) gridView.findViewById(R.id.ret);
        Button b3=(Button) gridView.findViewById(R.id.ext);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik=new Intent(context,booknotfy.class);
                ik.putExtra("oid",h.get(position));
                context.startActivity(ik);

            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                return_book
                RequestQueue queue = Volley.newRequestQueue(context);
                String url = "http://" + sh.getString("ip","")+ ":5000/return_book";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String res = json.getString("task");

                            if (res.equalsIgnoreCase("success")) {

                                Toast.makeText(context, "Returned", Toast.LENGTH_SHORT).show();

                                Intent ik=new Intent(context,bookstaken.class);
                                context.startActivity(ik);

                            } else {

                                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(context, "Error" + error, Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("oid", h.get(position));


                        return params;
                    }
                };
                queue.add(stringRequest);
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik=new Intent(context,extndduedate.class);
                ik.putExtra("oid",h.get(position));
                context.startActivity(ik);
            }
        });

//
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent ii =new Intent(context,user_libraryhome.class);
//
//                ii.putExtra("oid",h.get(position));
//
//                context.startActivity(ii);
//            }
//        });
//
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent ii =new Intent(context,user_libraryhome.class);
//
//                ii.putExtra("oid",h.get(position));
//
//                context.startActivity(ii);
//            }
//        });
//
//        b3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent ii =new Intent(context,user_libraryhome.class);
//
//                ii.putExtra("oid",h.get(position));
//
//                context.startActivity(ii);
//            }
//        });

        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);
        tv6.setTextColor(Color.BLACK);
//        tv7.setTextColor(Color.BLACK);
//

        return gridView;

    }

}





