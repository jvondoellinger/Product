package jvondoellinger.ShopFree.listeners.interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BatchProcessor {
    CompletableFuture<Void> process(List<String> messages);
}
