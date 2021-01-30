package lee.dorian.steem.examples.find_vesting_delegations;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import lee.dorian.steem.examples.find_vesting_delegations.databinding.LayoutVestingDelegationItemBinding;
import lee.dorian.steem.examples.find_vesting_delegations.models.VestingDelegation;

public class VestingDelegationListAdapter extends RecyclerView.Adapter<VestingDelegationListViewHolder> {

    @NonNull
    private List<VestingDelegation> vestingDelegationList = new LinkedList<>();

    public VestingDelegationListAdapter() {
    }

    @NonNull
    @Override
    public VestingDelegationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutVestingDelegationItemBinding binding = LayoutVestingDelegationItemBinding.inflate(
            LayoutInflater.from(parent.getContext()),
            parent,
            false
        );
        return new VestingDelegationListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VestingDelegationListViewHolder holder, int position) {
        holder.bind(vestingDelegationList.get(position), (position % 2) == 0);
    }

    @Override
    public int getItemCount() {
        return vestingDelegationList.size();
    }

    public void setVestingDelegationList(@NonNull List<VestingDelegation> vestingDelegationList) {
        this.vestingDelegationList = vestingDelegationList;
        notifyDataSetChanged();
    }

    @NonNull
    public List<VestingDelegation> getVestingDelegationList() {
        return vestingDelegationList;
    }
}
