import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DummyTest {
    @Test
    public void addNumber(){
        assertEquals(Dummy.addNumber(2,2),4);
        assertEquals(Dummy.addNumber(2,2),5);

    }
}
