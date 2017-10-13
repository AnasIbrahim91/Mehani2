package mehani.wyanbu.com.mehani.UserOrder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import mehani.wyanbu.com.mehani.R;

/**
 * Created by Anas on 10/6/2017.
 */

public class UserOrder_Adabter extends RecyclerView.Adapter<UserOrder_Adabter.ViewHolder> {
    private List<UserOrder_Items> userOrder_itemsList;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView UserOrder_name, UserOrder_id, UserOrder_description, UserOrder_data;
        public ImageView UserOrder_iconURL, UserOrder_edit, UserOrder_delet;

        public ViewHolder(View view) {
            super(view);

            UserOrder_iconURL = view.findViewById(R.id.user_odrer_icon);
            UserOrder_name = view.findViewById(R.id.user_odrer_name);
            UserOrder_description = view.findViewById(R.id.user_odrer_description);
            UserOrder_id = view.findViewById(R.id.user_order_id);
            UserOrder_data = view.findViewById(R.id.user_odrer_Data);

            UserOrder_edit = view.findViewById(R.id.user_order_edit);
            UserOrder_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Edit Not Work For This Time", Toast.LENGTH_SHORT).show();

                }
            });
            UserOrder_delet = view.findViewById(R.id.user_order_delete);
            UserOrder_delet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Delete Not Work For This Time", Toast.LENGTH_SHORT).show();


                }
            });

        }
    }


    @Override
    public UserOrder_Adabter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View FieldsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.userordercardview, parent, false);
        return new ViewHolder(FieldsView);
    }

    public UserOrder_Adabter(List<UserOrder_Items> userOrder_itemsList1) {
        this.userOrder_itemsList = userOrder_itemsList1;
    }

    @Override
    public void onBindViewHolder(UserOrder_Adabter.ViewHolder holder, int position) {
        UserOrder_Items userOrder_items = userOrder_itemsList.get(position);

        Picasso.with(holder.UserOrder_iconURL.getContext()).load(userOrder_items.getUserOrder_iconURL()).into(holder.UserOrder_iconURL);
        holder.UserOrder_name.setText(userOrder_items.getUserOrder_title());
        holder.UserOrder_description.setText(userOrder_items.getUserOrder_description());
        holder.UserOrder_id.setText(userOrder_items.getUserOrder_id());
        holder.UserOrder_data.setText(userOrder_items.getUserOrder_CreatData());

    }

    @Override
    public int getItemCount() {
        return userOrder_itemsList.size();
    }
}
