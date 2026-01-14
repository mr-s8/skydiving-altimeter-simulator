<div align="center">
  <p>
    <a href="https://github.com/mr-s8/skydiving-altimeter-simulator/blob/main/images/google_emoji.png"><img src="https://github.com/mr-s8/skydiving-altimeter-simulator/blob/main/images/google_emoji.png" width="250" alt="skydiving emoji" /></a>
  </p>
</div>



# skydiving-altimeter-simulator
This program simulates the behavior of an electronic altimeter during a skydive. It should help to internalize regular height checks. In later versions, you should be able to practice making decisions in emergency situations.
The design of the altimeter is based on the Viso 2+ from Larsen & Brusgaard.


## ❗ New version:
- All GUI updates now run on the Swing Event Dispatch Thread (EDT), making the application fully thread-safe and stable.
- The project now follows a clean Model-View-Controller design
- Instead of fixed per-tick updates, the simulator now uses delta-time–based physics, similar to a game engine:
  - Acceleration depends on gravity and air resistance
  - Velocity evolves continuously
  - Altitude is integrated from velocity
  - Parachute opening is modeled using changing drag over time


## ToDo
- add function to toggle between meters and feet
- allow user to turn backlight on and off
- allow user to set different freefallrates for freefly, speedfly etc
- allow user to choose aad type; also make the user turn on aad first
- add function for reserve pull (add buttons for cutaway and reserve handle; also add toggle button for RSL)
- make it a bit of a game, so that the user has to train decision making -> malfunctions; biplane/downplane situation (for example when you pull too low and aad fires)
- give feedback on screen, what went well, how long it took, what could be better etc
- show freefallrate
- ✔️ to fix: when pulling in the acceleration phase, the freefallrate goes up temporarily -> add another pull behaviour or work with percentages from current freefallrate

## Pictures
<div align="center">
  <p>
    <a href="https://github.com/mr-s8/skydiving-altimeter-simulator/blob/main/images/alti_sim.PNG"><img src="https://github.com/mr-s8/skydiving-altimeter-simulator/blob/main/images/alti_sim.PNG" alt="screenshot" /></a>
    <a href="https://github.com/mr-s8/skydiving-altimeter-simulator/blob/main/images/alti_sim_aad.PNG"><img src="https://github.com/mr-s8/skydiving-altimeter-simulator/blob/main/images/alti_sim_aad.PNG" alt="screenshot" /></a>
  </p>
</div>
