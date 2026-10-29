package tr.com.huseyinaydin.support;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Sistem geçici dizininde bir klasör açar, kapanınca içindekilerle birlikte siler.
 * Demonun kullanıcının bilgisayarında iz bırakmamasını try-with-resources'a emanet etmek için var.
 */
public final class TemporaryWorkspace implements AutoCloseable {

    private final Path root;

    private TemporaryWorkspace(Path root) {
        this.root = root;
    }

    public static TemporaryWorkspace create(String prefix) {
        try {
            return new TemporaryWorkspace(Files.createTempDirectory(prefix));
        } catch (IOException e) {
            throw new WorkspaceException("Geçici çalışma klasörü oluşturulamadı", e);
        }
    }

    public Path root() {
        return root;
    }

    @Override
    public void close() {
        if (!Files.exists(root)) {
            return;
        }
        try {
            deleteRecursively(root);
        } catch (IOException e) {
            throw new WorkspaceException("Geçici çalışma klasörü silinemedi: " + root, e);
        }
    }

    // Files.delete dolu klasörü silemez; önce içindekiler, en son klasörün kendisi silinmeli.
    // walkFileTree bunu postVisitDirectory ile doğal olarak sağlar ve IOException'ı lambda'ya sarmaya gerek bırakmaz.
    private static void deleteRecursively(Path directory) throws IOException {
        Files.walkFileTree(directory, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exception) throws IOException {
                if (exception != null) {
                    throw exception;
                }
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
