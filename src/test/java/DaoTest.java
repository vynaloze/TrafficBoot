import com.vynaloze.trafficboot.Main;
import com.vynaloze.trafficboot.dao.DAO;
import com.vynaloze.trafficboot.model.Stop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class DaoTest {
    @Autowired
    private DAO dao;

    @Test
    public void shouldInsertAndSelect() {
        //given
        int id = 2;
        Stop stop = new Stop(id, "dupa");
        //when
        dao.insertStop(stop);
        //then
        assertThat(dao.getStopById(id)).isEqualTo(stop);
    }

}
