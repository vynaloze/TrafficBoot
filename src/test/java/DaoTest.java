import com.vynaloze.trafficboot.Main;
import com.vynaloze.trafficboot.dao.DAO;
import com.vynaloze.trafficboot.model.Stop;
import com.vynaloze.trafficboot.model.exception.StopNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class DaoTest extends DaoTestBase {
    @Autowired
    private DAO dao;

    @Before
    public void setUp() {
        dao.insertStop(stop1);
        dao.insertStop(stop2);
    }

    @Test
    public void shouldGetUniqueStopById() {
        //given
        final Stop givenStop = stop1;
        //when
        Stop obtainedStop = dao.getStopById(givenStop.getId());
        //then
        assertThat(obtainedStop).isEqualTo(givenStop);
    }

    @Test
    public void shouldGetStopsByPartOfAddress() {
        //given
        final String expectedAddress = "upa";
        //when
        List<Stop> obtainedStops = dao.getStopsByAddress(expectedAddress);
        //then
        assertThat(obtainedStops).hasSize(2).containsExactlyInAnyOrder(stop1, stop2);
    }

    @Test
    public void shouldDeleteStop() {
        //given
        final int stopToDelete = stop1.getId();
        //when
        dao.deleteStops(stopToDelete);
        //then
        assertThat(dao.getStopsByAddress("")).hasSize(1).containsOnly(stop2);
    }

    @Test
    public void shouldThrowNotFoundExceptions() {
        //given
        final int invalidId = 42;
        final String invalidName = "Ford";
        //then
        assertThatExceptionOfType(StopNotFoundException.class).isThrownBy(() -> dao.getStopById(invalidId));
        assertThatExceptionOfType(StopNotFoundException.class).isThrownBy(() -> dao.getStopsByAddress(invalidName));
    }

    @After
    public void tearDown() {
        tearDownTest();
    }
}
