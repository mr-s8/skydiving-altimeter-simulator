package altimetersim;

public class AltimeterState {
    public double height = 4000;
    public double fallRate = 0;
    public boolean jumping = false;
    public boolean pulled = false;
    public boolean aadFired = false;
    public long jumpStartTime = 0;
    public long pulledTime = 0;
    public long lastTickTime = 0;
    public double gravity = 9.81;

    public double bodyDrag = 0.004;   // so that terminal velocity ist roughly at 50m/s in freefall
    public double canopyDrag = 0.4;  // so that terminal velocity ist roughly at 5m/s under canopy

    public double canopyInflation = 0;   // 0..1
    public double inflationTime = 5.0;   // Sekunden


    
}
