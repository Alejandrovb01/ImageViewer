package software.ulpgc.imageviewer.architecture.control;

import software.ulpgc.imageviewer.architecture.model.Image;
import software.ulpgc.imageviewer.apps.windows.view.ImageDisplay;

public class PaintOrderFactory {
    public static ImageDisplay.PaintOrder generatePaintOrderForNextImageWith(Image image, int displacementOffset) {
        return new ImageDisplay.PaintOrder(image.next().content(), displacementOffset);
    }

    public static ImageDisplay.PaintOrder generatePaintOrderForPreviousImageWith(Image image, int displacementOffset) {
        return new ImageDisplay.PaintOrder(image.previous().content(), displacementOffset);
    }

    public static ImageDisplay.PaintOrder generatePaintOrderForCurrentImageWith(Image image, int displacementOffset) {
        return new ImageDisplay.PaintOrder(image.content(), displacementOffset);
    }
}
