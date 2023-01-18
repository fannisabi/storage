package ltk.sabianfanni.store.domain;

import ltk.sabianfanni.store.interfaces.Application;
import ltk.sabianfanni.store.interfaces.Persistence;
import ltk.sabianfanni.store.services.StoreItemInFile;
import ltk.sabianfanni.store.services.StoreItemInMemory;

public class SlimStoreRegister implements Application {
    private Persistence persistenceInterface;

    @Override
    public void setPersistanceType(StorePersistenceType storePersistenceType) {
        persistenceInterface = storePersistenceType.equals(StorePersistenceType.INMEMORY) ? new StoreItemInMemory() : new StoreItemInFile();
    }

    @Override
    public void createProduct(String productName) {
        persistenceInterface.saveItem(new StoreItem(productName));

    }

    @Override
    public void buyProductItem(String productName, int numberOfProduct) {
        persistenceInterface.saveItem(new StoreItem(productName, numberOfProduct));
    }

    @Override
    public int sellProductItem(String productName, int numberOfProduct) {
        StoreItem searchedItem = persistenceInterface.loadItem(productName);
        int checkedItemNumber = searchedItem.getNumOfItems() - numberOfProduct < 0
                ? searchedItem.getNumOfItems()
                : numberOfProduct;
        persistenceInterface.saveItem(new StoreItem(searchedItem.getProductName(), - checkedItemNumber));
        System.out.println("Megvásároltál " + checkedItemNumber + " db " + searchedItem.getProductName() + "-t");
        return checkedItemNumber;
    }
}
