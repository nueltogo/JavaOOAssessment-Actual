import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StocksTest {
    @Mock
    ProductPricingService p = mock(ProductPricingService.class);

    @BeforeEach
    void setUp() {
        when(p.price(Mockito.anyString(),Mockito.anyString())).thenReturn(50.0);
        when(p.price(Mockito.anyString(),Mockito.anyString(),Mockito.anyInt(),Mockito.anyInt())).thenReturn(50.0);
    }

    @Test
    void getCurrentPrice() {
        Stocks newStocks = new Stocks("ss", "ss", "ss", p);
        System.out.println(newStocks.getCurrentPrice());
        verify(p).price("ss","ss");
    }
}