package org.firstinspires.ftc.teamcode.utilities;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@Config
public class Stick {
    Servo stickRot;
    public static double lockPos = 0.4;
    public static double op = 0;

    public Stick(HardwareMap hmap) {
        this.stickRot = hmap.servo.get(CONFIG.stick);
    }

    public void lock() {
        stickRot.setPosition(lockPos); //TODO: tune the value pls
    }
    public void unlock() {
        stickRot.setPosition(op); //TODO: pls tune the value
    }
}
