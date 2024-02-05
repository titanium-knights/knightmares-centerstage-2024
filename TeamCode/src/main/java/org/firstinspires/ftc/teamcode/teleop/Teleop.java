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
        OPENED,
        CLOSED,
        TOP_FILLED // top has a pixel, bottom does not have a pixel
    }
    BayState bayState = BayState.CLOSED;

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
            case OPENED:
                if (gamepad1.a) {
                    bay.close();
                    bayState = BayState.OPENED;
                }
                break;
            case CLOSED:
                if (gamepad1.a) {
                    bay.open();
                    bayState = BayState.CLOSED;
                }
                break;
            default:
                bayState = BayState.CLOSED;
                break;
        }

        // SLIDES & INTAKE
        switch (slideState) {
            case SLIDE_BOTTOM:
                if (slides.getTarget() == 1472) { // lowheight
                    if (gamepad1.left_trigger > 0.1f) {
                        intake.runIntake();
                        telemetry.addLine("SLIDE_BOTTOM left-trigger");
                        telemetry.update();
                    }
                    if (gamepad1.right_trigger > 0.1f) {
                        slides.low();
                        intake.stopIntake();
                        slideState = SlideState.SLIDE_LOW;
                        telemetry.addLine("SLIDE_BOTTOM right-trigger");
                        telemetry.update();
                    }
                }
                break;
            case SLIDE_LOW:
                if (slides.getTarget() == 1472) { // lowheight
                    if (gamepad1.left_trigger > 0.1f) {
                        slides.tozero();
                        intake.runIntake();
                        slideState = SlideState.SLIDE_BOTTOM;
                        telemetry.addLine("SLIDE_LOW left-trigger");
                        telemetry.update();
                    }
                    if (gamepad1.right_trigger > 0.1f) {
                        slides.middle();
                        intake.stopIntake();
                        slideState = SlideState.SLIDE_MEDIUM;
                        telemetry.addLine("SLIDE_LOW right-trigger");
                        telemetry.update();
                    }
                }
                break;
            case SLIDE_MEDIUM:
                if (slides.getTarget() == 1472) { // mid height
                    if (gamepad1.left_trigger > 0.1f) {
                        slides.tozero();
                        intake.runIntake();
                        slideState = SlideState.SLIDE_BOTTOM;
                        telemetry.addLine("SLIDE_MEDIUM left-trigger");
                        telemetry.update();
                    }
                    if (gamepad1.right_trigger > 0.1f) {
                        slides.high();
                        intake.stopIntake();
                        slideState = SlideState.SLIDE_HIGH;
                        telemetry.addLine("SLIDE_MEDIUM right-trigger");
                        telemetry.update();
                    }
                }
                break;
            case SLIDE_HIGH:
                if (slides.getTarget() ==) {

                }
                if (gamepad1.left_trigger > 0.1f) {
                    slides.tozero();
                    intake.runIntake();
                    slideState = SlideState.SLIDE_BOTTOM;
                    telemetry.addLine("SLIDE_HIGH left-trigger");
                    telemetry.update();
                }
                if (gamepad1.right_trigger > 0.1f) {
                    slides.high();
                    intake.stopIntake();
                    slideState = SlideState.SLIDE_HIGH;
                    telemetry.addLine("SLIDE_HIGH right-trigger");
                    telemetry.update();
                }
                break;
            default:
                slideState = SlideState.SLIDE_BOTTOM;
                telemetry.addLine("default");
                telemetry.update();
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
