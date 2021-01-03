package lee.dorian.steem.examples.get_following;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import lee.dorian.steem.examples.get_following.api.SteemClient;
import lee.dorian.steem.examples.get_following.models.Error;
import lee.dorian.steem.examples.get_following.models.Following;
import lee.dorian.steem.examples.get_following.models.FollowingParams;

public class MainViewModel extends ViewModel {
    private static String TAG = "MainViewModel";

    private SteemClient steemClient = new SteemClient();
    public MutableLiveData<List<Following>> liveFollowingList = new MutableLiveData<List<Following>>();
    public MutableLiveData<Error> liveError = new MutableLiveData<>();

    public PublishSubject<Boolean> observableProgress = PublishSubject.create();

    public MainViewModel() {}

    public void loadFollowingList(String account, int size) {
        observableProgress.onNext(true);
        steemClient.getFollowing(account, "", FollowingParams.TYPE_BLOG, size)
            .doOnSubscribe((b) -> {
                observableProgress.onNext(false);
            })
            .subscribeOn(Schedulers.io())
            .subscribe(
                (followCountResponse) -> {
                    List<Following> followList = Following.getFollowingList(followCountResponse);
                    liveFollowingList.postValue(followList);
                    liveError.postValue(followCountResponse.getError());
                },
                (error) -> {
                    Log.d(TAG, "Error: " + error.getMessage());
                    List<Following> emptyFollowlist = new LinkedList<>();
                    liveFollowingList.postValue(emptyFollowlist);
                },
                () -> {
                    Log.i(TAG, String.format("getFollowing(%s) completed", account));
                }
            );
    }

    public void loadMoreFollowings(String account, int size) {
        int sizeOfFollowingList = liveFollowingList.getValue().size();
        String startAccount = liveFollowingList.getValue().get(sizeOfFollowingList - 1).getFollowing();
        steemClient.getFollowing(account, startAccount, FollowingParams.TYPE_BLOG, size + 1)
            .subscribeOn(Schedulers.io())
            .subscribe(
                (followCountResponse) -> {
                    List<Following> followingList = Following.getFollowingList(followCountResponse);
                    if (followingList.size() <= 1) {
                        return;
                    }
                    if (followingList.get(0).getFollowing().equals(startAccount)) {
                        followingList.remove(0);
                    }

                    List<Following> existingFollowingList = liveFollowingList.getValue();
                    existingFollowingList.addAll(followingList);
                    liveFollowingList.postValue(existingFollowingList);
                },
                (error) -> {
                    Log.d(TAG, "Error: " + error.getMessage());
                },
                () -> {
                    Log.i(TAG, String.format("getFollowing(%s) completed", account));
                }
            );
    }
}
