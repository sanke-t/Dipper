package com.example.sanket.dipperassignment;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class MainActivity extends ActionBarActivity {

    int trailers,weight,rate;
    TextView a,b,c,d,e,f,g,i,j,k,l,m,n,o,li;
    String id,date,number,source;
    EditText h;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocationManager locationManager=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location)
            {
                li=(TextView)findViewById(R.id.location);
                li.setText(location.toString());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,locationListener);
        RequestQueue queue= VolleySingleton.getInstance().getRequestQueue();
        JsonObjectRequest req=new JsonObjectRequest(Request.Method.GET,"https://jsonblob.com/api/jsonblob/5704f337e4b01190df5c13ba", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) throws JSONException {
                try {
                    id = response.getString("postID");
                    date = response.getString("pickup date");
                    trailers = response.getInt("trailers");
                    weight = response.getInt("weight in tons");
                    number = response.getString("vehicle 1");
                    number = number.concat(", ");
                    number = number.concat(response.getString("vehicle 2"));
                    source = response.getString("source");
                    source=source.concat(" to ");
                    source=source.concat(response.getString("destination"));
                    rate = response.getInt("rate");
                    if(id!=null)
                    {
                        a = (TextView) findViewById(R.id.b);
                        b = (TextView) findViewById(R.id.d);
                        c = (TextView) findViewById(R.id.e);
                        d = (TextView) findViewById(R.id.g);
                        e = (TextView) findViewById(R.id.v);
                        f = (TextView) findViewById(R.id.r);
                        g = (TextView) findViewById(R.id.lmr);
                        h = (EditText) findViewById(R.id.bid);
                        i = (TextView) findViewById(R.id.a);
                        j = (TextView) findViewById(R.id.c);
                        k = (TextView) findViewById(R.id.f);
                        l = (TextView) findViewById(R.id.h);
                        m = (TextView) findViewById(R.id.v1);
                        n = (TextView) findViewById(R.id.route);
                        o = (TextView) findViewById(R.id.rate);
                        a.setText(id);
                        b.setText(date);
                        c.setText(String.valueOf(trailers));
                        d.setText(String.valueOf(weight));
                        e.setText(number);
                        f.setText(source);
                        g.setText(String.valueOf(rate));
                        i.setText("POST ID: ");
                        j.setText("DATE: ");
                        k.setText(" TRAILERS OF ");
                        l.setText(" TONS EACH");
                        m.setText("VEHICLE NUMBER(S)- ");
                        n.setText("ROUTE- ");
                        o.setText("LOWEST MARKET RATE- ");
                        h.setVisibility(View.VISIBLE);
                        h.setHint("YOUR BID");
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
        queue.add(req);
        button=(Button)findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(h.getText().toString())>=5000) {
                    Intent intent = new Intent(MainActivity.this, BookedScreen.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
   /* public void proceed(View v)
    {
        if(h.getText()!=null)
        {
            if(h.getText().equals("5000"))
            {
                Intent intent=new Intent(getApplicationContext(),BookedScreen.class);
                startActivity(intent);
            }
        }
    }*/

}
