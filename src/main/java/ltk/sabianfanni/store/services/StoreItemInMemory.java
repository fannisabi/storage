package ltk.sabianfanni.store.services;

import ltk.sabianfanni.store.domain.StoreItem;
import ltk.sabianfanni.store.exceptions.ItemNotAvailableException;
import ltk.sabianfanni.store.interfaces.Persistence;

import java.util.ArrayList;
import java.util.List;

public class StoreItemInMemory implements Persistence {
    private List<StoreItem> storeItemList;

    public StoreItemInMemory() {
        this.storeItemList = new ArrayList<>();
    }

    @Override
    public StoreItem loadItem(String productName) {
        StoreItem searchedItem = checkIfItemIsAvailable(productName);
        if (searchedItem == null) {
            throw new ItemNotAvailableException();
        };
        return searchedItem;
    }

    @Override
    public void saveItem(StoreItem item) {
        StoreItem searchedItem = checkIfItemIsAvailable(item.getProductName());
        if (searchedItem == null) {
            storeItemList.add(item);
            return;
        }
        searchedItem.setNumOfItems(searchedItem.getNumOfItems() + item.getNumOfItems());
    }

    private StoreItem checkIfItemIsAvailable(String productName) {
        return storeItemList
                .stream()
                .filter(item -> {
                    System.out.println(item);
                    return item.getProductName().equals(productName);
                }).findFirst().orElse(null);
    }
}
