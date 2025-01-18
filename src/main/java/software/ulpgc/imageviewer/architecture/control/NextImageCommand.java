package software.ulpgc.imageviewer.architecture.control;

public class NextImageCommand implements Command {
    private final ImagePresenter presenter;

    public NextImageCommand(ImagePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.show(presenter.currentImage().next());
    }
}
