package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.utilities.Intake;
import org.firstinspires.ftc.teamcode.utilities.Slides;
import org.firstinspires.ftc.teamcode.utilities.PullUp;
import org.firstinspires.ftc.teamcode.utilities.PlaneLauncher;
import org.firstinspires.ftc.teamcode.utilities.SimpleMecanumDrive;
import org.firstinspires.ftc.teamcode.utilities.Bay;

@Config
@TeleOp(name="DriveTrain Teleop")
public class Teleop extends OpMode {
    Slides slides;
    Intake intake;
    PullUp pullup;
    PlaneLauncher plane;
    SimpleMecanumDrive drive;
    Bay bay;

    //Set normal power constant to 1, no point in slowing the robot down
    final double normalPower = 1;

    // in case of joystick drift, ignore very small values
    final float STICK_MARGIN = 0.5f;

    //Prevents irreversible things such as pullup and plane launcher from running before this button is pressed
    boolean validate = false;

    //makes validate button have to be pressed for a while before features enabled
    int validatecount = 0;

    public enum SlideState {
        SLIDE_BOTTOM,
        SLIDE_LOW,
        SLIDE_MEDIUM,
        SLIDE_HIGH
    };
    SlideState slideState = SlideState.SLIDE_BOTTOM;

    public enum PullUpState {
        NEUTRAL,
        REACH_UP
    }
    PullUpState pullupstate = PullUpState.NEUTRAL;

    public enum BayState {
        EMPTY,
        BOTTOM_FILLED, // bottom has a pixel, top does not have a pixel
        FILLED,
        TOP_FILLED // top has a pixel, bottom does not have a pixel
    }
    BayState bayState = BayState.EMPTY;

    @Override
    public void init() {
        this.drive = new SimpleMecanumDrive(hardwareMap);
        this.bay = new Bay(hardwareMap);
        this.slides = new Slides(hardwareMap);
        this.pullup = new PullUp(hardwareMap);
        this.plane = new PlaneLauncher(hardwareMap);
        this.intake = new Intake(hardwareMap);
    }

    @Override
    public void loop() {
        //VALIDATE
        if (gamepad1.x) {++validatecount;}
        if (validatecount > 5) {validate = true;}

        //DRIVE
        float x = gamepad1.left_stick_x;
        float y = gamepad1.left_stick_y;
        float turn = gamepad1.right_stick_x;
        move(-x, y, turn);

        // BAY
        switch (bayState) {
            case EMPTY:
                bay.openTop();
                bay.closeBottom();
                if (???) { // TODO: if distance sensor senses that bottom has pixel
                    bayState = BayState.BOTTOM_FILLED;
                }
                break;
            case FILLED:
                if (gamepad1.a) {
                    bay.openBottom();
                    bayState = BayState.TOP_FILLED;
                }
                break;
            case BOTTOM_FILLED:
                bay.closeTop();
                if (gamepad1.a) {
                    bay.openBottom();
                    bay.openTop();
                    bayState = BayState.EMPTY;
                }
                if (???) { // TODO: if distance sensor senses that top has pixel
                    bayState = BayState.FILLED;
                }
                break;
            case TOP_FILLED:
                if (gamepad1.a) {
                    bay.openTop();
                    bayState = BayState.EMPTY;
                }
                break;
            default:
                bayState = BayState.EMPTY;
                break;
        }

        // SLIDES & INTAKE
        switch (slideState) {
            case SLIDE_BOTTOM:
                if (gamepad1.left_trigger > 0.1f) {
                    intake.runIntake();
                }
                if (gamepad1.right_trigger > 0.1f) {
                    slides.low();
                    intake.stopIntake();
                    slideState = SlideState.SLIDE_LOW;
                }
                break;
            case SLIDE_LOW:
                // slide low code
                if (gamepad1.left_trigger > 0.1f) {
                    slides.tozero();
                    intake.runIntake();
                    slideState = SlideState.SLIDE_BOTTOM;
                }
                if (gamepad1.right_trigger > 0.1f) {
                    slides.middle();
                    intake.stopIntake();
                    slideState = SlideState.SLIDE_MEDIUM;
                }
                break;
            case SLIDE_MEDIUM:
                if (gamepad1.left_trigger > 0.1f) {
                    slides.tozero();
                    intake.runIntake();
                    slideState = SlideState.SLIDE_BOTTOM;
                }
                if (gamepad1.right_trigger > 0.1f) {
                    slides.high();
                    intake.stopIntake();
                    slideState = SlideState.SLIDE_HIGH;
                }
                break;
            case SLIDE_HIGH:
                if (gamepad1.left_trigger > 0.1f) {
                    slides.tozero();
                    intake.runIntake();
                    slideState = SlideState.SLIDE_BOTTOM;
                }
                break;
            default:
                slideState = SlideState.SLIDE_BOTTOM;
        }

        //PULL UP
        switch (pullupstate) {
            case NEUTRAL:
                if (gamepad1.x && gamepad1.dpad_up) {
                    pullup.manualLeftUp();
                    pullup.manualRightUp();
                    pullupstate = PullUpState.REACH_UP;
                }
                break;
            case REACH_UP:
                if (gamepad1.dpad_up) {
                    pullup.manualLeftDown();
                    pullup.manualRightDown();
                    pullupstate = PullUpState.NEUTRAL;
                }
                break;
            default:
                pullupstate = PullUpState.NEUTRAL;
        }

        //PLANE LAUNCHER
        if (gamepad1.x && gamepad1.dpad_right) {
            plane.reset();
            telemetry.addData("pos: ", plane.getPosition());
            telemetry.update();
        }
    }

    public void move(float x, float y, float turn) {
        // if the stick movement is negligible, set STICK_MARGIN to 0
        if (Math.abs(x) <= STICK_MARGIN) x = .0f;
        if (Math.abs(y) <= STICK_MARGIN) y = .0f;
        if (Math.abs(turn) <= STICK_MARGIN) turn = .0f;

        //Notation of a ? b : c means if a is true do b, else do c.
        double multiplier = normalPower;
        drive.move(x * multiplier, y * multiplier, -turn * multiplier);

    }
}
