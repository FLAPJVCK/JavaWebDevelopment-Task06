package by.epamtc.VaskevichArtsiom.task06.logic;

import by.epamtc.VaskevichArtsiom.task06.entity.Matrix;

public class WriterSecond extends WriterFirst {

    public WriterSecond(Matrix matrix, int threadName, int threadNumber) {
        super(matrix, threadName, threadNumber);
    }

    @Override
    public void run() {
        this.changeRecordingDirection();
    }

    @Override
    public void changeRecordingDirection() {
        for (int x = threadNumber - (matrix.getN() - 1); x < matrix.getN(); x++) {
            for (int y = (matrix.getN() - 1); y > 0 && x < matrix.getN(); y--) {
                matrix.fillMatrix(x, y, threadName);
                x++;
            }
        }
    }
}
