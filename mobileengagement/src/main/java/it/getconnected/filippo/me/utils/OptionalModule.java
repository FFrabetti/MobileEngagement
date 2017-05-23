package it.getconnected.filippo.me.utils;

import it.getconnected.filippo.me.services.MEServiceModule;

public interface OptionalModule<T extends MEServiceModule> {

    public T get();
    public boolean isPresent();

}

// ---------------- ADAPTER for java.util.Optional<T> ----------------

//public class OptionalAdapter<T extends MEServiceModule> implements OptionalModule<T> {
//
//    private Optional<T> opt;
//
//    public OptionalAdapter(T value) {
//        opt = Optional.of(value);
//    }
//
//    @Override
//    public T get() {
//        return opt.get();
//    }
//
//    @Override
//    public boolean isPresent() {
//        return opt.isPresent();
//    }
//
//}
