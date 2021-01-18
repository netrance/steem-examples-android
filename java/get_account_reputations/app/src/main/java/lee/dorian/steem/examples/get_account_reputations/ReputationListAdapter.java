package lee.dorian.steem.examples.get_account_reputations;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import lee.dorian.steem.examples.get_account_reputations.databinding.LayoutReputationItemBinding;
import lee.dorian.steem.examples.get_account_reputations.models.Reputation;

public class ReputationListAdapter extends RecyclerView.Adapter<ReputationListViewHolder> {

    @NonNull
    private List<Reputation> reputationList = new LinkedList<>();

    public ReputationListAdapter() {
    }

    @NonNull
    @Override
    public ReputationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutReputationItemBinding binding = LayoutReputationItemBinding.inflate(
            LayoutInflater.from(parent.getContext()),
            parent,
            false
        );
        return new ReputationListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReputationListViewHolder holder, int position) {
        holder.bind(reputationList.get(position));
    }

    @Override
    public int getItemCount() {
        return reputationList.size();
    }

    public void setReputationList(@NonNull List<Reputation> reputationList) {
        this.reputationList = reputationList;
        notifyDataSetChanged();
    }

    @NonNull
    public List<Reputation> getReputationList() {
        return reputationList;
    }
}
