package org.smartregister.chw.anc.actionhelper;

import android.content.Context;

import org.smartregister.chw.anc.domain.VisitDetail;
import org.smartregister.chw.anc.model.BaseHomeVisitAction;

import java.util.List;
import java.util.Map;

import timber.log.Timber;

/**
 * Designed to set default methods for the ANCActionHelper
 * This object must remain inert to the Home Visit action. Its designed primarily for extension by simple visit actions
 */
public abstract class HomeVisitActionHelper implements BaseHomeVisitAction.HomeVisitActionHelper {
    protected Context context;

    @Override
    public void onJsonFormLoaded(String jsonString, Context context, Map<String, List<VisitDetail>> details) {
        this.context = context;
    }

    /**
     * set preprocessed status to be inert
     *
     * @return null
     */
    @Override
    public String getPreProcessed() {
        return null;
    }

    /**
     * set schedule status to be inert
     *
     * @return null
     */
    @Override
    public BaseHomeVisitAction.ScheduleStatus getPreProcessedStatus() {
        return BaseHomeVisitAction.ScheduleStatus.DUE;
    }

    /**
     * set schedule status to be inert
     *
     * @return null
     */
    @Override
    public String getPreProcessedSubTitle() {
        return null;
    }

    /**
     * prevent post processing
     *
     * @return null
     */
    @Override
    public String postProcess(String jsonPayload) {
        return null;
    }

    /**
     * Do nothing on payload received
     *
     * @param baseHomeVisitAction
     */
    @Override
    public void onPayloadReceived(BaseHomeVisitAction baseHomeVisitAction) {
        Timber.v("onPayloadReceived");
    }

    public Context getContext() {
        return context;
    }
}
