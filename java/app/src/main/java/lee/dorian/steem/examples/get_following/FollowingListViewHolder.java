package lee.dorian.steem.examples.get_following;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import lee.dorian.steem.examples.get_following.databinding.LayoutFollowingItemBinding;
import lee.dorian.steem.examples.get_following.models.Following;

public class FollowingListViewHolder extends RecyclerView.ViewHolder {

    private LayoutFollowingItemBinding binding;

    public FollowingListViewHolder(@NonNull LayoutFollowingItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Following following) {
        binding.setVariable(BR.following, following);
    }
}
