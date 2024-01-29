package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.utilities.Intake;

@Config
@TeleOp(name="DriveTrain Teleop")
public class Teleop extends OpMode {
    Intake intake;

    public enum SlideState {
        SLIDE_BOTTOM,
        SLIDE_LOW,
        SLIDE_MEDIUM,
        SLIDE_HIGH
    };
    SlideState slideState = SlideState.SLIDE_BOTTOM;

    public void intake() {
        if (gamepad1.a) { intake.runIntake(); } // button may need to be changed

        if (gamepad1.b) { intake.stopIntake();} // button may need to be changed
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        switch(slideState) {
            case SLIDE_BOTTOM:
                // slide bottom code
                break;
            case SLIDE_LOW:
                // slide low code
                break;
            case SLIDE_MEDIUM:
                // slide medium code
                break;
            case SLIDE_HIGH:
                // slide high code
                break;
            default:
                slideState = SlideState.SLIDE_BOTTOM;
        }
    }
}
