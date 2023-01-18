package ltk.sabianfanni.store.domain;

public class StoreItem {
    private String productName;
    private int numOfItems;

    public StoreItem(String productName) {
        this.productName = productName;
    }

    public StoreItem(String productName, int numOfItems) {
        this.productName = productName;
        this.numOfItems = numOfItems;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }

    @Override
    public String toString() {
        return "StoreItem{" +
                "productName='" + productName + '\'' +
                ", numOfItems=" + numOfItems +
                '}';
    }
}
