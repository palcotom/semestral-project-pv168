import javax.swing.*;
import java.awt.*;

/**
 * @author Oskar Spacek
 */
public class MainWindow extends JFrame {

    private MainWindow() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() ->
                new MainWindow().setVisible(true));
    }
}
