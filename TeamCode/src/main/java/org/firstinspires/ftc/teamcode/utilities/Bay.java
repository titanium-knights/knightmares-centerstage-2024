package org.firstinspires.ftc.teamcode.utilities;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@Config
public class Bay {
    Servo bottomLeft;
    Servo bottomRight;
    DistanceSensor ds;

    int pixelDistance = 30; // TODO: tune value

    // TODO: TUNE VALUES !!
    public static double closedPosLeft = 0.25;
    public static double openPosLeft = 1;
    public static double closedPosRight = 1;
    public static double openPosRight = 0.25;

    public Bay(HardwareMap hmap) {
        this.bottomLeft = hmap.servo.get(CONFIG.bayLeft);
        this.bottomRight = hmap.servo.get(CONFIG.bayRight);
    }

    public void open() {
        bottomLeft.setPosition(openPosLeft);
        bottomRight.setPosition(openPosRight);
    }

    public double getPosition() {
        return (bottomLeft.getPosition() + bottomRight.getPosition()) / 2.0;
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
