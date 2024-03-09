package altimetersim;

import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AltimeterApp extends JFrame {

    private JTextField exitAltitudeField;
    private JLabel numberLabel;
    private JLabel aadLabel;

    public AltimeterApp() {

        // Gui Configuration
        setTitle("Altimeter Sim");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(false);

        // Declaring the elements that have to be controlled by the AltiLogic class
        exitAltitudeField = new JTextField(20);
        numberLabel = new JLabel("4.00");
        aadLabel = new JLabel("AAD Fire");
        AltiLogic logic = new AltiLogic(exitAltitudeField, numberLabel, aadLabel);

        // Top Row with reset and jump button
        JPanel buttonPanelTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton jumpButton = new JButton("Jump");
        JButton resetButton = new JButton("Go back up");

        jumpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.jumpPressed();

            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.goBackUpPressed();

            }
        });

        jumpButton.setPreferredSize(new Dimension(150, 50));
        resetButton.setPreferredSize(new Dimension(150, 50));

        jumpButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        resetButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonPanelTop.add(resetButton);
        buttonPanelTop.add(jumpButton);

        add(buttonPanelTop, BorderLayout.NORTH);

        // Mid row with Image and button
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 500));

        JPanel imageButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        ImageIcon imageIcon = new ImageIcon("assets/viso2+.png");
        Image image = imageIcon.getImage();
        int originalWidth = imageIcon.getIconWidth();
        int originalHeight = imageIcon.getIconHeight();
        int targetWidth = 400;
        int targetHeight = (int) ((double) originalHeight / originalWidth * targetWidth);
        Image scaledImage = image.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        imageButtonPanel.add(imageLabel);

        JButton pullButton = new JButton("Pull");
        pullButton.setPreferredSize(new Dimension(150, 50));
        pullButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        imageButtonPanel.add(pullButton);
        imageButtonPanel.setBounds(150, 10, 800, 400);

        numberLabel.setFont(new Font("Arial", Font.BOLD, 70));
        numberLabel.setForeground(Color.BLACK);
        numberLabel.setBounds(450, 130, 200, 80);

        aadLabel.setFont(new Font("Arial", Font.BOLD, 65));
        aadLabel.setForeground(Color.RED);
        aadLabel.setBounds(330, 330, 500, 80);

        layeredPane.add(imageButtonPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(numberLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(aadLabel, JLayeredPane.PALETTE_LAYER);

        pullButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.pullParachute();

            }
        });

        add(layeredPane, BorderLayout.CENTER);

        // Bottom Row with entry field for exit altitude
        JPanel exitAltitudePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        JLabel exitAltitudeLabel = new JLabel("Exit Altitude:");

        JLabel infoIconLabel = new JLabel(new ImageIcon("assets/info_icon16.png"));
        infoIconLabel.setToolTipText("-only values between 1.000 and 10.000\n-default is 4.000(m)"); // Set the hover
                                                                                                     // text

        JPanel exitAltitudeInputPanel = new JPanel();
        exitAltitudeInputPanel.add(infoIconLabel);
        exitAltitudeInputPanel.add(exitAltitudeLabel);
        exitAltitudeInputPanel.add(exitAltitudeField);

        exitAltitudePanel.add(exitAltitudeInputPanel);

        add(exitAltitudePanel, BorderLayout.SOUTH);

        // Set theme
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        // Update UI
        SwingUtilities.updateComponentTreeUI(this);

    }

}
