package lee.dorian.steem.examples.get_following;

import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import lee.dorian.steem.examples.get_following.models.Following;

public class DataBinding {

    @SuppressWarnings("unchecked")
    @BindingAdapter("items")
    public static void setItems(RecyclerView recyclerView, List<Following> followingList) {
        FollowingListAdapter adapter = (FollowingListAdapter) recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new FollowingListAdapter();
            recyclerView.setAdapter(adapter);
        }

        if (followingList != null) {
            adapter.setFollowingList(followingList);
        }
    }

    @BindingAdapter("src")
    public static void setSrc(ImageView imageView, String url) {
        Glide.with(imageView).load(Uri.parse(url)).into(imageView);
    }

}
