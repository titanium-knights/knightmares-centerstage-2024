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
    DistanceSensor bottom;
    DistanceSensor top;

    int distanceToFirstPixel = 40; // TODO: i put in a random value

    public Bay(HardwareMap hmap) {
        this.topLeft = hmap.servo.get(CONFIG.bayTopLeft); // i have to actually add this into the phone
        this.topRight = hmap.servo.get(CONFIG.bayTopRight);
        this.bottomLeft = hmap.servo.get(CONFIG.bayBottomLeft);
        this.bottomRight = hmap.servo.get(CONFIG.bayBottomRight);
        this.dsensor = hmap.opticalDistanceSensor.get(CONFIG.pixelDetect); // is opticalDistanceSensor right
        setInit();
    }

    public void setInit() { // is this right
        // 0 is not blocking the pixel and 1 is blocking the pixel TODO: to tune
        topLeft.setPosition(0);
        topRight.setPosition(0);
        bottomLeft.setPosition(1);
        bottomRight.setPosition(1);
    }

    public void detectFirstPixel() {
        double distance = dsensor.getDistance(DistanceUnit.MM);
        if (distance < distanceToFirstPixel) {
            topLeft.setPosition(1);
            topRight.setPosition(1);
        }
        else {
            topLeft.setPosition(0);
            topRight.setPosition(0);
        }
    }

    public void drop() {
            bottomLeft.setPosition(0);
            bottomRight.setPosition(0);
    }





}
