package pya.marlon.com.rxjavaproject.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pya.marlon.com.rxjavaproject.R;
import pya.marlon.com.rxjavaproject.domain.model.UserBean;

/**
 * Created by marlonpya on 22/05/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private ArrayList<UserBean> listUser;

    public UserAdapter(Context context, ArrayList<UserBean> listUser) {
        this.listUser = listUser;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final UserBean user = listUser.get(position);
        holder.txtName.setText(user.name);
        holder.txtUrl.setText(user.htmlUrl);
        holder.txtDescription.setText(user.description);
        holder.txtLanguage.setText(user.language);
        holder.txtCount.setText(user.stargazersCount + "");
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtName) TextView txtName;
        @BindView(R.id.txtUrl) TextView txtUrl;
        @BindView(R.id.txtDescription) TextView txtDescription;
        @BindView(R.id.txtlanguage) TextView txtLanguage;
        @BindView(R.id.txtCount) TextView txtCount;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
