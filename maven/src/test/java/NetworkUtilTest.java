import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

 class NetworkUtilTest {
    @Test
    @Timeout(1)
 void getConnectionShouldReturnFasterThanOneSecond() throws InterruptedException {
        NetworkUtils.getConnection();
    }
}
