package ltk.sabianfanni.store.exceptions;

public class ItemNotAvailableException extends RuntimeException{
    public ItemNotAvailableException() {
        System.out.println("A termék nem elérhető!");
    }
}
