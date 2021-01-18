package lee.dorian.steem.examples.get_account_reputations;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import lee.dorian.steem.examples.get_account_reputations.api.SteemClient;
import lee.dorian.steem.examples.get_account_reputations.models.Reputation;
import lee.dorian.steem.examples.get_account_reputations.models.Error;

public class MainViewModel extends ViewModel {
    private static String TAG = "MainViewModel";

    private SteemClient steemClient = new SteemClient();
    public MutableLiveData<List<Reputation>> liveReputationList = new MutableLiveData<List<Reputation>>();
    public MutableLiveData<Error> liveError = new MutableLiveData<>();

    public PublishSubject<Boolean> observableProgress = PublishSubject.create();

    public MainViewModel() {}

    public void loadReputationList(String account, int size) {
        observableProgress.onNext(true);
        steemClient.getReputationList(account, size)
            .doFinally(() -> {
                observableProgress.onNext(false);
            })
            .subscribeOn(Schedulers.io())
            .subscribe(
                (reputationResponse) -> {
                    List<Reputation> followList = Reputation.getReputationList(reputationResponse);
                    liveReputationList.postValue(followList);
                    liveError.postValue(reputationResponse.getError());
                },
                (error) -> {
                    Log.d(TAG, "Error: " + error.getMessage());
                    List<Reputation> emptyReputationList = new LinkedList<>();
                    liveReputationList.postValue(emptyReputationList);
                },
                () -> {
                    Log.i(TAG, String.format("getReputationList(%s, %d) completed", account, size));
                }
            );
    }

}
