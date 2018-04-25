import com.vynaloze.trafficboot.model.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DaoTestBase {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    protected final Stop stop1 = new Stop(1, "dupa");
    protected final Stop stop2 = new Stop(2, "pupa");

    protected void setUpTest() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS test.stops (id integer, address text)");
    }

    protected void tearDownTest() {
        jdbcTemplate.execute("DROP TABLE test.stops");
    }
}
