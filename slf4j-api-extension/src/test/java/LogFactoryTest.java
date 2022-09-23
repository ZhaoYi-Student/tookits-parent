import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogFactoryTest {

    private final Logger log = LoggerFactory.getLogger(LogFactoryTest.class);

    @Test
    public void infoTest() {
        log.info("result is {}", 1);
    }

}
