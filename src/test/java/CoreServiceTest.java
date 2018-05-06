import com.vynaloze.trafficboot.dao.DAO;
import com.vynaloze.trafficboot.model.Stop;
import com.vynaloze.trafficboot.model.exception.DuplicateStopFoundException;
import com.vynaloze.trafficboot.service.CoreServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CoreServiceTest {
    @Mock
    private DAO<Stop> dao;
    @InjectMocks
    private CoreServiceImpl service;

    private Stop stop1 = new Stop(1, "one");
    private Stop stop2 = new Stop(2, "two");

    @Test
    public void shouldNotAddDuplicateStop() {
        //given
        when(dao.find(Stop.class, stop1.getId())).thenReturn(stop1);
        //then
        assertThatExceptionOfType(DuplicateStopFoundException.class).isThrownBy(() -> service.addStop(stop1));
    }
}
