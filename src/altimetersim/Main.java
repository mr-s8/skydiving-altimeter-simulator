package altimetersim;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;


public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // Set theme
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } catch (Exception ex) {
                System.err.println("Failed to initialize LaF");
            }

            AltimeterApp app = new AltimeterApp();

            app.setVisible(true);
        });
    }
}
