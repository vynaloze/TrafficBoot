import com.vynaloze.trafficboot.Main;
import com.vynaloze.trafficboot.dao.DAO;
import com.vynaloze.trafficboot.model.Stop;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@TestPropertySource(locations = "classpath:test.properties")
public class DaoTest extends DaoTestBase {
    @Autowired
    private DAO dao;

    @Before
    public void setUp() {
        setUpTest();
    }

    @Test
    public void shouldInsertAndSelect() {
        //given
        Stop givenStop = stop1;
        //when
        dao.insertStop(givenStop);
        //then
        assertThat(dao.getStopById(givenStop.getId())).isEqualTo(givenStop);
    }

    @Test
    public void shouldReturnListOfStops() {
        //given
        String expectedAddress = "upa";
        //when
        dao.insertStop(stop1);
        dao.insertStop(stop2);
        //then
        assertThat(dao.getStopsByAddress(expectedAddress)).hasSize(2).containsExactlyInAnyOrder(stop1, stop2);
    }

    @After
    public void tearDown() {
        tearDownTest();
    }

}
