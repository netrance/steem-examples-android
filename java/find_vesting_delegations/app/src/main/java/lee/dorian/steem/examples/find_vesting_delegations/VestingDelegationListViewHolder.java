package lee.dorian.steem.examples.find_vesting_delegations;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import lee.dorian.steem.examples.find_vesting_delegations.databinding.LayoutVestingDelegationItemBinding;
import lee.dorian.steem.examples.find_vesting_delegations.models.VestingDelegation;

public class VestingDelegationListViewHolder extends RecyclerView.ViewHolder {

    private LayoutVestingDelegationItemBinding binding;

    public VestingDelegationListViewHolder(@NonNull LayoutVestingDelegationItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(VestingDelegation vestingDelegation, boolean isEven) {
        binding.setVariable(BR.vestingDelegation, vestingDelegation);
        binding.setVariable(BR.isEven, isEven);
    }
}
