package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class PullUp {
    Servo leftServo;
    Servo rightServo;
    // servo rotation value for when both servos rotate upwards
    public int UP_VALUE = 3;
    public PullUp(HardwareMap hmap) {
        this.leftServo = hmap.servo.get(CONFIG.pullUpLeftServo);
        this.rightServo = hmap.servo.get(CONFIG.pullUpRightServo);
    }

    // method to make rotators move up to that position
    public void rotateUp() {
        leftServo.setPosition(UP_VALUE);
        rightServo.setPosition(UP_VALUE);
    }

    public void reset() {

    }
}
