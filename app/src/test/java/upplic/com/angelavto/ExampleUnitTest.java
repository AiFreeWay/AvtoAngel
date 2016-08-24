package upplic.com.angelavto;

import org.junit.Test;

import upplic.com.angelavto.domain.executors.CreateCar;
import upplic.com.angelavto.presentation.factories.FragmentsFactory;
import upplic.com.angelavto.presentation.view_controllers.AcMainCtrl;
import upplic.com.angelavto.presentation.views.fragments.BaseFragment;
import upplic.com.angelavto.presentation.views.fragments.CreateCarFragment;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        FragmentsFactory fragmentsFactory = new FragmentsFactory();
        CreateCarFragment fragment = (CreateCarFragment) fragmentsFactory.getFragment(FragmentsFactory.Fragments.CRAETE_CAR);
        System.out.println(fragment);
        assertEquals(fragment.getView(), null);
    }
}