package tr.com.huseyinaydin.resource;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Açılış ve kapanışını bildiren örnek kaynak. Veritabanı bağlantısı ya da dosya gibi
 * gerçek kaynakların try-with-resources içindeki yaşam döngüsünü görünür kılmak için var.
 */
public final class TrackedResource implements AutoCloseable {

    private final String name;
    private final Consumer<String> eventSink;
    private final boolean failsOnClose;
    private boolean closed;

    private TrackedResource(String name, Consumer<String> eventSink, boolean failsOnClose) {
        this.name = Objects.requireNonNull(name, "name");
        this.eventSink = Objects.requireNonNull(eventSink, "eventSink");
        this.failsOnClose = failsOnClose;
        eventSink.accept("açıldı: " + name);
    }

    public static TrackedResource open(String name, Consumer<String> eventSink) {
        return new TrackedResource(name, eventSink, false);
    }

    // Boolean parametre yerine ayrı fabrika metodu: çağıran kodda niyet okunur kalsın.
    public static TrackedResource openFailingOnClose(String name, Consumer<String> eventSink) {
        return new TrackedResource(name, eventSink, true);
    }

    public void use(String action) {
        if (closed) {
            throw new IllegalStateException("Kapalı kaynak kullanılamaz: " + name);
        }
        eventSink.accept("kullanıldı: " + name + " (" + action + ")");
    }

    public boolean isClosed() {
        return closed;
    }

    // Exception imzası daraltıldı: çağıranlar gereksiz yere "throws Exception" yazmak zorunda kalmasın.
    @Override
    public void close() {
        if (closed) {
            return;
        }
        closed = true;
        eventSink.accept("kapandı: " + name);
        if (failsOnClose) {
            throw new ResourceCloseException(name);
        }
    }
}
