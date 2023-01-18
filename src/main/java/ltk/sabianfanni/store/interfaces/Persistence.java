package ltk.sabianfanni.store.interfaces;

import ltk.sabianfanni.store.domain.StoreItem;

public interface Persistence {
    StoreItem loadItem(String productName);
    void saveItem(StoreItem item);
}
