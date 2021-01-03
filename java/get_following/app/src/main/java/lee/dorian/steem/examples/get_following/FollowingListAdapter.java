package lee.dorian.steem.examples.get_following;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import lee.dorian.steem.examples.get_following.databinding.LayoutFollowingItemBinding;
import lee.dorian.steem.examples.get_following.models.Following;

public class FollowingListAdapter extends RecyclerView.Adapter<FollowingListViewHolder> {

    @NonNull
    private List<Following> followingList = new LinkedList<>();

    public FollowingListAdapter() {
    }

    @NonNull
    @Override
    public FollowingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutFollowingItemBinding binding = LayoutFollowingItemBinding.inflate(
            LayoutInflater.from(parent.getContext()),
            parent,
            false
        );
        return new FollowingListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowingListViewHolder holder, int position) {
        holder.bind(followingList.get(position));
    }

    @Override
    public int getItemCount() {
        return followingList.size();
    }

    public void setFollowingList(@NonNull List<Following> followingList) {
        this.followingList = followingList;
        notifyDataSetChanged();
    }

    @NonNull
    public List<Following> getFollowingList() {
        return followingList;
    }
}
