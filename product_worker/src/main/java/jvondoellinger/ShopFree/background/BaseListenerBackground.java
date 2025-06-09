package jvondoellinger.ShopFree.background;

import jvondoellinger.ShopFree.core.entity.ProductFactory;
import jvondoellinger.ShopFree.core.repository.IProductRepository;
import jvondoellinger.ShopFree.workers.listeners.AbstractListener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
public class BaseListenerBackground implements CommandLineRunner {
	private final List<AbstractListener> listeners = new ArrayList<>();
    private final IProductRepository repository;
    private final ProductFactory factory;

    public BaseListenerBackground(IProductRepository repository, ProductFactory factory) {
        this.repository = repository;
        //listeners.add(listener);
        this.factory = factory;
    }

	@Override
	public void run(String... args) throws Exception {
		// parallelStart();

	}

	private void parallelStart() {
		Flux.fromIterable(listeners)
				.map(AbstractListener::listen)
				.parallel();
	}
}
