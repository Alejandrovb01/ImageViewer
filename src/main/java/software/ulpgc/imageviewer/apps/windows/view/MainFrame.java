package software.ulpgc.imageviewer.apps.windows.view;

import software.ulpgc.imageviewer.architecture.control.Command;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final SwingImageDisplay imageDisplay;
    private final Map<String, Command> commands;

    public MainFrame() throws HeadlessException {
        this.commands = new HashMap<>();
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(imageDisplay = createImageDisplay());
        this.add(createToolbar(), BorderLayout.SOUTH);
    }

    private Component createToolbar() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(createButton("Previous"));
        panel.add(createButton("Next"));
        return panel;
    }

    private Component createButton(String name) {
        JButton button = new JButton(name);
        button.addActionListener(e -> commands.get(name).execute());
        return button;
    }

    private SwingImageDisplay createImageDisplay() {
        return new SwingImageDisplay(new SwingImageDeserializer());
    }

    public ImageDisplay imageDisplay() {
        return imageDisplay;
    }

    public MainFrame add(String name, Command command) {
        commands.put(name, command);
        return this;
    }
}
