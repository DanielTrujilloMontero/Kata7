package watch2;

import javax.swing.JFrame;

public class Main extends JFrame {

    public static void main(String[] args) {
        new Main().launch();
    }
    private final Watch watch;
    private final WatchDisplay watchDisplay;
    private final WatchPresenter watchPresenter;

    public Main() {
        this.watch = new Watch();
        this.watchDisplay = new WatchDisplay();
        this.watchPresenter = new WatchPresenter(watch, watchDisplay);
        this.initFrame();
    }
    
    
    private void initFrame() {
        this.setTitle("Watch");
        this.setUndecorated(false);
        this.setSize(400,400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(watchDisplay);
    }

    private void launch() {
        this.setVisible(true);
    }

    
}
