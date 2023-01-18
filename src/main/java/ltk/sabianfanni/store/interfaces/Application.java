package ltk.sabianfanni.store.interfaces;

import ltk.sabianfanni.store.domain.StorePersistenceType;

public interface Application {
    void setPersistanceType(StorePersistenceType storePersistenceType);
    void createProduct(String productName);
    void buyProductItem(String productName, int numberOfProduct);
    int sellProductItem(String productName, int numberOfProduct);
}
