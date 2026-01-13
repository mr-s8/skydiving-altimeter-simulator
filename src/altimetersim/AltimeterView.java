package altimetersim;

import javax.swing.*;
import java.awt.*;

public class AltimeterView {

    public JPanel root = new JPanel(new BorderLayout());


    public JLabel heightLabel = new JLabel("4.00");
    public JLabel aadLabel = new JLabel("AAD FIRE");
    public JTextField exitAltitudeField = new JTextField(20);
    public JButton jumpButton = new JButton("Jump");
    public JButton resetButton = new JButton("Go to exit");
    public JButton pullButton = new JButton("Pull");

    public AltimeterView() {

        //// TOP BUTTON ROW
        JPanel buttonPanelTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        jumpButton.setPreferredSize(new Dimension(150, 50));
        resetButton.setPreferredSize(new Dimension(150, 50));
        jumpButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        resetButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonPanelTop.add(resetButton);
        buttonPanelTop.add(jumpButton);

        root.add(buttonPanelTop, BorderLayout.NORTH);

        // MID ROW
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

        pullButton.setPreferredSize(new Dimension(150, 50));
        pullButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        imageButtonPanel.add(pullButton);
        imageButtonPanel.setBounds(150, 10, 800, 400);

        heightLabel.setFont(new Font("Arial", Font.BOLD, 70));
        heightLabel.setForeground(Color.BLACK);
        heightLabel.setBounds(450, 130, 200, 80);

        aadLabel.setFont(new Font("Arial", Font.BOLD, 65));
        aadLabel.setForeground(Color.RED);
        aadLabel.setBounds(330, 330, 500, 80);

        layeredPane.add(imageButtonPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(heightLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(aadLabel, JLayeredPane.PALETTE_LAYER);

        root.add(layeredPane, BorderLayout.CENTER);

        // BOTTOM ROW
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

        root.add(exitAltitudePanel, BorderLayout.SOUTH);

    }

    public void render(AltimeterState state) {
        heightLabel.setText(AltimeterUtil.getDisplayHeight((int) state.height));
        aadLabel.setVisible(state.aadFired);
    }
}
