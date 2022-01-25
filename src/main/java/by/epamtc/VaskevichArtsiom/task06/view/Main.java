package by.epamtc.VaskevichArtsiom.task06.view;

import by.epamtc.VaskevichArtsiom.task06.entity.Matrix;
import by.epamtc.VaskevichArtsiom.task06.logic.WriterFirst;
import by.epamtc.VaskevichArtsiom.task06.logic.WriterSecond;
import by.epamtc.VaskevichArtsiom.task06.logic.Writer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Matrix matrix = Matrix.getInstance();
        int n = matrix.getN();
        int threadName = (int) (Math.random() * 100);
        ExecutorService service = Executors.newScheduledThreadPool(n * 2);
        List<WriterFirst> threadsFirstPart = new ArrayList<>();
        List<WriterSecond> threadsSecondPart = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            threadsFirstPart.add(new WriterFirst(matrix, threadName + i, i));
        }
        for (int i = n; i < n * 2; i++) {
            threadsSecondPart.add(new WriterSecond(matrix, threadName + i, i));
        }
        try {
            threadsFirstPart.forEach(service::execute);
            threadsSecondPart.forEach(service::execute);
            service.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try (Writer writer = new Writer()) {
            writer.writing(matrix.getStringBuffer().toString());
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
        service.shutdown();
    }
}
