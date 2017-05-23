package it.getconnected.filippo.me.utils;

import it.getconnected.filippo.me.services.MEServiceModule;

public class OptModule<T extends MEServiceModule> implements OptionalModule<T> {

    private static final Object EMPTY = initEmpty();

    private static <T extends MEServiceModule> OptionalModule<T> initEmpty() {
        // OptionalModule<T> result
        return new OptModule<T>(null);
    }

    @SuppressWarnings("unchecked")
    public static <T extends MEServiceModule> OptionalModule<T> empty() {
        return (OptionalModule<T>) EMPTY;
    }

    private T optInstance;

    public OptModule(T value) {
        optInstance = value;
    }

    @Override
    public T get() {
        return optInstance;
    }

    @Override
    public boolean isPresent() {
        return optInstance != null;
    }

}
