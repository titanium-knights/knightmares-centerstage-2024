package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.utilities.SimpleMecanumDrive;

@Config
@TeleOp(name="Demo Teleop")
public class Demo extends OpMode {
//    Slides slides;
//    Intake intake;
//    PullUp pullup;
//    PlaneLauncher plane;
    SimpleMecanumDrive drive;
//    Bay bay;

    //Set normal power constant to 1, no point in slowing the robot down
    final double normalPower = 1;

    // in case of joystick drift, ignore very small values
    final float STICK_MARGIN = 0.5f;

//    //Prevents irreversible things such as pullup and plane launcher from running before this button is pressed
//    boolean validate = false;

//    //makes validate button have to be pressed for a while before features enabled
//    int validatecount = 0;

//    double bayBuffer = 0.05;

//    public enum SlideState {
//        SLIDE_BOTTOM,
//        SLIDE_LOW,
//        SLIDE_MEDIUM,
//        SLIDE_HIGH
//    };
//    SlideState slideState = SlideState.SLIDE_BOTTOM;

//    public enum PullUpState {
//        NEUTRAL,
//        REACH_UP
//    }
//    PullUpState pullupstate = PullUpState.REACH_UP;
//
//    public enum BayState {
//        OPENED,
//        CLOSED,
//        TOP_FILLED // top has a pixel, bottom does not have a pixel
//    }
//    BayState bayState = BayState.CLOSED;

    ElapsedTime elapsedTime;

    enum ButtonPressState {
        PRESSED_GOOD, //the first time we see the button pressed
        DEPRESSED, //you haven't let go
        UNPRESSED // its not pressed
    }
//    ButtonPressState baybutton;
//    ButtonPressState intakeButton;
//    ButtonPressState slideButton;
//
//    ButtonPressState slideManual;
//    ButtonPressState slideManualUp;

//    boolean intakeRunning = false;
    @Override
    public void init() {
        this.drive = new SimpleMecanumDrive(hardwareMap);
//        this.bay = new Bay(hardwareMap);
//        this.slides = new Slides(hardwareMap);
//        this.pullup = new PullUp(hardwareMap);
//        this.plane = new PlaneLauncher(hardwareMap);
//        this.intake = new Intake(hardwareMap);
//        this.baybutton = ButtonPressState.UNPRESSED;
//        this.intakeButton = ButtonPressState.UNPRESSED;
//        this.slideButton = ButtonPressState.UNPRESSED;
//        this.slideManual = ButtonPressState.UNPRESSED;
//        this.slideManualUp = ButtonPressState.UNPRESSED;
    }

    @Override
    public void loop() {
        //VALIDATE
//        if (gamepad1.dpad_up) {++validatecount;}
//        if (validatecount > 5) {validate = true;}
//
//        if (gamepad1.b && baybutton == ButtonPressState.UNPRESSED) {
//            baybutton = ButtonPressState.PRESSED_GOOD;
//        } else if (gamepad1.b && baybutton==ButtonPressState.PRESSED_GOOD) {
//            baybutton = ButtonPressState.DEPRESSED;
//        } else if (!gamepad1.b) baybutton = ButtonPressState.UNPRESSED;
//
//        if (gamepad1.left_trigger>0.1f && slideButton == ButtonPressState.UNPRESSED) {
//            slideButton = ButtonPressState.PRESSED_GOOD;
//        } else if (gamepad1.left_trigger>0.1f && slideButton==ButtonPressState.PRESSED_GOOD) {
//            slideButton = ButtonPressState.DEPRESSED;
//        } else if (gamepad1.left_trigger<0.1f) slideButton = ButtonPressState.UNPRESSED;
//
//        if (gamepad1.right_trigger > 0.1f && intakeButton==ButtonPressState.UNPRESSED) {
//            intakeButton = ButtonPressState.PRESSED_GOOD;
//        } else if (gamepad1.right_trigger > 0.1f && intakeButton==ButtonPressState.PRESSED_GOOD) {
//            intakeButton = ButtonPressState.DEPRESSED;
//        } else if (gamepad1.right_trigger < 0.1f) intakeButton=ButtonPressState.UNPRESSED;
//
//        if (intakeButton==ButtonPressState.PRESSED_GOOD && intakeRunning) {
//            intake.stopIntake();
//            intakeRunning = false;
//        } else if (intakeButton==ButtonPressState.PRESSED_GOOD) {
//            intakeRunning = true;
//            intake.runIntake();
//        }

//        if (gamepad1.right_bumper && slideManual == ButtonPressState.UNPRESSED) {
//            slideManual = ButtonPressState.PRESSED_GOOD;
//        } else if (gamepad1.right_bumper && slideManual==ButtonPressState.PRESSED_GOOD) {
//            slideManual = ButtonPressState.DEPRESSED;
//        } else if (!gamepad1.right_bumper) slideManual = ButtonPressState.UNPRESSED;
//
//        if (gamepad1.left_bumper && slideManualUp == ButtonPressState.UNPRESSED) {
//            slideManualUp = ButtonPressState.PRESSED_GOOD;
//        } else if (gamepad1.left_bumper && slideManualUp==ButtonPressState.PRESSED_GOOD) {
//            slideManualUp = ButtonPressState.DEPRESSED;
//        } else if (!gamepad1.left_bumper) slideManualUp = ButtonPressState.UNPRESSED;
//
//        if (gamepad1.right_bumper){//slideManual==ButtonPressState.PRESSED_GOOD) {
//            slides.downHold();
//        } else if (gamepad1.left_bumper){//slideManualUp==ButtonPressState.PRESSED_GOOD) {
//            slides.upHold();
//        } else {
//            slides.stop();
        }

        //DRIVE
        float x = gamepad1.left_stick_x;
        float y = gamepad1.left_stick_y;
        float turn = gamepad1.right_stick_x;
        moveDrivetrain(x, -y, turn);

        // BAY
//        double pos = bay.getPosition();
//
//        if (gamepad1.b) {
//            bay.close();
//        }
//        if (gamepad1.a) {
//            bay.open();
//        }

//        switch (bayState) {
//            case OPENED:
//                if (Math.abs(bay.getPosition() - Bay.openPosLeft) < 0.05 && baybutton==ButtonPressState.PRESSED_GOOD) {
//                    bay.close();
//                    bayState = BayState.CLOSED;
//                    telemetry.addLine("bayState" + bayState);
//                    telemetry.update();
//                    telemetry.addLine("pos " + pos);
//                    telemetry.update();
//                }
//                telemetry.addLine("OPENED");
//                telemetry.update();
//                break;
//
//            case CLOSED:
//                if (Math.abs(bay.getPosition() - Bay.closedPosLeft) < 0.05 && baybutton==ButtonPressState.PRESSED_GOOD) {
//                    bay.open();
//                    bayState = BayState.OPENED;
//                    telemetry.addLine("bayState" + bayState);
//                    telemetry.update();
//                    telemetry.addLine("pos " + pos);
//                    telemetry.update();
//                }
//
//                double position = Math.abs(bay.getPosition() - 0);
//                telemetry.addLine("position " + position);
//                telemetry.addLine("pressed? " + baybutton.toString());
//                telemetry.update();
//                break;
//            default:
//                bayState = BayState.CLOSED;
//                telemetry.addLine("bayState" + bayState);
//                telemetry.update();
//        }

        // SLIDES & INTAKE
//        switch (slideState) {
//            case SLIDE_BOTTOM:
//                if (Math.abs(slides.getEncoder() - 0) < 10) { // drop height
//                    if (slideButton==ButtonPressState.PRESSED_GOOD) {
//                        slideState = SlideState.SLIDE_HIGH;
//                        telemetry.addData("pos", slides.getEncoder());
//                        telemetry.update();
//                        slides.high();
//                    }
//                }
//                break;
//            case SLIDE_LOW:
//                telemetry.addData("u shouldnt see this lol but encoder position", slides.getEncoder());
//                telemetry.update();
//                if (Math.abs(slides.getEncoder() - 10) < 10) { // lowheight, changed 1400 something to 100
//                    if (gamepad1.left_trigger > 0.1f) {
//                        slides.tozero();
//                        intake.runIntake();
//                        slideState = SlideState.SLIDE_BOTTOM;
//                        telemetry.addData("pos", slides.getEncoder());
//                        telemetry.update();
//                    }
//                    if (gamepad1.right_trigger > 0.1f) {
//                        slides.middle();
//                        intake.stopIntake();
//                        slideState = SlideState.SLIDE_MEDIUM;
//                        telemetry.addData("pos", slides.getEncoder());
//                        telemetry.update();
//                    }
//                }
//                break;
//            case SLIDE_MEDIUM:
//                if (Math.abs(slides.getEncoder() - 20) < 10) { // mid height 2424
//                    if (gamepad1.left_trigger > 0.1f) {
//                        slides.tozero();
//                        intake.runIntake();
//                        slideState = SlideState.SLIDE_BOTTOM;
//                        telemetry.addData("pos", slides.getEncoder());
//                        telemetry.update();
//                    }
//                    if (gamepad1.right_trigger > 0.1f) {
//                        slides.high();
//                        intake.stopIntake();
//                        slideState = SlideState.SLIDE_HIGH;
//                        telemetry.addData("pos", slides.getEncoder());
//                        telemetry.update();
//                    }
//                }
//                break;
//            case SLIDE_HIGH:
//                if (Math.abs(slides.getEncoder() - 30) < 10) { // high height 3481
//                    if (slideButton==ButtonPressState.PRESSED_GOOD) {
//                        slides.tozero();
//                        slideState = SlideState.SLIDE_BOTTOM;
//                        telemetry.addData("pos", slides.getEncoder());
//                        telemetry.update();
//                    }
//                }
//                break;
//            default:
//                slideState = SlideState.SLIDE_BOTTOM;
//                telemetry.addLine("default");
//                telemetry.update();
//        }
//
//        //PULL UP
//        if (gamepad1.dpad_up) {
//            pullup.manualLeftUp();
//            pullup.manualRightUp();
//            pullupstate = PullUpState.REACH_UP;
//        }
//        if (gamepad1.dpad_down) {
//            pullup.manualLeftDown();
//            pullup.manualRightDown();
//            pullupstate = PullUpState.NEUTRAL;
//        }
//        switch (pullupstate) {
//            case NEUTRAL:
//                if (gamepad1.x && gamepad1.dpad_up) {
//                    pullup.manualLeftUp();
//                    pullup.manualRightUp();
//                    pullupstate = PullUpState.REACH_UP;
//                }
//                break;
//            case REACH_UP:
//                telemetry.addData("pullup1pos", + pullup.getPosition1());
//                telemetry.addData("pullup2pos", + pullup.getPosition2());
//                telemetry.update();
//                if (gamepad1.dpad_up) {
//                    pullup.manualLeftDown();
//                    pullup.manualRightDown();
//                    pullupstate = PullUpState.NEUTRAL;
//                }
//                break;
//            default:
//                pullupstate = PullUpState.NEUTRAL;
//        }

        //PLANE LAUNCHER
//        if (gamepad1.y) {
//            plane.reset();
//            telemetry.addData("pos: ", plane.getPosition());
//            telemetry.update();
//        }
//    }

    public void moveDrivetrain(float x, float y, float turn) {
        // if the stick movement is negligible, set STICK_MARGIN to 0
        if (Math.abs(x) <= STICK_MARGIN) x = .0f;
        if (Math.abs(y) <= STICK_MARGIN) y = .0f;
        if (Math.abs(turn) <= STICK_MARGIN) turn = .0f;

        //Notation of a ? b : c means if a is true do b, else do c.
        double multiplier = normalPower;
        drive.move(x * multiplier, y * multiplier, -turn * multiplier);

    }
}
