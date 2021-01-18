package lee.dorian.steem.examples.get_account_reputations;

import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import lee.dorian.steem.examples.get_account_reputations.models.Reputation;

public class DataBindingUtil {

    //@SuppressWarnings("unchecked")
    @BindingAdapter("items")
    public static void setItems(RecyclerView recyclerView, List<Reputation> followingList) {
        ReputationListAdapter adapter = (ReputationListAdapter) recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new ReputationListAdapter();
            recyclerView.setAdapter(adapter);
        }

        if (followingList != null) {
            adapter.setReputationList(followingList);
        }
    }

    @BindingAdapter("src")
    public static void setSrc(ImageView imageView, String url) {
        Glide.with(imageView).load(Uri.parse(url)).into(imageView);
    }

}
