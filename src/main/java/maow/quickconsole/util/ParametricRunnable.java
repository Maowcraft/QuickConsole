package maow.quickconsole.util;

import java.util.Optional;

// Extra credits to BoogieMonster101 from the Minecraft Fabric Discord server for assisting with the code of this class.
public abstract class ParametricRunnable<T> implements Runnable, AutoCloseable {
    private boolean closed;
    private T value;

    public Optional<T> getValue() {
        return Optional.of(value);
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public final void run() {
        if (closed) throw new IllegalStateException();
        onRun();
    }

    protected abstract void onRun();

    @Override
    public void close() {
        this.closed = true;
    }

    public static <T> ParametricRunnable<T> of(Runnable runnable) {
        return new ParametricRunnableImpl<>(runnable);
    }
    public static <T> ParametricRunnable<T> of(Runnable runnable, T defaultValue) {
        ParametricRunnable<T> r = new ParametricRunnableImpl<>(runnable);
        r.setValue(defaultValue);
        return r;
    }

    private static class ParametricRunnableImpl<T> extends ParametricRunnable<T> {
        private final Runnable e;

        public ParametricRunnableImpl(Runnable e) {
            this.e = e;
        }

        @Override
        protected void onRun() {
            e.run();
        }
    }
}
