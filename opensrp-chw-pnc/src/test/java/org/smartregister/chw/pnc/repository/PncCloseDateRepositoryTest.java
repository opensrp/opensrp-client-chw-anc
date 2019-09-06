package org.smartregister.chw.pnc.repository;

import android.content.ContentValues;

import net.sqlcipher.database.SQLiteDatabase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.smartregister.chw.pnc.BaseUnitTest;
import org.smartregister.repository.Repository;

public class PncCloseDateRepositoryTest extends BaseUnitTest {

    @Mock
    private Repository repository;

    @Mock
    private SQLiteDatabase sqLiteDatabase;

    private PncCloseDateRepository pncCloseDateRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        pncCloseDateRepository = Mockito.spy(new PncCloseDateRepository(repository));
        Mockito.doReturn(sqLiteDatabase).when(pncCloseDateRepository).getWritableDatabase();
        Mockito.doReturn(sqLiteDatabase).when(pncCloseDateRepository).getReadableDatabase();
    }

    @Test
    public void closeOldPNCRecordsUpdatesPregnancyOutcome() {
        pncCloseDateRepository.closeOldPNCRecords(1);
        Mockito.verify(sqLiteDatabase).update(Mockito.anyString(), Mockito.any(ContentValues.class), Mockito.anyString(), Mockito.any(String[].class));
    }
}
