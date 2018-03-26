import javax.swing.*;
import java.awt.*;

public class TreeViewer {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
                JFrame frame = new DOMTreeFrame();
                frame.setTitle("TreeViewer");
                frame.setSize(700, 500);
                frame.setLocation(200, 200);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        );
    }
}
