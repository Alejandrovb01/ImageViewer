package software.ulpgc.imageviewer.apps.windows.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SwingImageDeserializer implements ImageDeserializer {
    private final Map<Integer, BufferedImage> memoize;

    public SwingImageDeserializer() {
        this.memoize = new HashMap<>();
    }

    @Override
    public Object deserialize(byte[] bytes) {
        return memoize.computeIfAbsent(Arrays.hashCode(bytes), i -> read(bytes));
    }

    private BufferedImage read(byte[] bytes) {
        try {
            return ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
