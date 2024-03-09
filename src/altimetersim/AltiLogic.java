package altimetersim;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

// Class that handles decrementing the height
public class AltiLogic {
    private double height = 4000.0;
    private Boolean jumping = false;
    private Boolean onAircraft = true;
    private JTextField exitAltitudeField;
    private JLabel numberLabel;
    private JLabel aadLabel;
    private int freeFallRate = 15;
    private Boolean startPhase = true;
    private Boolean pulled = false;

    public AltiLogic(JTextField exitAltitudeField, JLabel numberLabel, JLabel aadLabel) {
        this.exitAltitudeField = exitAltitudeField;
        this.numberLabel = numberLabel;
        this.aadLabel = aadLabel;
        this.aadLabel.setVisible(false);
    }

    void goBackUpPressed() {
        this.aadLabel.setVisible(false);
        this.pulled = false;
        if (this.jumping) { // in this case the jumpThread sets back the display
            this.onAircraft = true;
            this.jumping = false;
            this.freeFallRate = 15;
        } else {
            this.onAircraft = true;
            this.freeFallRate = 15;
            String exitHeight = exitAltitudeField.getText();
            int heightValue = getValidHeight(exitHeight);
            this.height = (int) heightValue;
            String displayHeight = getDisplayHeight((int) this.height);
            numberLabel.setText(displayHeight);
        }
    }

    void jumpPressed() {
        if (this.onAircraft == false || this.jumping) {
            return;
        }
        this.jumping = true;
        this.onAircraft = false;
        this.pulled = false;

        Thread jumpThread = new Thread(() -> {

            int iteration = 0;
            this.startPhase = true;
            while (this.onAircraft == false && this.height != 0) {
                if (iteration < 8 && startPhase) {
                    this.freeFallRate = 15;
                } else if (iteration > 7 && this.freeFallRate < 28 && startPhase) {
                    this.freeFallRate = 30;
                } else if (iteration > 27 && iteration < 40 && startPhase) {
                    this.freeFallRate = 40;
                } else if (iteration > 39 && startPhase) {
                    this.freeFallRate = 50;
                    startPhase = false;
                }
                sleep(250);
                this.height -= (0.25 * this.freeFallRate);
                if (this.height < 0) {
                    this.height = 0.0;
                }
                if (this.height < 6) { // for the flare; later when there is no aad, this has to be changed
                    this.freeFallRate = 3;
                }

                if (this.height < 226 && this.freeFallRate > 35) { // AAD fire
                    this.aadLabel.setVisible(true);
                    pullParachute(); // later add reserve function
                }

                String displayHeight = getDisplayHeight((int) this.height);
                SwingUtilities.invokeLater(() -> {
                    numberLabel.setText(displayHeight);
                });
                iteration += 1;
            }

            if (this.onAircraft) { // If Go back up has been pressed this is the case
                String exitHeight = exitAltitudeField.getText();
                int heightValue = getValidHeight(exitHeight);
                this.height = heightValue;
                String displayHeight = getDisplayHeight((int) this.height);
                numberLabel.setText(displayHeight);
            }
            this.jumping = false;
            this.freeFallRate = 15;

        });
        jumpThread.start();
    }

    void pullParachute() {
        if (this.jumping == false || this.pulled) {
            return;
        }
        this.pulled = true;
        Thread pullThread = new Thread(() -> { // still a pretty fast opening
            this.startPhase = false;
            this.freeFallRate = 40;
            sleep(500);
            this.freeFallRate = 30;
            sleep(500);
            this.freeFallRate = 20;
            sleep(250);
            this.freeFallRate = 10;
            sleep(250);
            this.freeFallRate = 5;
        });
        pullThread.start();
    }

    String getDisplayHeight(int heightValue) {
        int ones = heightValue % 10;
        int tens = (heightValue / 10) % 10;
        int hundreds = (heightValue / 100) % 10;
        int thousands = (heightValue / 1000) % 10;
        int tenThousands = (heightValue / 10000) % 10;
        String res = "";
        if (tenThousands == 0) {
            if (thousands == 0) {
                if (hundreds == 0) {
                    if (tens == 0) {
                        res = "" + ones;
                    } else {
                        res = "" + tens + ones;
                    }

                } else {
                    res = "" + hundreds + tens + ones;
                }
            } else {
                res = "" + thousands + "." + hundreds + tens;
            }
        } else {
            res = "" + tenThousands + thousands + "." + hundreds;
        }
        return res;
    }

    int getValidHeight(String height) {
        Integer heightValue = getInt(height);
        if (heightValue != null && heightValue > 999 && heightValue < 10001) {
            return heightValue.intValue();
        } else {
            int defaultNumber = 4000;
            return defaultNumber;
        }
    }

    Integer getInt(String str) {
        try {
            int number = Integer.parseInt(str);
            return number;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
