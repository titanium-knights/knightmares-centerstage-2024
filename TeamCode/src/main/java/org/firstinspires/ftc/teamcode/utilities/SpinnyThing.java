package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class SpinnyThing {
    Servo spinny;

    public SpinnyThing(HardwareMap hmap) { this.spinny = hmap.servo.get(CONFIG.spinny); }
    public void toDrop() { spinny.setPosition(0); }
    public void toPickUp() { spinny.setPosition(0.5); }
}
