package lee.dorian.steem.examples.find_vesting_delegations;

import android.util.Log;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import lee.dorian.steem.examples.find_vesting_delegations.api.SteemClient;
import lee.dorian.steem.examples.find_vesting_delegations.models.DynamicGlobalProperties;
import lee.dorian.steem.examples.find_vesting_delegations.models.VestingDelegation;
import lee.dorian.steem.examples.find_vesting_delegations.models.Error;

public class MainViewModel extends ViewModel {
    private static String TAG = "MainViewModel";

    private SteemClient steemClient = new SteemClient();

    public MutableLiveData<DynamicGlobalProperties> liveDynamicGlobalProperties = new MutableLiveData<>();
    public MutableLiveData<List<VestingDelegation>> liveVestingDelegationList = new MutableLiveData<>();
    public MutableLiveData<Error> liveError = new MutableLiveData<>();

    public MutableLiveData<Boolean> liveProgress = new MutableLiveData<>();

    public MainViewModel() {
        liveProgress.postValue(false);
    }

    public void loadDynamicGlobalProperties() {
        steemClient.getDynamicGlogalProperties().retry(SteemClient.RETRY_COUNT).subscribeOn(Schedulers.io()).subscribe(
            (dynamicGlobalPropertiesResponse) -> {
                DynamicGlobalProperties.shared = dynamicGlobalPropertiesResponse.getResult();
                liveDynamicGlobalProperties.postValue(DynamicGlobalProperties.shared);
            },
            (error) -> {
                Log.d(TAG, "Error: " + error.getMessage());
            },
            () -> {
                Log.i(TAG, "getDynamicGlogalProperties() completed");
            }
        );
    }

    public void loadVestingDelegationList(String account) {
        liveProgress.postValue(true);
        steemClient.getVestingDelegationList(account)
            .retry(SteemClient.RETRY_COUNT)
            .doFinally(() -> {
                liveProgress.postValue(false);
            })
            .subscribeOn(Schedulers.io())
            .subscribe(
                (vestingDelegationResponse) -> {
                    List<VestingDelegation> vestingDelegationList = VestingDelegation.getVestingDelegationList(vestingDelegationResponse);
                    liveVestingDelegationList.postValue(vestingDelegationList);
                    liveError.postValue(vestingDelegationResponse.getError());
                },
                (error) -> {
                    Log.d(TAG, "Error: " + error.getMessage());
                    List<VestingDelegation> emptyVestingDelegationList = new LinkedList<>();
                    liveVestingDelegationList.postValue(emptyVestingDelegationList);
                },
                () -> {
                    Log.i(TAG, String.format("getVestingDelegationList(%s) completed", account));
                }
            );
    }

}
