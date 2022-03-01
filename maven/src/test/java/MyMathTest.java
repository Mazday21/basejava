import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

 class MyMathTest {
    @Test
     void ZeroDenominatorShouldThrowException() {
       Exception exception = assertThrows(ArithmeticException.class, () -> {
           MyMath.divide(1,0);
        }, "ArithmeticException was expected");

        Assertions.assertEquals(2, MyMath.divide(10,5), exception.getMessage());
    }
}
