package org.smartregister.chw.anc.adapter;

import android.content.Context;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;
import org.robolectric.RuntimeEnvironment;
import org.smartregister.chw.anc.BaseUnitTest;
import org.smartregister.chw.anc.contract.BaseAncHomeVisitContract;
import org.smartregister.chw.anc.model.BaseHomeVisitAction;
import org.smartregister.chw.opensrp_chw_anc.R;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class BaseAncHomeVisitAdapterTest extends BaseUnitTest {

    @Mock
    private BaseAncHomeVisitContract.View view;

    @Mock
    private LinkedHashMap<String, BaseHomeVisitAction> myDataset;

    private Context context = RuntimeEnvironment.application;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCircleColorComplete() throws Exception {
        BaseAncHomeVisitAdapter ancHomeVisitAdapter = new BaseAncHomeVisitAdapter(context, view, myDataset);
        BaseHomeVisitAction ancHomeVisitAction = Mockito.mock(BaseHomeVisitAction.class);
        Mockito.doReturn(true).when(ancHomeVisitAction).isEnabled();
        Mockito.doReturn(true).when(ancHomeVisitAction).isValid();
        Mockito.doReturn(BaseHomeVisitAction.Status.COMPLETED).when(ancHomeVisitAction).getActionStatus();

        int res = Whitebox.invokeMethod(ancHomeVisitAdapter, "getCircleColor", ancHomeVisitAction);
        assertEquals(res, R.color.alert_complete_green);
    }

    @Test
    public void testGetCircleColorPending() throws Exception {
        BaseAncHomeVisitAdapter ancHomeVisitAdapter = new BaseAncHomeVisitAdapter(context, view, myDataset);
        BaseHomeVisitAction ancHomeVisitAction = Mockito.mock(BaseHomeVisitAction.class);
        Mockito.doReturn(true).when(ancHomeVisitAction).isEnabled();
        Mockito.doReturn(true).when(ancHomeVisitAction).isValid();
        Mockito.doReturn(BaseHomeVisitAction.Status.PENDING).when(ancHomeVisitAction).getActionStatus();

        int res = Whitebox.invokeMethod(ancHomeVisitAdapter, "getCircleColor", ancHomeVisitAction);
        assertEquals(res, R.color.transparent_gray);
    }

    @Test
    public void testGetCircleColorPartial() throws Exception {
        BaseAncHomeVisitAdapter ancHomeVisitAdapter = new BaseAncHomeVisitAdapter(context, view, myDataset);
        BaseHomeVisitAction ancHomeVisitAction = Mockito.mock(BaseHomeVisitAction.class);
        Mockito.doReturn(true).when(ancHomeVisitAction).isEnabled();
        Mockito.doReturn(true).when(ancHomeVisitAction).isValid();
        Mockito.doReturn(BaseHomeVisitAction.Status.PARTIALLY_COMPLETED).when(ancHomeVisitAction).getActionStatus();

        int res = Whitebox.invokeMethod(ancHomeVisitAdapter, "getCircleColor", ancHomeVisitAction);
        assertEquals(res, R.color.pnc_circle_yellow);
    }

    @Test
    public void testBindClickListenerOnValidObject() throws Exception {
        BaseAncHomeVisitAdapter ancHomeVisitAdapter = new BaseAncHomeVisitAdapter(context, view, myDataset);
        View view = Mockito.mock(View.class);
        BaseHomeVisitAction ancHomeVisitAction = Mockito.mock(BaseHomeVisitAction.class);
        Mockito.doReturn(true).when(ancHomeVisitAction).isValid();
        Mockito.doReturn(true).when(ancHomeVisitAction).isEnabled();

        Whitebox.invokeMethod(ancHomeVisitAdapter, "bindClickListener", view, ancHomeVisitAction);

        Mockito.verify(view).setOnClickListener(any(View.OnClickListener.class));
    }

    @Test
    public void testGetItemCount() {
        BaseAncHomeVisitAdapter ancHomeVisitAdapter = new BaseAncHomeVisitAdapter(context, view, generateActions(10));

        assertEquals(10, ancHomeVisitAdapter.getItemCount());
    }

    private LinkedHashMap<String, BaseHomeVisitAction> generateActions(int count) {
        LinkedHashMap<String, BaseHomeVisitAction> map = new LinkedHashMap<>();
        int x = 0;
        while (count > x) {
            BaseHomeVisitAction action = Mockito.mock(BaseHomeVisitAction.class);
            Mockito.doReturn(true).when(action).isValid();
            map.put(String.valueOf(x), action);
            x++;
        }
        return map;
    }
}
