package org.firstinspires.ftc.teamcode.utilities;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Bay {
    Servo topLeft;
    Servo topRight;
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
        this.topLeft = hmap.servo.get(CONFIG.bayTopLeft); // i have to actually add this into the phone
        this.topRight = hmap.servo.get(CONFIG.bayTopRight);
        this.bottomLeft = hmap.servo.get(CONFIG.bayBottomLeft);
        this.bottomRight = hmap.servo.get(CONFIG.bayBottomRight);
        this.ds = hmap.opticalDistanceSensor.get(CONFIG.distSensor);
    }

    public double distance() {
        return ds.getDistance(DistanceUnit.MM);
    }

    public void detectFirst() {
        if (distance() > pixelDistance) {
            bottomLeft.setPosition(closedPosLeft);
            bottomRight.setPosition(closedPosRight);
        }
        if (distance() < pixelDistance) {
            topLeft.setPosition(closedPosLeft);
            topRight.setPosition(closedPosRight);
        }
    }

    public void openTop() {
        topLeft.setPosition(openPosLeft);
        topRight.setPosition(openPosRight);
    }

    public void openBottom() {
        bottomLeft.setPosition(openPosLeft);
        bottomRight.setPosition(openPosRight);
    }

    public void closeTop() {
        topLeft.setPosition(closedPosLeft);
        topRight.setPosition(closedPosRight);
    }

    public void closeBottom() {
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
