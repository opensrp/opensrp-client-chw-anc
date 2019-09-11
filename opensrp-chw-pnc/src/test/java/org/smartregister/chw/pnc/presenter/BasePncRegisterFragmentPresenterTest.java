package org.smartregister.chw.pnc.presenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.smartregister.chw.anc.contract.BaseAncRegisterFragmentContract;
import org.smartregister.chw.anc.util.Constants;
import org.smartregister.chw.anc.util.DBConstants;
import org.smartregister.chw.pnc.BaseUnitTest;

public class BasePncRegisterFragmentPresenterTest extends BaseUnitTest {

    @Mock
    private BaseAncRegisterFragmentContract.Model model;

    @Mock
    private BaseAncRegisterFragmentContract.View view;

    private BasePncRegisterFragmentPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        BasePncRegisterFragmentPresenter presenterObj = new BasePncRegisterFragmentPresenter(view, model, "viewConfigurationIdentifier");
        presenter = Mockito.spy(presenterObj);
    }

    @Test
    public void getMainConditionRetrnsCorrectCondition() {
        String expectedCondition = "";
        Mockito.when(presenter.getMainCondition()).thenReturn("");
        Assert.assertEquals(expectedCondition, presenter.getMainCondition());
        Mockito.verify(presenter, Mockito.times(1)).getMainCondition();
    }

    @Test
    public void getDefaultSortQueryReturnsCorrectQuery() {
        String expectedSortQuery = Constants.TABLES.PREGNANCY_OUTCOME + "." + DBConstants.KEY.LAST_INTERACTED_WITH + " DESC ";
        Mockito.when(presenter.getDefaultSortQuery()).thenReturn(Constants.TABLES.PREGNANCY_OUTCOME + "." + DBConstants.KEY.LAST_INTERACTED_WITH + " DESC ");
        Assert.assertEquals(expectedSortQuery, presenter.getDefaultSortQuery());
        Mockito.verify(presenter, Mockito.times(1)).getDefaultSortQuery();
    }

    @Test
    public void getMainTableReturnsCorrectTable() {
        String expectedTable = Constants.TABLES.PREGNANCY_OUTCOME;
        Mockito.when(presenter.getMainTable()).thenReturn(Constants.TABLES.PREGNANCY_OUTCOME);
        Assert.assertEquals(expectedTable, presenter.getMainTable());
        Mockito.verify(presenter, Mockito.times(1)).getMainTable();
    }

}
