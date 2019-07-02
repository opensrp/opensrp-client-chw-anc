package org.smartregister.chw.anc.contract;

import android.content.Context;

import com.vijay.jsonwizard.domain.Form;

import org.json.JSONObject;
import org.smartregister.chw.anc.domain.MemberObject;
import org.smartregister.chw.anc.model.BaseAncHomeVisitAction;

import java.util.LinkedHashMap;
import java.util.Map;

public interface BaseAncHomeVisitContract {

    interface View extends VisitView {

        BaseAncHomeVisitContract.Presenter presenter();

        Form getFormConfig();

        void startForm(BaseAncHomeVisitAction ancHomeVisitAction);

        void startFormActivity(JSONObject jsonForm);

        void startFragment(BaseAncHomeVisitAction ancHomeVisitAction);

        void redrawHeader(MemberObject memberObject);

        void redrawVisitUI();

        void displayProgressBar(boolean state);

        Map<String, BaseAncHomeVisitAction> getAncHomeVisitActions();

        void close();

        Presenter getPresenter();

        /**
         * Save the received data into the events table
         * Start aggregation of all events and persist results into the events table
         */
        void submitVisit();

        void initializeActions(LinkedHashMap<String, BaseAncHomeVisitAction> map);

        Context getContext();

        void displayToast(String message);

        Boolean getEditMode();
    }

    interface VisitView {

        /**
         * Results action when a dialog is opened and returns a payload
         *
         * @param jsonString
         */
        void onDialogOptionUpdated(String jsonString);

    }

    interface Presenter {

        void startForm(String formName, String memberID, String currentLocationId);

        /**
         * Recall this method to redraw ui after every submission
         *
         * @return
         */
        boolean validateStatus();

        /**
         * Preload header and visit
         */
        void initialize();

        void submitVisit();
    }

    interface Model {

        JSONObject getFormAsJson(String formName, String entityId, String currentLocationId) throws Exception;

    }

    interface Interactor {

        void saveRegistration(String jsonString, boolean isEditMode, final BaseAncHomeVisitContract.InteractorCallBack callBack);

        void calculateActions(View view, MemberObject memberObject, BaseAncHomeVisitContract.InteractorCallBack callBack);

        void submitVisit(String memberID, Map<String, BaseAncHomeVisitAction> map, InteractorCallBack callBack);
    }

    interface InteractorCallBack {

        void onRegistrationSaved(boolean isEdit);

        void preloadActions(LinkedHashMap<String, BaseAncHomeVisitAction> map);

        void onSubmitted(boolean successful);
    }
}
