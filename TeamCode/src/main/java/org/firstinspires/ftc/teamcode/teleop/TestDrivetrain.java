package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.utilities.SimpleMecanumDrive;

@Config
@TeleOp(name="DriveTrain only Teleop")
public class TestDrivetrain extends OpMode {
    SimpleMecanumDrive drive;

    //Set normal power constant to 1, no point in slowing the robot down
    final double normalPower = 1;

    // in case of joystick drift, ignore very small values
    final float STICK_MARGIN = 0.5f;

    @Override
    public void init() {
        this.drive = new SimpleMecanumDrive(hardwareMap);
    }

    @Override
    public void loop() {
        //DRIVE
        float x = gamepad1.left_stick_x;
        float y = gamepad1.left_stick_y;
        float turn = gamepad1.right_stick_x;
        move(-x, y, turn);
    }

    public void move(float x, float y, float turn) {
        // if the stick movement is negligible, set STICK_MARGIN to 0
        if (Math.abs(x) <= STICK_MARGIN) x = .0f;
        if (Math.abs(y) <= STICK_MARGIN) y = .0f;
        if (Math.abs(turn) <= STICK_MARGIN) turn = .0f;

        //Notation of a ? b : c means if a is true do b, else do c.
        double multiplier = normalPower;

        if (gamepad1.dpad_up) {
            drive.moveBR(x * multiplier, y * multiplier, -turn * multiplier);
        }
        if (gamepad1.dpad_down) {
            drive.moveBL(x * multiplier, y * multiplier, -turn * multiplier);
        }
        if (gamepad1.dpad_right) {
            drive.moveFR(x * multiplier, y * multiplier, -turn * multiplier);
        }
        if (gamepad1.dpad_left) {
            drive.moveFL(x * multiplier, y * multiplier, -turn * multiplier);
        }

    }
}
