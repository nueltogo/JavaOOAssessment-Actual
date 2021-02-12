public class Futures extends Product {
    private Double currentPrice;
    private String exchange;
    private String contractCode;
    private int month;
    private int year;
    ProductPricingService pricingService;

    public Futures(String ID, String exchange, String contractCode, int month, int year, ProductPricingService pricingService) {
        super(ID);
        this.exchange = exchange;
        this.contractCode = contractCode;
        this.month = month;
        this.year = year;
        this.pricingService = pricingService;
        this.currentPrice = this.pricingService.price(this.exchange,this.contractCode,this.month,this.year);
    }


    public Double getCurrentPrice() {
        return currentPrice;
    }

    public String getExchange() {
        return exchange;
    }

    public String getContractCode() {
        return contractCode;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
