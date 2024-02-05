package org.firstinspires.ftc.teamcode.utilities;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Bay {
    Servo bottomLeft;
    Servo bottomRight;
    DistanceSensor ds;

    int pixelDistance = 30; // TODO: tune value

    // TODO: TUNE VALUES !!
    double closedPosLeft = 0;
    double openPosLeft = 0.5;
    double closedPosRight = 1; // maybe this is supposed to be 1 and openPosRight should be 0?? idk !!
    double openPosRight = 0.5; // what if everything is backwards and my life is alie

    public Bay(HardwareMap hmap) {
        this.bottomLeft = hmap.servo.get(CONFIG.bayLeft);
        this.bottomRight = hmap.servo.get(CONFIG.bayRight);
    }

    public void open() {
        bottomLeft.setPosition(openPosLeft);
        bottomRight.setPosition(openPosRight);
    }

    public void close() {
        bottomLeft.setPosition(closedPosLeft);
        bottomRight.setPosition(closedPosRight);
    }
//    DO IN TELEOP
//    public void drop() {
//        bottomLeft.setPosition(openPosLeft);
//        bottomRight.setPosition(openPosRight);
//        // wait
//        bottomLeft.setPosition(closePosLeft);
//        bottomRight.setPosition(closePosRight);
//        // wait
//        topLeft.setPosition(openPosLeft);
//        topRight.setPosition(openPosRight);
//    }





}
