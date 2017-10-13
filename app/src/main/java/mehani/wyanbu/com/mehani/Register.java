package mehani.wyanbu.com.mehani;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mehani.wyanbu.com.mehani.GlobalVariables.NetWorks;
import mehani.wyanbu.com.mehani.GlobalVariables.Variables;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


    }

    public void check_fun(View v) {
        final EditText check = (EditText) findViewById(R.id.Reg_phoneinput);
        if(check.getText().toString().trim().length()==10){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.loadingdialog, null);
        builder.setView(dialogView);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        dialog.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, NetWorks.Register,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject json = new JSONObject(response);
                            JSONObject json1 = json.getJSONObject("Result");
                            JSONObject json2;
                            if (json1.has("Exists")) {
                                json2 = json1.getJSONObject("Exists");
                                Toast.makeText(Register.this, "مسجل مسبقا", Toast.LENGTH_SHORT).show();
                            } else {
                                json2 = json1.getJSONObject("Success");
                                Toast.makeText(Register.this, "حساب جديد", Toast.LENGTH_SHORT).show();

                            }

                            SharedPreferences.Editor editor = getSharedPreferences(Variables.SharedPreferences, MODE_PRIVATE).edit();
                            editor.putString(Variables.SharedPreferencesID, json2.getString("username"));
                            editor.putString(Variables.SharedPreferencesPassword, json2.getString("password"));
                            editor.apply();

                            startActivity(new Intent(getApplicationContext(), MainCareers.class));

                            dialog.cancel();


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Error Main catch :", e.toString());
                            dialog.cancel();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.e("Error main :", error.toString());
                        dialog.cancel();


                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mobile", check.getText().toString());

                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(postRequest);


        }else {
            Toast.makeText(this, "أدخال خاطىء", Toast.LENGTH_SHORT).show();
            check.setText("");
        }

    }
}
