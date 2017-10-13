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
import android.view.MotionEvent;
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

import mehani.wyanbu.com.mehani.FieldsListView.ListViewAdabter;
import mehani.wyanbu.com.mehani.FieldsListView.ListViewItems;
import mehani.wyanbu.com.mehani.GlobalVariables.NetWorks;

public class Careers_Fields extends AppCompatActivity {
    private List<ListViewItems> listViewItemsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListViewAdabter mlistViewAdabter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_careers__fields);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("المهن الفرعية");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_careers_fields);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        GetFieldForCareers();
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }

    private void GetFieldForCareers() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.loadingdialog, null);
        builder.setView(dialogView);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        dialog.show();


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, NetWorks.Careers_Fields + "1", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonObject = response.getJSONArray("AllFields");
                    for (int i = 0; i < jsonObject.length(); i++) {

                        listViewItemsList.add(new ListViewItems(
                                jsonObject.getJSONObject(i).getString("id"),
                                jsonObject.getJSONObject(i).getString("name"),
                                jsonObject.getJSONObject(i).getString("description"),
                                jsonObject.getJSONObject(i).getString("icon")
                        ));

                    }
                    mlistViewAdabter = new ListViewAdabter(listViewItemsList);
                    recyclerView.setAdapter(mlistViewAdabter);
                    dialog.cancel();

                } catch (JSONException e) {

                    e.printStackTrace();
                    dialog.cancel();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error Profile :", error.toString());
                dialog.cancel();

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);


    }
}
