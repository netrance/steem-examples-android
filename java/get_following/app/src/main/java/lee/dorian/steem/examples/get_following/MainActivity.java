package lee.dorian.steem.examples.get_following;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lee.dorian.steem.examples.get_following.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private MainViewModel mainViewModel;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setViewModel(mainViewModel);
        binding.rvFollowingList.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);

        // Loads following list after typing steemit account
        RxTextView.textChangeEvents(binding.etAccount)
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .filter(account -> !account.text().toString().isEmpty())
            .subscribeOn(Schedulers.io())
            //.observeOn(AndroidSchedulers.mainThread())    // No need because observer doesn't update UI directly
            .subscribe(event -> {
                mainViewModel.loadFollowingList(binding.etAccount.getText().toString(), 100);
            });

        binding.rvFollowingList.addOnScrollListener(followingListScrollListener);

        mainViewModel.observableProgress
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onNextProgress);
    }

    // To load more followings when the following list scrolls to bottom
    // Reference: https://stackoverflow.com/questions/36127734/detect-when-recyclerview-reaches-the-bottom-most-position-while-scrolling
    private RecyclerView.OnScrollListener followingListScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (!recyclerView.canScrollVertically(1) && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                mainViewModel.loadMoreFollowings(binding.etAccount.getText().toString(), 100);
            }
        }
    };

    // To determine if progress dialog is to be opened or closed
    private Consumer<? super Boolean> onNextProgress = (isProgressing) -> {
        if (isProgressing) {
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Loading ...");
            progressDialog.show();
        }
        else {
            if (null != progressDialog) {
                progressDialog.dismiss();
            }
        }
    };

}