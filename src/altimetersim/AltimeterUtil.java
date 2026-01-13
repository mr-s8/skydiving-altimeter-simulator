package altimetersim;

public class AltimeterUtil {

    public static String getDisplayHeight(int heightValue) {
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

    public static int parseExitAltitude(String input) {
        try {
            int h = Integer.parseInt(input);
            if (h >= 1000 && h <= 10000)
                return h;
        } catch (Exception e) {
        }
        return 4000;
    }
}
