package by.epamtc.VaskevichArtsiom.task06.logic;

import by.epamtc.VaskevichArtsiom.task06.entity.Matrix;

public class WriterFirst extends Thread {
    Matrix matrix;
    int threadNumber;
    int threadName;

    public WriterFirst(Matrix matrix, int threadName, int threadNumber) {
        this.matrix = matrix;
        this.threadName = threadName;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        changeRecordingDirection();
    }

    public void changeRecordingDirection() {
        for (int x = 0; x <= threadNumber; x++) {
            for (int y = threadNumber; y >= 0; y--) {
                matrix.fillMatrix(x, y, threadName);
                x++;
            }
        }
    }
}
