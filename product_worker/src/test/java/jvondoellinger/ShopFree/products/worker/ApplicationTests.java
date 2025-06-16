package jvondoellinger.ShopFree.products.worker;

import jvondoellinger.ShopFree.core.entity.Product;
<<<<<<< HEAD
=======
import jvondoellinger.ShopFree.database.config.ProductTableConfig;
>>>>>>> 63f2632 (update - working)
import jvondoellinger.ShopFree.products.worker.creation.CrudsProducts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
<<<<<<< HEAD
=======
import org.springframework.context.annotation.Import;
>>>>>>> 63f2632 (update - working)
import reactor.core.publisher.Flux;

@SpringBootTest
@Import({ProductTableConfig.class})
class ApplicationTests {
    @Autowired
    private CrudsProducts products;

    @Test
    void createEntities() {
        products.createLoop();
    }

    @Test
    void batchCreateEntities() {
        products.batchCreate();
    }

    @Test
    void batchDeleteEntities() {
        products.batchDelete();
    }

    @Test
    void deleteEntities() {
        products.deleteLoop();
    }

    @Test
    void batchUpdateEntities() {
        products.batchUpdate();
    }

    @Test
    void updateEntities() {
        products.updateLoop();
    }
}
