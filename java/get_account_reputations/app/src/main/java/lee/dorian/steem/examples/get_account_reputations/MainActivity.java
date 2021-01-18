package lee.dorian.steem.examples.get_account_reputations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lee.dorian.steem.examples.get_account_reputations.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setViewModel(mainViewModel);
        binding.rvReputationList.setLayoutManager(new LinearLayoutManager(this));

        // Loads reputation list after typing steemit account
        RxTextView.textChangeEvents(binding.etAccount)
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .filter(account -> !account.text().toString().isEmpty())
            .subscribeOn(Schedulers.io())
            //.observeOn(AndroidSchedulers.mainThread())    // No need because observer doesn't update UI directly
            .subscribe(event -> {
                mainViewModel.loadReputationList(binding.etAccount.getText().toString(), 100);
            });

        mainViewModel.observableProgress
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onNextProgress);
    }

    // To determine whether to show loading progress
    private Consumer<? super Boolean> onNextProgress = (isProgressing) -> {
        if (isProgressing) {
            binding.layoutLoading.setVisibility(View.VISIBLE);
        }
        else {
            binding.layoutLoading.setVisibility(View.GONE);
        }
    };

}