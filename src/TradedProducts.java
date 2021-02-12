import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class TradedProducts implements MontrealTradedProducts{
    //HashMap to hold the product as well as the quantity of the product traded
    private Map<Product, Integer> registeredProducts = new HashMap<>();

    public Map<Product, Integer> getRegisteredProducts() {
        return registeredProducts;
    }

    @Override
    public void addNewProduct(Product product) throws ProductAlreadyRegisteredException {
        List<String> listOfID = registeredProducts.keySet().stream().map(Product::getID).collect(Collectors.toList());
        if(listOfID.contains(product.getID())) throw new ProductAlreadyRegisteredException(); //Checks to see if product being added has its id already registered in system if so throws an exception.
        registeredProducts.put(product, 0);
    }

    @Override
    public void trade(Product product, int quantity) {
        List<String> listOfID = registeredProducts.keySet().stream().map(Product::getID).collect(Collectors.toList());
        if(listOfID.contains(product.getID())){ //Checks to see if product being traded has ben registered in the system
            registeredProducts.put(product, registeredProducts.get(product)+quantity);
        }
        else{
            System.out.println("Product with ID "+ product.getID() + " not registered."); //displays the product ID of the product which has not been registered.
        }
    }

    @Override
    public int totalTradeQuantityForDay() {
        return registeredProducts.values().stream().reduce(0, Integer::sum);
    }

    @Override
    public double totalValueOfDaysTradedProducts() {
        List<Double> currentPriceList = registeredProducts.keySet().stream().map(Product::getCurrentPrice).collect(Collectors.toList());
        List<Integer> quantitiesList = new ArrayList<>(registeredProducts.values());
        double total = 0.0;
        for(int num = 0; num < currentPriceList.size();num++){
            total += currentPriceList.get(num) * quantitiesList.get(num);
        }
        return total;
    }
}
