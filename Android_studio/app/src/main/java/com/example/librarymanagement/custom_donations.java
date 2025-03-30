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

public class custom_donations extends BaseAdapter {
    private Context context;
    ArrayList<String> a;
    ArrayList<String> b;
    ArrayList<String> c;
    ArrayList<String> d;
    ArrayList<String> e;
    ArrayList<String> f;
    ArrayList<String> g;
    ArrayList<String> h;
    ArrayList<String> i;
    ArrayList<String> status;
    ArrayList<String> did;
    SharedPreferences sh;
    public custom_donations(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c, ArrayList<String> d, ArrayList<String> e, ArrayList<String> f, ArrayList<String> g, ArrayList<String> h, ArrayList<String> i, ArrayList<String> status ,ArrayList<String> did) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
        this.e=e;
        this.f=f;
        this.g=g;
        this.h=h;
        this.i=i;
        this.status=status;
        this.did=did;




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
            gridView=inflator.inflate(R.layout.activity_custom_donations, null);

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
        TextView tv2=(TextView)gridView.findViewById(R.id.na);
        TextView tv3=(TextView)gridView.findViewById(R.id.bo);
        TextView tv7=(TextView)gridView.findViewById(R.id.au);
        Button b1=(Button) gridView.findViewById(R.id.add);
        Button b2=(Button) gridView.findViewById(R.id.button17);

        if(!status.get(position).equals("pending"))
        {
            b1.setVisibility(View.GONE);
        }
        else
        {
            b1.setVisibility(View.VISIBLE);
        }
        b1.setTag(position);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=Integer.parseInt(view.getTag().toString());

                RequestQueue queue = Volley.newRequestQueue(context);
                String url = "http://" + sh.getString("ip","")+ ":5000/addbook_fromdonations";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String res = json.getString("task");

                            if (res.equalsIgnoreCase("success")) {
                                Toast.makeText(context, "added", Toast.LENGTH_SHORT).show();

//
                                Intent ik = new Intent(context.getApplicationContext(), donations.class);
                                context.startActivity(ik);

                            } else {

                                Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(context.getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("name",c.get(pos) );
                        params.put("details", f.get(pos));
                        params.put("stock",e.get(pos));
                        params.put("author",g.get(pos) );
                        params.put("file",a.get(pos) );
                        params.put("review",h.get(pos) );
                        params.put("category",i.get(pos) );
                        params.put("did",did.get(pos) );
                        params.put("lid",sh.getString("lid","") );
                        return params;
                    }
                };
                queue.add(stringRequest);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ii =new Intent(context,donation_more.class);
                ii.putExtra("did",did.get(position));
                context.startActivity(ii);
            }
        });





        java.net.URL thumb_u;
        try {
//            thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");
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

        tv7.setText(g.get(position));



        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv7.setTextColor(Color.BLACK);


        return gridView;

    }

}





