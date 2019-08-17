package chap8;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

public class Compress {

    private final CompressionStrategy strategy;

    public Compress(CompressionStrategy strategy) {
        this.strategy = strategy;
    }

    public void compress(Path inFile, File outFile) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(outFile)) {
            Files.copy(inFile, strategy.compress(outputStream));
        }
    }

    public static void main(String[] args) {
        Compress gzipCompress = new Compress(GZIPOutputStream::new);
        Compress zipCompress = new Compress(ZipOutputStream::new);

    }
}
