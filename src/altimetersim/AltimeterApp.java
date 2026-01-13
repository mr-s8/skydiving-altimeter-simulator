package altimetersim;

import javax.swing.*;


public class AltimeterApp extends JFrame {

    private final AltimeterState state = new AltimeterState();
    private final AltimeterLogic logic = new AltimeterLogic(state);
    private final AltimeterView view = new AltimeterView();

    public AltimeterApp() {
        setTitle("Altimeter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(view.root);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // initially aad label shouldnt be visible.
        view.aadLabel.setVisible(false); // without this the label would be visible on the first frame, since the render
                                         // function hasnt been called yet

        // Button → Logic
        view.jumpButton.addActionListener(e -> logic.jump());

        view.pullButton.addActionListener(e -> logic.pull());

        view.resetButton.addActionListener(e -> {
            String text = view.exitAltitudeField.getText();
            logic.reset(text);
        });

        // Timer = Heartbeat
        Timer timer = new Timer(250, e -> {
            logic.tick();
            view.render(state);
        });
        timer.start();
    }
}
