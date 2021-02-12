public class ProductAlreadyRegisteredException extends Exception {
    public ProductAlreadyRegisteredException() {
        System.out.println("Product with this ID has already been registered.");
    }
}
