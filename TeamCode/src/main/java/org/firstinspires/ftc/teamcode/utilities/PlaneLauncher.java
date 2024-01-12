package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class PlaneLauncher {
    Servo launcher;

    public PlaneLauncher(HardwareMap hmap) { this.launcher = hmap.servo.get(CONFIG.planeServo); }

    public void set() { launcher.setPosition(0.12); }

    public void reset() { launcher.setPosition(0.5); }

    public void setPosition(double angle) { launcher.setPosition(angle); }

    public double getPosition() { return launcher.getPosition(); }
}
