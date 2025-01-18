package software.ulpgc.imageviewer.architecture.control;

import software.ulpgc.imageviewer.architecture.model.Image;
import software.ulpgc.imageviewer.apps.windows.view.ImageDisplay;

public class ImagePresenter {
    private final ImageDisplay imageDisplay;
    private Image image;
    private final EventManager eventManager;

    public ImagePresenter(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
        this.eventManager = new EventManager(imageDisplay, this);
        this.imageDisplay.on(eventManager.shift());
        this.imageDisplay.on(eventManager.release());
    }

    public void show(Image image) {
        this.image = image;
        imageDisplay.paint(PaintOrderFactory.generatePaintOrderForCurrentImageWith(image, 0));
    }

    public Image currentImage() {
        return image;
    }
}
