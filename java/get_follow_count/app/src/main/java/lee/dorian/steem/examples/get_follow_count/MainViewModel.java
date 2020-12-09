package lee.dorian.steem.examples.get_follow_count;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lee.dorian.steem.examples.get_follow_count.models.FollowCount;
import lee.dorian.steem.examples.get_follow_count.models.FollowCountResponse;

public class MainViewModel extends ViewModel {

    private static String TAG = "MainViewModel";

    private SteemClient steemClient = new SteemClient();
    public MutableLiveData<FollowCount> liveFollowCount = new MutableLiveData<FollowCount>();

    public MainViewModel() {
    }

    public void loadFollowCount(String account) {
        steemClient.getFollowCount(account)
            .subscribeOn(Schedulers.io())
            .subscribe(
                (followCountResponse) -> {
                    FollowCount followCount = new FollowCount(followCountResponse);
                    liveFollowCount.postValue(followCount);
                },
                (error) -> {
                    Log.d(TAG, "Error: " + error.getMessage());
                },
                () -> {
                    Log.i(TAG, String.format("getFollowFount(%s) completed", account));
                }
            );
    }

}
