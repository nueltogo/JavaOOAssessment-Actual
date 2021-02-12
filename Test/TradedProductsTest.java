import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TradedProductsTest {

    @Mock
    ProductPricingService p = mock(ProductPricingService.class);

    @BeforeEach
    void setUp() {
        when(p.price(Mockito.anyString(),Mockito.anyString())).thenReturn(50.0);
        when(p.price(Mockito.anyString(),Mockito.anyString(),Mockito.anyInt(),Mockito.anyInt())).thenReturn(30.0);
    }

    @Test
    void addNewProduct() {
        Product p1 = new Futures("KL","LM","JUTY",10,2015,p);
        Product p2 = new Futures("JT","YE","JUTO",10,2015,p);
        //Product p3 = new Futures("KL","LM","JUTP",10,2015,p);

        List<Product> testProducts = Arrays.asList(p1,p2);

        TradedProducts electricals = new TradedProducts();
        try {
            electricals.addNewProduct(p1);
        //    electricals.addNewProduct(p3);
            electricals.addNewProduct(p2);
        }catch (ProductAlreadyRegisteredException e){
            System.out.println(e);
        }
        assertEquals(testProducts, new ArrayList<>(electricals.getRegisteredProducts().keySet()));
    }

    @Test
    void trade() {
        Product p1 = new Futures("KL","LM","JUTY",10,2015,p);
        Product p2 = new Futures("JT","YE","JUTO",10,2015,p);
        Product p3 = new Futures("KO","LS","JUTR",10,2015,p);
        Product p4 = new Stocks("KM","LK","JUT2",p);
        Product p5 = new Stocks("JQ","YP","JUT1",p);
        Product p6 = new Stocks("KP","LW","JUT3",p);
        TradedProducts funitures = new TradedProducts();
        try{
            funitures.addNewProduct(p1);
            funitures.addNewProduct(p2);
            funitures.addNewProduct(p4);
            funitures.addNewProduct(p5);
            funitures.addNewProduct(p6);
        }catch (ProductAlreadyRegisteredException e){
            System.out.println(e);
        }

        funitures.trade(p1,15);
        funitures.trade(p2,20);
        funitures.trade(p4,5);
        funitures.trade(p5,19);
        funitures.trade(p6,30);
        funitures.trade(p3, 16);
        Map<Product, Integer> quantityListTest = new HashMap<>();
        quantityListTest.put(p1, 15);
        quantityListTest.put(p2, 20);
        quantityListTest.put(p4, 5);
        quantityListTest.put(p5, 19);
        quantityListTest.put(p6, 30);
        assertEquals(quantityListTest, funitures.getRegisteredProducts());

    }

    @Test
    void totalTradeQuantityForDay() {
        Product p1 = new Futures("KL","LM","JUTY",10,2015,p);
        Product p2 = new Futures("JT","YE","JUTO",10,2015,p);
        Product p3 = new Futures("KO","LS","JUTR",10,2015,p);
        Product p4 = new Stocks("KM","LK","JUT2",p);
        Product p5 = new Stocks("JQ","YP","JUT1",p);
        Product p6 = new Stocks("KP","LW","JUT3",p);
        TradedProducts funitures = new TradedProducts();
        try{
            funitures.addNewProduct(p1);
            funitures.addNewProduct(p2);
            funitures.addNewProduct(p4);
            funitures.addNewProduct(p5);
            funitures.addNewProduct(p6);
        }catch (ProductAlreadyRegisteredException e){
            System.out.println(e);
        }

        funitures.trade(p1,15);
        funitures.trade(p2,20);
        funitures.trade(p4,5);
        funitures.trade(p5,19);
        funitures.trade(p6,30);
        Map<Product, Integer> quantityListTest = new HashMap<>();
        quantityListTest.put(p1, 15);
        quantityListTest.put(p2, 20);
        quantityListTest.put(p4, 5);
        quantityListTest.put(p5, 19);
        quantityListTest.put(p6, 30);
        int sumtest = 0;
        for(int quantity : quantityListTest.values()){
            sumtest += quantity;
        }
        int sum = funitures.totalTradeQuantityForDay();
        assertEquals(sumtest,sum);
    }

    @Test
    void totalValueOfDaysTradedProducts() {
        Product p1 = new Futures("KL","LM","JUTY",10,2015,p);
        Product p2 = new Futures("JT","YE","JUTO",10,2015,p);
        Product p3 = new Futures("KO","LS","JUTR",10,2015,p);
        Product p4 = new Stocks("KM","LK","JUT2",p);
        Product p5 = new Stocks("JQ","YP","JUT1",p);
        Product p6 = new Stocks("KP","LW","JUT3",p);
        TradedProducts funitures = new TradedProducts();
        try{
            funitures.addNewProduct(p1);
            funitures.addNewProduct(p2);
            funitures.addNewProduct(p4);
            funitures.addNewProduct(p5);
            funitures.addNewProduct(p6);
        }catch (ProductAlreadyRegisteredException e){
            System.out.println(e);
        }

        funitures.trade(p1,15);
        funitures.trade(p2,20);
        funitures.trade(p4,5);
        funitures.trade(p5,19);
        funitures.trade(p6,30);
        Map<Product, Integer> quantityListTest = new HashMap<>();
        quantityListTest.put(p1, 15);
        quantityListTest.put(p2, 20);
        quantityListTest.put(p4, 5);
        quantityListTest.put(p5, 19);
        quantityListTest.put(p6, 30);
        List<Double> priceList = quantityListTest.keySet().stream().map(Product::getCurrentPrice).collect(Collectors.toList());
        List<Integer> quantityList = new ArrayList<>(quantityListTest.values());
        double total = 0.0;
        for(int num = 0; num < priceList.size();num++){
            total += priceList.get(num) * quantityList.get(num);
        }
        assertEquals(total, funitures.totalValueOfDaysTradedProducts());
    }
}