import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FuturesTest {

    @Mock
    ProductPricingService p = mock(ProductPricingService.class);

    @BeforeEach
    void setUp() {
        when(p.price(Mockito.anyString(),Mockito.anyString())).thenReturn(50.0);
        when(p.price(Mockito.anyString(),Mockito.anyString(),Mockito.anyInt(),Mockito.anyInt())).thenReturn(50.0);
    }

    @org.junit.jupiter.api.Test
    void getCurrentPrice() {
        Futures newStocks = new Futures("ss","ss","ss",21,21,p);
        System.out.println(newStocks.getCurrentPrice());
        verify(p).price("ss","ss",21,21);
    }


}