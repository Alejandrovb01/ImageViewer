package software.ulpgc.imageviewer.architecture.control;

import software.ulpgc.imageviewer.apps.windows.view.ImageDisplay;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseEventManager {
    private final ImageDisplay.Shift shift;
    private final ImageDisplay.Release release;
    private int initX;

    public MouseEventManager(ImageDisplay.Shift shift, ImageDisplay.Release release) {
        this.shift = shift;
        this.release = release;
    }

    public MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                shift.offset(e.getX() - initX);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        };
    }

    public MouseAdapter mouseListener() {
        return new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                initX = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                release.offset(e.getX() - initX);
            }
        };
    }
}
