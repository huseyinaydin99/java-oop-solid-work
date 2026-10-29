package tr.com.huseyinaydin.demo;

import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Path nesnesinin dosya sistemine dokunmadan yaptığı işlemler.
 */
public final class PathBasicsDemo implements DemoStep {

    private final PrintStream out;

    public PathBasicsDemo(PrintStream out) {
        this.out = Objects.requireNonNull(out, "out");
    }

    @Override
    public String title() {
        return "Path: of, resolve, normalize, relativize, getFileName";
    }

    @Override
    public void run(Path workDirectory) {
        // Paths.get eski API; Java 11'den beri Path.of aynı işi yapar ve önerilen yoldur.
        Path viaOf = Path.of("logs", "2026", "app.log");
        Path viaPaths = Paths.get("logs/2026/app.log");
        out.println("Path.of ile Paths.get aynı yolu mu verdi? " + viaOf.equals(viaPaths));

        // resolve: işletim sistemine göre doğru ayraçla yol birleştirir; "+" ile string birleştirmekten güvenlidir.
        Path logFile = workDirectory.resolve("logs").resolve("payment.log");
        out.println("resolve       : <çalışma>/" + workDirectory.relativize(logFile));

        // Kullanıcıdan ya da ayar dosyasından gelen yollar "." ve ".." içerebilir; karşılaştırmadan önce sadeleştirilir.
        Path messy = Path.of("logs", ".", "tmp", "..", "payment.log");
        out.println("normalize öncesi : " + messy);
        out.println("normalize sonrası: " + messy.normalize());

        Path reportFile = workDirectory.resolve("reports").resolve("log-report.txt");
        out.println("relativize (log -> rapor): " + logFile.getParent().relativize(reportFile));

        out.println("getFileName   : " + logFile.getFileName());
        out.println("getParent     : " + logFile.getParent().getFileName());
        out.println("resolveSibling: " + logFile.resolveSibling("archive").getFileName());
        out.println("Not: Bunların hiçbiri diske dokunmaz; Path yalnızca bir adrestir.");
    }
}
