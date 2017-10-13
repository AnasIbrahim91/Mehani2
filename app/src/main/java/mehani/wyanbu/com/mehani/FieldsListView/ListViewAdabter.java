package mehani.wyanbu.com.mehani.FieldsListView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mehani.wyanbu.com.mehani.GlobalVariables.NetWorks;
import mehani.wyanbu.com.mehani.R;
import mehani.wyanbu.com.mehani.User_Order;

/**
 * Created by Anas on 10/6/2017.
 */

public class ListViewAdabter extends RecyclerView.Adapter<ListViewAdabter.ViewHolder> {

    private List<ListViewItems> listViewItemsList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Fields_name, Fields_id, Fields_description;
        public ImageView Fields_iconURL;

        public ViewHolder(View view) {
            super(view);
            Fields_iconURL = view.findViewById(R.id.fieldscard_icon);
            Fields_name = view.findViewById(R.id.fieldscard_name);
            Fields_description = view.findViewById(R.id.fieldscard_description);
            Fields_id = view.findViewById(R.id.fieldscard_id);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog(view.getContext(), "1", "ماذا تريد ؟", "http://mehani.wyanbu.com//assets//site//images//map-marker-hi.png");
                }
            });
        }
    }


    @Override
    public ListViewAdabter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View FieldsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fieldscardview, parent, false);
        return new ViewHolder(FieldsView);
    }

    public ListViewAdabter(List<ListViewItems> listViewAdabterlist) {
        this.listViewItemsList = listViewAdabterlist;
    }

    @Override
    public void onBindViewHolder(ListViewAdabter.ViewHolder holder, int position) {
        ListViewItems ListViewItems = listViewItemsList.get(position);

        Picasso.with(holder.Fields_iconURL.getContext()).load(ListViewItems.getFields_iconURL()).into(holder.Fields_iconURL);
        holder.Fields_name.setText(ListViewItems.getFields_title());
        holder.Fields_description.setText(ListViewItems.getFields_description());
        holder.Fields_id.setText(ListViewItems.getFields_id());

    }

    @Override
    public int getItemCount() {
        return listViewItemsList.size();
    }

    public void showDialog(final Context context, final String ID, String Name, String Icon) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.dialog_addtorequest, null);
        builder.setView(dialogView);
        TextView dialogTitle = dialogView.findViewById(R.id.adddialog_title);
        ImageView dialogIcon = dialogView.findViewById(R.id.adddialog_icon);
        Picasso.with(context).load(Icon).into(dialogIcon);
        dialogTitle.setText(Name);
        final AlertDialog dialog = builder.create();

        Button bAdd = dialogView.findViewById(R.id.adddialog_bdone);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = dialogView.findViewById(R.id.adddialog_input);
                PostDataToDb(context, ID, input.getText().toString(), "");
            }
        });

        Button bCancel = dialogView.findViewById(R.id.adddialog_bcancel);
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();


    }

    private void PostDataToDb(final Context context, final String OrderId, final String UserInput, final String UserID) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.loadingdialog, null);
        builder.setView(dialogView);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        dialog.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, NetWorks.SetRequest,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String test = "";
                        try {
                            JSONObject json = new JSONObject(response);
                            test = json.getString("Success");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dialog.cancel();
                        }

                        if (test.contains("تم حفظ")) {
                            Intent intent = new Intent(context, User_Order.class);
                            context.startActivity(intent);
                            dialog.cancel();


                        } else {
                            Toast.makeText(context, "ادخال خاطىء", Toast.LENGTH_SHORT).show();
                            dialog.cancel();

                        }


                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.e("Error Profile :", error.toString());
                        dialog.cancel();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("field_id", OrderId);
                params.put("notes", UserInput);
                params.put("app_user_id", UserID);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(postRequest);


    }
}
