package software.ulpgc.imageviewer.architecture.control;

import software.ulpgc.imageviewer.architecture.model.Image;
import software.ulpgc.imageviewer.apps.windows.view.ImageDisplay;

public class EventManager {
    private final ImageDisplay imageDisplay;
    private final ImagePresenter imagePresenter;

    public EventManager(ImageDisplay imageDisplay, ImagePresenter imagePresenter) {
        this.imageDisplay = imageDisplay;
        this.imagePresenter = imagePresenter;
    }

    public ImageDisplay.Shift shift() {
        return displacementOffset -> {
            Image image = imagePresenter.currentImage();
            imageDisplay.paint(
                    PaintOrderFactory.generatePaintOrderForCurrentImageWith(image, displacementOffset),
                    isDisplayingPreviousImageWith(displacementOffset) ?
                            PaintOrderFactory.generatePaintOrderForPreviousImageWith(image, displacementOffset - imageDisplay.width()) :
                            PaintOrderFactory.generatePaintOrderForNextImageWith(image, imageDisplay.width() + displacementOffset)
            );
        };
    }

    public ImageDisplay.Release release() {
        return displacementOffset -> {
            if (isDisplayingCurrentImageWith(displacementOffset)) {
                Image image = displacementOffset > 0 ? imagePresenter.currentImage().previous() : imagePresenter.currentImage().next();
                imagePresenter.show(image);
            }
        };
    }

    private boolean isDisplayingPreviousImageWith(int displacementOffset) {
        return displacementOffset > 0;
    }

    private boolean isDisplayingCurrentImageWith(int displacementOffset) {
        return Math.abs(displacementOffset) > imageDisplay.width() / 2;
    }
}
