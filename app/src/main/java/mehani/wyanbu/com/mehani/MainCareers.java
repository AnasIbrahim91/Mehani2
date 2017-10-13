package mehani.wyanbu.com.mehani;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mehani.wyanbu.com.mehani.CareersGridView.GridViewAdabter;
import mehani.wyanbu.com.mehani.CareersGridView.GridViewItems;
import mehani.wyanbu.com.mehani.GlobalVariables.NetWorks;
import mehani.wyanbu.com.mehani.GlobalVariables.Variables;

public class MainCareers extends AppCompatActivity {
    private List<GridViewItems> GridViewAdabter_list = new ArrayList<>();
    private RecyclerView recyclerView;
    private GridViewAdabter mGridViewAdabter;
    FloatingActionMenu menufab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_careers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Locale locale3 = new Locale("ar_");
        Locale.setDefault(locale3);
        android.content.res.Configuration config3 = new android.content.res.Configuration();
        config3.locale = locale3;
        this.getResources().updateConfiguration(config3, this.getResources().getDisplayMetrics());

        setTitle("المهن الرئيسية");

        SharedPreferences prefs = getSharedPreferences(Variables.SharedPreferences, MODE_PRIVATE);
        String ID = prefs.getString(Variables.SharedPreferencesID, "0");
        String Password = prefs.getString(Variables.SharedPreferencesPassword, "0");
        if (ID.equals("0") || Password.equals("0")) {
            startActivity(new Intent(this, Register.class));
        }

        FloatingActionButton UserOrderFab = (FloatingActionButton) findViewById(R.id.userorderfab);

        menufab = (FloatingActionMenu) findViewById(R.id.menufab);
        menufab.close(true);

        UserOrderFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menufab.close(true);
                startActivity(new Intent(getApplicationContext(), User_Order.class));

            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_Grid);
        recyclerView.setLayoutManager(new GridLayoutManager(MainCareers.this, 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        GetCareersData();


    }

    public void GetCareersData() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.loadingdialog, null);
        builder.setView(dialogView);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        dialog.show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, NetWorks.Get_AllCareers, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonObject = response.getJSONArray("AllCareers");

                    for (int i = 0; i < jsonObject.length(); i++) {
                        if (jsonObject.getJSONObject(i).getInt("contain_fields") == 1) {

                            GridViewAdabter_list.add(new GridViewItems(jsonObject.getJSONObject(i).getString("id"), jsonObject.getJSONObject(i).getString("name"), jsonObject.getJSONObject(i).getString("icon")));
                        }
                    }

                    mGridViewAdabter = new GridViewAdabter(GridViewAdabter_list);
                    recyclerView.setAdapter(mGridViewAdabter);
                    dialog.cancel();

                } catch (JSONException e) {
                    e.printStackTrace();
                    dialog.cancel();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error Home :", error.toString());
                dialog.cancel();

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);


    }

    @Override
    protected void onPostResume() {
        menufab.close(true);

        super.onPostResume();
    }
}
