import com.vynaloze.trafficboot.model.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DaoTestBase {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    protected final Stop stop1 = new Stop(1, "dupa");
    protected final Stop stop2 = new Stop(2, "pupa");

    protected void tearDownTest() {
        jdbcTemplate.execute("TRUNCATE TABLE test.stops");
    }
}
