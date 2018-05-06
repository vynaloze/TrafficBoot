import com.vynaloze.trafficboot.Main;
import com.vynaloze.trafficboot.dao.DAO;
import com.vynaloze.trafficboot.dao.orm.exception.EntityNotFoundException;
import com.vynaloze.trafficboot.model.Stop;
import com.vynaloze.trafficboot.util.CustomQueryProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class DaoTest extends DaoTestBase {
    @Autowired
    private DAO<Stop> dao;
    @Autowired
    private CustomQueryProvider queryProvider;

    @Before
    public void setUp() {
        dao.create(stop1);
        dao.create(stop2);
    }

    @Test
    public void shouldGetUniqueStopById() {
        //given
        final Stop givenStop = stop1;
        //when
        Stop obtainedStop = dao.find(Stop.class, givenStop.getId());
        //then
        assertThat(obtainedStop).isEqualTo(givenStop);
    }

    @Test
    public void shouldGetStopsByPartOfAddress() {
        //given
        final String expectedAddress = "%upa%";
        final String query = queryProvider.getSelectStopByAddressQuery();
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("address", expectedAddress);
        //when
        List<Stop> obtainedStops = dao.findWithParameters(Stop.class, query, namedParameters);
        //then
        assertThat(obtainedStops).hasSize(2).containsExactlyInAnyOrder(stop1, stop2);
    }

    @Test
    public void shouldDeleteStop() {
        //given
        final int stopToDelete = stop1.getId();
        final String query = queryProvider.getSelectStopByAddressQuery();
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("address", "%");
        //when
        dao.delete(Stop.class, stopToDelete);
        //then
        assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> dao.find(Stop.class, stopToDelete));
        assertThat(dao.findWithParameters(Stop.class, query, namedParameters)).hasSize(1).containsOnly(stop2);
    }

    @After
    public void tearDown() {
        tearDownTest();
    }
}
