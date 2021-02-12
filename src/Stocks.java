public class Stocks extends Product {
    private Double currentPrice;
    private String ticker;
    private String exchange;
    ProductPricingService pricingService;

    public Stocks(String ID,String ticker, String exchange, ProductPricingService pricingService) {
        super(ID);
        this.ticker = ticker;
        this.exchange = exchange;
        this.pricingService = pricingService;
        this.currentPrice = this.pricingService.price(this.exchange,this.ticker);
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public String getTicker() {
        return ticker;
    }

    public String getExchange() {
        return exchange;
    }
}
