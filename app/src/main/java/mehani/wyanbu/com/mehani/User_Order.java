package mehani.wyanbu.com.mehani;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mehani.wyanbu.com.mehani.GlobalVariables.NetWorks;
import mehani.wyanbu.com.mehani.UserOrder.UserOrder_Adabter;
import mehani.wyanbu.com.mehani.UserOrder.UserOrder_Items;

public class User_Order extends AppCompatActivity {
    private List<UserOrder_Items> userOrder_itemsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserOrder_Adabter mUserOrder_adabter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

            setTitle("طلباتي");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_User_Orfer);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        GetUserRequestData("");
    }

    private void GetUserRequestData(String id) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.loadingdialog, null);
        builder.setView(dialogView);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        dialog.show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, NetWorks.UserOrder, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonObject = response.getJSONArray("Orders");

                    for (int i = 0; i < jsonObject.length(); i++) {

                        userOrder_itemsList.add(new UserOrder_Items(
                                jsonObject.getJSONObject(i).getString("id"),
                                jsonObject.getJSONObject(i).getString("field_name"),
                                jsonObject.getJSONObject(i).getString("notes"),
                                jsonObject.getJSONObject(i).getString("field_icon"),
                                jsonObject.getJSONObject(i).getString("Created_date")
                        ));

                    }

                    mUserOrder_adabter = new UserOrder_Adabter(userOrder_itemsList);
                    recyclerView.setAdapter(mUserOrder_adabter);
                    dialog.cancel();

                } catch (JSONException e) {

                    e.printStackTrace();
                    dialog.cancel();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error MyRequest :", error.toString());
                dialog.cancel();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);


    }
}
