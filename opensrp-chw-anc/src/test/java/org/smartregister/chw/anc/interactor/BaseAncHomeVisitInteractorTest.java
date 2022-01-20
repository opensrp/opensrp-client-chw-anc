package org.smartregister.chw.anc.interactor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.smartregister.chw.anc.AncLibrary;
import org.smartregister.chw.anc.contract.BaseAncHomeVisitContract;
import org.smartregister.chw.anc.domain.MemberObject;
import org.smartregister.chw.anc.model.BaseAncHomeVisitAction;
import org.smartregister.chw.anc.model.BaseHomeVisitAction;
import org.smartregister.chw.anc.util.AppExecutors;
import org.smartregister.sync.helper.ECSyncHelper;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;

@RunWith(MockitoJUnitRunner.class)
public class BaseAncHomeVisitInteractorTest implements Executor {

    private BaseAncHomeVisitInteractor interactor;

    @Mock
    private BaseAncHomeVisitContract.InteractorCallBack interactorCallBack;

    @Mock
    private AncLibrary ancLibrary;

    @Mock
    private ECSyncHelper syncHelper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        AppExecutors appExecutors = new AppExecutors(this, this, this);
        interactor = Mockito.spy(new BaseAncHomeVisitInteractor(appExecutors, ancLibrary, syncHelper));
    }

    @Override
    public void execute(Runnable command) {
        command.run();
    }

    @Test
    public void testReloadMemberDetailsNotifiesPresenter() {
        interactor.reloadMemberDetails("12334", interactorCallBack);
        Mockito.verify(interactorCallBack, Mockito.never()).onMemberDetailsReloaded(Mockito.any());
    }

    @Test
    public void testGetMemberClient() {
        MemberObject memberObject = interactor.getMemberClient("12345");
        Assert.assertNull(memberObject);
    }

    @Test
    public void testSubmitVisitSavesVisits() {
        // final boolean editMode, final String memberID, final Map<String, BaseAncHomeVisitAction> map, final BaseAncHomeVisitContract.InteractorCallBack callBack
        Map<String, BaseAncHomeVisitAction> map = new LinkedHashMap<>();

        interactor.submitVisit(false, "12345", map, interactorCallBack);

        Mockito.verify(interactorCallBack).onSubmitted(Mockito.anyBoolean());
    }
}
