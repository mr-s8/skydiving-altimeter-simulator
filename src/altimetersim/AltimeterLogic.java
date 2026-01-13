package altimetersim;

public class AltimeterLogic {

    private final AltimeterState state;

    public AltimeterLogic(AltimeterState state) {
        this.state = state;
    }

    public void jump() {
        if (state.jumping)
            return;
        state.jumping = true;
        state.jumpStartTime = System.nanoTime();
        state.lastTickTime = state.jumpStartTime;
    }

    public void pull() {
        if (!state.jumping || state.pulled) // right now you cant pull after an aad fire (only aad logo would be shown), only before. a seperate reservePulled boolean would be needed
            return;
        state.pulled = true;
        state.pulledTime = System.nanoTime();
        state.canopyInflation = 0; // on button press after pull this would deflate the canopy again...
    }

    public void reset(String input) {
        state.jumping = false;
        state.jumpStartTime = 0;
        state.lastTickTime = 0;
        state.fallRate = 0;
        state.aadFired = false;
        state.pulled = false;
        state.canopyInflation = 0;
        state.pulledTime = 0;
        int h = AltimeterUtil.parseExitAltitude(input);
        state.height = h;
    }

    public void tick() {
        if (!state.jumping || state.height <= 0)
            return;

        long now = System.nanoTime();


        double dt = (now - state.lastTickTime) / 1_000_000_000.0; // Sekunden
        // double airtimeSeconds = (now - state.jumpStartTime) / 1_000_000_000.0; // not needed

        double drag;

        // calculate drag based on if the parachute has been pulled and how long it has been open
        if (state.pulled) {
            double t = (now - state.pulledTime) / 1_000_000_000.0;
            state.canopyInflation = Math.min(1.0, t / state.inflationTime);

            drag = state.bodyDrag +
                    (state.canopyDrag - state.bodyDrag) * state.canopyInflation;

        } else {
            drag = state.bodyDrag;
        }
        

        // a = g − drag * v²
        // Acceleration becomes zero when the air resistance force equals the gravitational force.
        double acceleration = state.gravity - drag * state.fallRate * state.fallRate;

        // modify fallrate with respect to the real elapsed time since last tick
        state.fallRate += acceleration * dt;

        // modify height with respect to the real elapsed time since last tick
        state.height -= state.fallRate * dt;

        if (state.height < 226 && state.fallRate > 35 && !state.aadFired) {

            // aad can still fire in opening phase -> two out
            if (!state.pulled) {
                state.pulled = true;
                state.pulledTime = System.nanoTime();
            }

            state.aadFired = true;
        }

        if (state.height <= 0) {
            state.height = 0;
        }

        state.lastTickTime = now;

    }

}
