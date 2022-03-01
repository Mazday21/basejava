import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

 class Vector2DTest {
     private  final double EPS = 1e-9;
     private static Vector2D v1;

     @BeforeAll
     static void createNewVector() {
         v1 = new Vector2D();
     }

    @Test
     void newVectorShouldHaveZeroLength() {
        //assertion проверить ожидаемый результат с фактическим
        Assertions.assertEquals(0, v1.length(), EPS);
    }

    @Test
     void newVectorShouldHaveZeroX() {
        Assertions.assertEquals(0, v1.getX(), EPS);
    }

    @Test
     void newVectorShouldHaveZeroY() {
        Assertions.assertEquals(0, v1.getY(), EPS);
    }
}
