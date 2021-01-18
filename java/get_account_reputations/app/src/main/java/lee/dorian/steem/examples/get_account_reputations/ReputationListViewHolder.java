package lee.dorian.steem.examples.get_account_reputations;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import lee.dorian.steem.examples.get_account_reputations.databinding.LayoutReputationItemBinding;
import lee.dorian.steem.examples.get_account_reputations.models.Reputation;

public class ReputationListViewHolder extends RecyclerView.ViewHolder {

    private LayoutReputationItemBinding binding;

    public ReputationListViewHolder(@NonNull LayoutReputationItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Reputation following) {
        binding.setVariable(BR.reputation, following);
    }
}
