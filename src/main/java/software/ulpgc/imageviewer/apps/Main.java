package software.ulpgc.imageviewer.apps;

import software.ulpgc.imageviewer.apps.windows.view.MainFrame;
import software.ulpgc.imageviewer.architecture.model.Image;
import software.ulpgc.imageviewer.architecture.model.FileImageLoader;
import software.ulpgc.imageviewer.architecture.control.ImagePresenter;
import software.ulpgc.imageviewer.architecture.control.PreviousImageCommand;
import software.ulpgc.imageviewer.architecture.control.NextImageCommand;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        ImagePresenter imagePresenter = new ImagePresenter(mainFrame.imageDisplay());
        mainFrame
                .add("Next", new NextImageCommand(imagePresenter))
                .add("Previous", new PreviousImageCommand(imagePresenter));
        imagePresenter.show(firstImage());
        mainFrame.setVisible(true);
    }
    private static Image firstImage() {
        return new FileImageLoader(new File("images")).load();
    }
}
