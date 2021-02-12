public abstract class Product{
    private String ID;
    private Double currentPrice;



    public Product(String ID){
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    abstract Double getCurrentPrice();

}
