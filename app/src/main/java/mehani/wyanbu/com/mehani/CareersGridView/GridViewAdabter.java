package mehani.wyanbu.com.mehani.CareersGridView;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import mehani.wyanbu.com.mehani.Careers_Fields;
import mehani.wyanbu.com.mehani.R;


/**
 * Created by Anas on 10/6/2017.
 */

public class GridViewAdabter extends RecyclerView.Adapter<GridViewAdabter.ViewHolder> {
    private List<GridViewItems> GridViewItemslist;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Careers_name, Careers_id;
        public ImageView Careers_iconURL;

        public ViewHolder(View view) {
            super(view);
            Careers_iconURL = view.findViewById(R.id.careersgridview_icon);
            Careers_name = view.findViewById(R.id.careersgridview_name);
            Careers_id = view.findViewById(R.id.careersgridview_id);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Careers_Fields.class);
                    intent.putExtra("id", Careers_id.getText().toString());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View CareersView = LayoutInflater.from(parent.getContext()).inflate(R.layout.careerscardview, parent, false);
        return new ViewHolder(CareersView);
    }

    public GridViewAdabter(List<GridViewItems> GridViewItemslist) {
        this.GridViewItemslist = GridViewItemslist;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        GridViewItems GridViewItems = GridViewItemslist.get(position);

        Picasso.with(holder.Careers_iconURL.getContext()).load(GridViewItems.getCareers_iconURL()).into(holder.Careers_iconURL);
        holder.Careers_name.setText(GridViewItems.getCareers_name());
        holder.Careers_id.setText(GridViewItems.getCareers_id());
    }

    @Override
    public int getItemCount() {
        return GridViewItemslist.size();
    }

}
