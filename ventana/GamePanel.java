package ventana;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class GamePanel extends JPanel {

    public GamePanel() {
        setBackground(Color.BLACK); // Fondo del panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Aquí puedes dibujar tu nave, enemigos, etc.
        g.setColor(Color.WHITE);
        g.drawString("¡Bienvenido al juego!", 50, 50);
    }
}
