package ventana;
import javax.swing.JFrame;

public class Window extends JFrame {

    public static final int WIDTH = 800, HEIGHT = 600;

    public Window() {
        setTitle("Space Ship Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        GamePanel panel = new GamePanel();
        add(panel); // Agrega el panel

        setVisible(true);
    }

    public static void main(String[] args) {
        new Window();
    }
}
