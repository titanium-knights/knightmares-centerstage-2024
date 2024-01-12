package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.utilities.Intake;

@Config
@TeleOp(name="DriveTrain Teleop")
public class Teleop extends OpMode {
    Intake intake;

    public void intake() {
        if (gamepad1.a) { intake.runIntake(); } // button may need to be changed

        if (gamepad1.b) { intake.stopIntake();} // button may need to be changed
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
