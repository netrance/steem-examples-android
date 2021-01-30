package lee.dorian.steem.examples.find_vesting_delegations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import lee.dorian.steem.examples.find_vesting_delegations.databinding.ActivityMainBinding;

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
        binding.rvVestingDelegationList.setLayoutManager(new LinearLayoutManager(this));

        // Loads vesting delegation list after typing steemit account
        RxTextView.textChangeEvents(binding.etAccount)
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .filter(account -> account.text().toString().length() > 2)
            .subscribeOn(Schedulers.io())
            //.observeOn(AndroidSchedulers.mainThread())    // No need because observer doesn't update UI directly
            .subscribe(event -> {
                mainViewModel.loadVestingDelegationList(binding.etAccount.getText().toString());
            });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainViewModel.loadDynamicGlobalProperties();
    }
}