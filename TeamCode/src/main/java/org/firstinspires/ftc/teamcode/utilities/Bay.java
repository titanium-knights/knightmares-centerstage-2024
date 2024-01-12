package org.firstinspires.ftc.teamcode.utilities;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Bay {
    Servo topLeft;
    Servo topRight;
    Servo bottomLeft;
    Servo bottomRight;

    public Bay(HardwareMap hmap) {
        this.topLeft = hmap.servo.get(CONFIG.bayTopLeft); // i have to actually add this into the phone
        this.topRight = hmap.servo.get(CONFIG.bayTopRight);
        this.bottomLeft = hmap.servo.get(CONFIG.bayBottomLeft);
        this.bottomRight = hmap.servo.get(CONFIG.bayBottomRight);
        setInit();
    }

    public void setInit() { // do i need this
    }





}
