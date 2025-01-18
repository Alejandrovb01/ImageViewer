package software.ulpgc.imageviewer.apps.windows.view;

import software.ulpgc.imageviewer.architecture.control.MouseEventManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private final ImageDeserializer deserializer;
    private final MouseEventManager mouseEventManager;
    private final List<PaintOrder> orders;
    private Shift shift = Shift.NULL;
    private Release release = Release.NULL;

    @Override
    public void on(Shift shift) {
        this.shift = shift;
    }

    public SwingImageDisplay(ImageDeserializer deserializer) {
        this.mouseEventManager = new MouseEventManager(this::handleShift, this::handleRelease);
        this.deserializer = deserializer;
        this.orders = new ArrayList<>();
        this.addMouseListener(mouseEventManager.mouseListener());
        this.addMouseMotionListener(mouseEventManager.mouseMotionListener());
    }

    private void handleShift(int offset) {
        shift.offset(offset);
    }

    private void handleRelease(int offset) {
        release.offset(offset);
    }

    @Override
    public void on(Release release) {
        this.release = release;
    }

    @Override
    public int width() {
        return getWidth();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        orders.forEach(o -> paintOrder(g, o));
    }

    @Override
    public void paint(PaintOrder... orders) {
        this.orders.clear();
        Collections.addAll(this.orders, orders);
        repaint();
    }

    private void paintOrder(Graphics g, PaintOrder order) {
        Image image = deserialize(order.content());
        ViewPort viewPort = ViewPort.ofSize(this.getWidth(), this.getHeight())
                .fit(image.getWidth(null), image.getHeight(null));
        g.drawImage(image,viewPort.x() + order.offset(), viewPort.y(),viewPort.width(), viewPort.height(), null);
    }

    private Image deserialize(byte[] content) {
        return (Image) deserializer.deserialize(content);
    }
}
