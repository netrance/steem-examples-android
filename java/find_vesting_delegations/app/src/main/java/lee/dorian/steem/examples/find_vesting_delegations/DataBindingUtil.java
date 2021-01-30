package lee.dorian.steem.examples.find_vesting_delegations;

import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import lee.dorian.steem.examples.find_vesting_delegations.models.VestingDelegation;

public class DataBindingUtil {

    //@SuppressWarnings("unchecked")
    @BindingAdapter("items")
    public static void setItems(RecyclerView recyclerView, List<VestingDelegation> vestingDelegationList) {
        VestingDelegationListAdapter adapter = (VestingDelegationListAdapter) recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new VestingDelegationListAdapter();
            recyclerView.setAdapter(adapter);
        }

        if (vestingDelegationList != null) {
            adapter.setVestingDelegationList(vestingDelegationList);
        }
    }

    @BindingAdapter("src")
    public static void setSrc(ImageView imageView, String url) {
        Glide.with(imageView).load(Uri.parse(url)).into(imageView);
    }

}
