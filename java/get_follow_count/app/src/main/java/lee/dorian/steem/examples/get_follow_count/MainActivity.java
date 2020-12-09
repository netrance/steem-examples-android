package lee.dorian.steem.examples.get_follow_count;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lee.dorian.steem.examples.get_follow_count.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setViewModel(mainViewModel);

        RxTextView.textChangeEvents(binding.etAccount)
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .filter(account -> !account.text().toString().isEmpty())
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            //.observeOn(AndroidSchedulers.mainThread())    // No need because observer doesn't update UI directly
            .subscribe(event -> {
                mainViewModel.loadFollowCount(binding.etAccount.getText().toString());
            });

    }


}