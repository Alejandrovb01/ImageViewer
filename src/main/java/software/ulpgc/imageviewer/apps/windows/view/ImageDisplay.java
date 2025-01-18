package software.ulpgc.imageviewer.apps.windows.view;

public interface ImageDisplay {
    int width();

    void paint(PaintOrder... orders);
    void on(Shift shift);

    void on(Release release);

    record PaintOrder(byte[] content, int offset) {}

    interface Shift {
        Shift NULL = offset -> {};

        void offset(int offset);
    }

    interface Release {
        Release NULL = offset -> {};
        void offset(int offset);
    }
}
