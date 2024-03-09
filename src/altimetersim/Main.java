package altimetersim;

import javax.swing.SwingUtilities;

public class Main {
    static AltimeterApp app = new AltimeterApp();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            app.setVisible(true);
        });
    }
}
