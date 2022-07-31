package com.developerdiindy.shopit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SHOP";
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tv_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    requestRestJSONWithSomeHttpHeaders();
                } catch (AuthFailureError authFailureError) {
                    authFailureError.printStackTrace();
                }
            }
        });
    }

    public  JSONObject json_object(){

        JSONObject param= new JSONObject();

        try{
            param.put("providerCallbackHost", "https://webhook.site/de55348f-c6e2-4518-bc48-1661f1efeec9");
            textView.setText(param.toString());

        }catch(Exception e){}
        return param;

    }

    private void requestPOST() {
        String url = "https://sandbox.momodeveloper.mtn.com/v1_0/apiuser";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, json_object(),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        textView.setText(response.toString());
                        //hideProgressDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                //hideProgressDialog();
            }
        }) {

            //@Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("X-Reference-Id", "b1dd5140-0b5f-483e-a9d1-12447214ca76");
                headers.put("Ocp-Apim-Subscription-Key", "70d55faeb88e4515886ddaf4ffe3f963");

                return headers;
            }
        };

        // Adding request to request queue
        MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonObjReq);

    }




    public void requestRestJSONArrayWithSomeHttpHeaders() throws AuthFailureError {

        String url = "https://sandbox.momodeveloper.mtn.com/collection/v1_0/requesttopay/4faa50bf-317d-4da2-947e-0f5cfbfcbb31";

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSMjU2In0.eyJjbGllbnRJZCI6IjcwYThjYTQ1LWJmNzAtNDM2Ny05M2I2LWIzMTljZDk3YzVmYyIsImV4cGlyZXMiOiIyMDIyLTA3LTMxVDEzOjU3OjExLjM3MyIsInNlc3Npb25JZCI6ImY0NTA4NjdiLWQ1M2UtNGZhMC05ZDFmLTg1MWZjMGQ5NjdiNyJ9.B0QtENl_S7HPnprOnjQOYr1vQJAaexwcAC9IfxhqpzaWaG36Vrz1lyH4MWs2Wa2d6HfH28hQrdOFMBiylBiTtPESvIGgKQuVvpga_DC9qu_F91xzi3LZWz4beT0A9fXLuOl1EWEdy3LHHWvbQp6ofnYPd6J4kCKzNhHyd6e0RACH-yF4KBIsbVq8FAbjedDrg31CAmvSNMOzVARetBmO-73e4ao-zuqVJiEeYVDGmlR1G9X5hFbxX3UW6e4Km0ye2B_t6UdLSC4twEVvc7m64Y2W3MblBsrHY9G1CvPCEstH26hzH-n4YiwcnkJurlIPIQUZ_cyyBEJDnycTlep5XQ";

        Map<String, String> params = new HashMap<String, String>();

        params.put("Content-Type", "application/json");

        params.put("Ocp-Apim-Subscription-Key", "70d55faeb88e4515886ddaf4ffe3f963");
        params.put("X-Target-Environment", "sandbox");
        params.put("X-Reference-Id", "4faa50bf-317d-4da2-947e-0f5cfbfcbb31");
        params.put("Authorization", "Bearer " + token);

        ArrayRequest jsObjRequest = new ArrayRequest(Request.Method.GET, url, params, requestArraySuccessListener(), requestErrorListener());

        MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsObjRequest);

    }

    private Response.Listener<JSONArray> requestArraySuccessListener() {
        return new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response);

                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject obj = response.getJSONObject(i);
                        //String resp = obj.getString("payeeNote");
                        Toast.makeText(MainActivity.this, obj.getString("apiKey"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        };
    }


    public void requestJSONWithSomeHttpHeaders() throws AuthFailureError {

        String url = "https://sandbox.momodeveloper.mtn.com/collection/v1_0/requesttopay/4faa50bf-317d-4da2-947e-0f5cfbfcbb31";

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSMjU2In0.eyJjbGllbnRJZCI6IjcwYThjYTQ1LWJmNzAtNDM2Ny05M2I2LWIzMTljZDk3YzVmYyIsImV4cGlyZXMiOiIyMDIyLTA3LTMxVDEzOjU3OjExLjM3MyIsInNlc3Npb25JZCI6ImY0NTA4NjdiLWQ1M2UtNGZhMC05ZDFmLTg1MWZjMGQ5NjdiNyJ9.B0QtENl_S7HPnprOnjQOYr1vQJAaexwcAC9IfxhqpzaWaG36Vrz1lyH4MWs2Wa2d6HfH28hQrdOFMBiylBiTtPESvIGgKQuVvpga_DC9qu_F91xzi3LZWz4beT0A9fXLuOl1EWEdy3LHHWvbQp6ofnYPd6J4kCKzNhHyd6e0RACH-yF4KBIsbVq8FAbjedDrg31CAmvSNMOzVARetBmO-73e4ao-zuqVJiEeYVDGmlR1G9X5hFbxX3UW6e4Km0ye2B_t6UdLSC4twEVvc7m64Y2W3MblBsrHY9G1CvPCEstH26hzH-n4YiwcnkJurlIPIQUZ_cyyBEJDnycTlep5XQ";

        Map<String, String> params = new HashMap<String, String>();

        params.put("Content-Type", "x-www-form-urlencoded");

        params.put("Ocp-Apim-Subscription-Key", "70d55faeb88e4515886ddaf4ffe3f963");
        params.put("X-Target-Environment", "sandbox");
        params.put("X-Reference-Id", "4faa50bf-317d-4da2-947e-0f5cfbfcbb31");
        params.put("X-Authorization-Copy", "Bearer " + token);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.GET, url, params, requestSuccessListener(), requestErrorListener());

        MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsObjRequest);

    }

    public void requestRestJSONWithSomeHttpHeaders() throws AuthFailureError {

        String url = "https://sandbox.momodeveloper.mtn.com/collection/v1_0/requesttopay/4faa50bf-317d-4da2-947e-0f5cfbfcbb31";

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSMjU2In0.eyJjbGllbnRJZCI6IjcwYThjYTQ1LWJmNzAtNDM2Ny05M2I2LWIzMTljZDk3YzVmYyIsImV4cGlyZXMiOiIyMDIyLTA3LTMwVDIwOjQ5OjQzLjQ3MCIsInNlc3Npb25JZCI6IjA0NDJmNGQ1LTY2NTAtNDIzYy04MTBhLTFjM2U2NDM0ZmZhMyJ9.cZwogApK0VaD7Pe7wyOYINAZ7oaZyotWaUSB65ow7Me7ogpkejh_Q77Hz3xGkEi7vhVvC_hbNPOqHcx922cPJdL5kGztiPFQXEJCxdtlAimNy_bsrA0cjwFvFq8gm1uoycfLdzwLf3NDnwjlUSei1ykuERPmRHFT40qcwAs_kzI0fpD2Z1QmUgzXRLcnT7s0rkAnI4aYBTD0N81J75v6zLl4f2uFsZIG6H-nTY9ZnQiif_mUwV9UZo_a0g_ecNvSuC5Af9An1oG-2CJS0i7Vz_ImkBJK6rToycxPK-z6hh4Kpb4qsuUIqo9HbTClwfxIjlIxqZXlCkTBWYOCHM9Bvw";

        Map<String, String> params = new HashMap<String, String>();

        params.put("User-Agent", "Mozilla/5.0");
        params.put("accept-language", "en-US");
        params.put("api-version", "1.0");

        params.put("Ocp-Apim-Subscription-Key", "70d55faeb88e4515886ddaf4ffe3f963");
        params.put("X-Target-Environment", "sandbox");
        params.put("X-Reference-Id", "4faa50bf-317d-4da2-947e-0f5cfbfcbb31");
        params.put("Authorization", "Bearer " + token);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.GET, url, params, requestSuccessListener(), requestErrorListener());

        MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsObjRequest);

    }

    private Response.ErrorListener requestErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.d("ERROR","error => "+error.toString());
                textView.setText("That didn't work!");
            }
        };
    }

    private Response.Listener<JSONObject> requestSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                try {

                    String str = response.getString("message");
                    textView.setText(str);

//                    // Parsing json
//                    for (int i = 0; i < response.length(); i++) {
//                        try {
//
//                            JSONObject obj = response.getJSONObject(i);
//                            Message msg = new Message();
//                            msg.setMessageThread(obj.getString("msgThread"));
//                            msg.setUserName(obj.getString("Username"));
//                            msg.setDate(obj.getString("msgDate"));
//
//                            // adding movie to movies array
//                            MessageList.add(msg);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }


                    // notifying list adapter about data changes
                    // so that it renders the list view with updated data
                    //adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }


}