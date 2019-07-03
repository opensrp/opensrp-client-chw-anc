package org.smartregister.chw.anc.interactor;

import android.support.annotation.VisibleForTesting;

import org.ei.drishti.dto.AlertStatus;
import org.smartregister.chw.anc.contract.BaseAncMemberProfileContract;
import org.smartregister.chw.anc.domain.MemberObject;
import org.smartregister.chw.anc.util.AppExecutors;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class BaseAncMemberProfileInteractor implements BaseAncMemberProfileContract.Interactor {
    public static final String TAG = BaseAncMemberProfileInteractor.class.getName();
    protected AppExecutors appExecutors;
    private Map<String, Date> vaccineList = new LinkedHashMap<>();

    @VisibleForTesting
    BaseAncMemberProfileInteractor(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    public BaseAncMemberProfileInteractor() {
        this(new AppExecutors());
    }

    public Map<String, Date> getVaccineList() {
        return vaccineList;
    }

    @Override
    public void refreshProfileView(final MemberObject memberObject, final boolean isForEdit, final BaseAncMemberProfileContract.InteractorCallBack callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.refreshProfileTopSection(memberObject);
                    }
                });
            }
        };
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void refreshProfileInfo(MemberObject memberObject, final BaseAncMemberProfileContract.InteractorCallBack callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.refreshFamilyStatus(AlertStatus.normal);
                        callback.refreshLastVisit(new Date());
                        callback.refreshUpComingServicesStatus("ANC Visit", AlertStatus.normal, new Date());
                    }
                });
            }
        };
        appExecutors.diskIO().execute(runnable);
    }


    @Override
    public void updateVisitNotDone(long value, BaseAncMemberProfileContract.InteractorCallBack callback) {

//       Implement
    }


}