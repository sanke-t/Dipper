package com.example.sanket.dipperassignment;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;


public class BookedScreen extends ActionBarActivity {
    String ship,mat,i,book,amnt,nme,add,add1,ph,ph1;
    TextView shipper,shipper1,material,material1,id,id1,booking,booking1,amount,amount1,heading,headingt,name,name1,namet,namet1,address,address1,addresst,addrest1,phone,phone1,phonet,phonet1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_screen);
        RequestQueue q=VolleySingleton.getInstance().getRequestQueue();
        JsonObjectRequest re=new JsonObjectRequest(Request.Method.GET,"https://jsonblob.com/api/jsonblob/569a05b3e4b01190df49b82f", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) throws JSONException {
                try {
                    i=response.getString("id");
                    ship=response.getJSONObject("shipper").getString("company_name");
                    mat=response.getJSONObject("load").getString("material_type");
                    book=response.getString("booking_price");
                    amnt=response.getString("total_amount");
                    nme=response.getJSONObject("trucker").getString("trucker_name");
                    nme=nme.concat(" from ");
                    nme=nme.concat(response.getJSONObject("trucker").getString("company_name"));
                    add1=response.getJSONObject("trucker").getString("city");
                    add1=add1.concat(", ");
                    add1=add1.concat(response.getJSONObject("trucker").getString("state"));
                    add=response.getJSONObject("shipper").getString("city");
                    add=add.concat(", ");
                    add=add.concat(response.getJSONObject("shipper").getString("state"));
                    ph=response.getJSONObject("shipper").getString("office_phone_no");
                    ph1=response.getJSONObject("trucker").getString("office_phone_no");
                    if(ship!=null)
                    {
                        id=(TextView)findViewById(R.id.id);
                        id1=(TextView)findViewById(R.id.id1);
                        addrest1=(TextView)findViewById(R.id.addresst1);
                        addresst=(TextView)findViewById(R.id.addresst);
                        address1=(TextView)findViewById(R.id.address1);
                        address=(TextView)findViewById(R.id.address);
                        namet1=(TextView)findViewById(R.id.namet1);
                        namet=(TextView)findViewById(R.id.namet);
                        name1=(TextView)findViewById(R.id.name1);
                        name=(TextView)findViewById(R.id.name);
                        headingt=(TextView)findViewById(R.id.headingt);
                        heading=(TextView)findViewById(R.id.heading);
                        amount1=(TextView)findViewById(R.id.amount1);
                        amount=(TextView)findViewById(R.id.amount);
                        booking1=(TextView)findViewById(R.id.booking1);
                        booking=(TextView)findViewById(R.id.booking);
                        shipper=(TextView)findViewById(R.id.shipper);
                        shipper1=(TextView)findViewById(R.id.shipper1);
                        material=(TextView)findViewById(R.id.material);
                        material1=(TextView)findViewById(R.id.material1);
                        phone=(TextView)findViewById(R.id.phone);
                        phone1=(TextView)findViewById(R.id.phone1);
                        phonet=(TextView)findViewById(R.id.phonet);
                        phonet1=(TextView)findViewById(R.id.phonet1);
                        shipper.setText("SHIPPER: ");
                        shipper1.setText(ship);
                        material.setText("MATERIAL: ");
                        material1.setText(mat);
                        id.setText("ID: ");
                        id1.setText(i);
                        booking.setText("BOOKING AMOUNT: ");
                        booking1.setText(book);
                        amount.setText("TOTAL AMOUNT ");
                        amount1.setText(amnt);
                        name.setText("NAME: ");
                        name1.setText(ship);
                        address.setText("ADDRESS: ");
                        address1.setText(add);
                        addrest1.setText(add);
                        phone.setText("PHONE: ");
                        phone1.setText(ph);
                        namet.setText("NAME: ");
                        namet1.setText(nme);
                        addresst.setText("ADDRESS: ");
                        addrest1.setText(add1);
                        phonet.setText("PHONE: ");
                        phonet1.setText(ph1);
                        heading.setText("SHIPPER INFO");
                        headingt.setText("TRUCKER INFO");
                    }



                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                error.printStackTrace(pw);
                TextView a = (TextView)findViewById(R.id.a);
                a.setText(sw.toString());
            }
        });

        // req.setRetryPolicy(new DefaultRetryPolicy(12000, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        q.add(re);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_booked_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
