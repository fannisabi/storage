package ltk.sabianfanni.store.services;

import ltk.sabianfanni.store.domain.StoreItem;
import ltk.sabianfanni.store.exceptions.ItemNotAvailableException;
import ltk.sabianfanni.store.interfaces.Persistence;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StoreItemInFile implements Persistence {
    private List<StoreItem> storeItemList;
    public Path file = Paths.get("inventory.csv");


    public StoreItemInFile() {
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
            saveToFile();
            return;
        }
        searchedItem.setNumOfItems(searchedItem.getNumOfItems() + item.getNumOfItems());
        saveToFile();
    }

    public void saveToFile() {
        try (BufferedWriter bwriter = Files.newBufferedWriter(file)) {
            for (StoreItem storeItem : storeItemList) {
                String item = "";
                item += storeItem.getProductName()
                        .concat(",")
                        .concat(String.valueOf(storeItem.getNumOfItems()));
                bwriter.write(item);
                bwriter.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private StoreItem checkIfItemIsAvailable(String productName) {
        try {
            storeItemList = Files.readAllLines(file).stream().map(storageString -> {
                String[] storeData = storageString.split(",");
                StoreItem newStoreItem = new StoreItem(storeData[0], Integer.parseInt(storeData[1]));
                return newStoreItem;
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return storeItemList
                .stream()
                .filter(item -> {
                    System.out.println(item);
                    return item.getProductName().equals(productName);
                }).findFirst().orElse(null);
    }
}
