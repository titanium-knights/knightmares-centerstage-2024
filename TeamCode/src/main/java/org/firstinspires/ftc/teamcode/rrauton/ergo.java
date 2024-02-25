package org.firstinspires.ftc.teamcode.rrauton;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.rr.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.utilities.Slides;
import org.firstinspires.ftc.teamcode.utilities.Bay;
import org.firstinspires.ftc.teamcode.utilities.InitialVision;
import org.firstinspires.ftc.teamcode.utilities.Intake;
import org.firstinspires.ftc.teamcode.utilities.Stick;


@Config
@Autonomous(name="ergo", group="Autonomous")
public class ergo extends LinearOpMode {

    public static double ANGLEONE = 0.0;
    public static double ANGLEDOS = 0;
    public static double ANGLETRES = 0;

    public static double STARTANGLE = 0;
    public static double M = 1; // multiplier

    public SampleMecanumDrive drive;
    public Stick stick;
    public InitialVision vision;
    public Slides slides;
    public Intake intake;
    public Bay bay;
    public static int rot = 70; // intended to be 90 but the turn overturns it
    // 65-76 if full battery
    // 110 if at very low battery
    //TODO etc. etc., and add to the createHardware method

    public void createHardware(HardwareMap hmap) {
        drive = new SampleMecanumDrive(hmap);
        stick = new Stick(hmap);
//        vision = new InitialVision(hmap, telemetry, "blue"); //TODO: remember to change to red for blue side
        slides = new Slides(hmap);
        bay = new Bay(hmap);
        intake = new Intake(hmap);
    }

    public Trajectory toSpotTwo;
    public Trajectory backToDropPixel;
    public Trajectory dropPixel;
    public Trajectory forwardFromPixel;
    public Trajectory backOne;
    public Trajectory backOnee;
    public Trajectory forwardOne;
    public Trajectory backThree;
    public Trajectory rightHalf;
    public Trajectory rightOne;
    public Trajectory rightOneHalf;
    public Trajectory leftOne;
    public Trajectory leftHalf;
    public Trajectory leftOneHalf;
    public Trajectory toPaint;
    public Trajectory forwardFromToPaint;
    public Trajectory rightOneCloseBackDrop;
    public Trajectory leftOneCloseBackBackDrop;
    public Trajectory backOneCloseBackDrop;
    public Trajectory forwardHalf;

    @Override
    public void runOpMode() {
        createHardware(hardwareMap);
        stick.lock();
        waitForStart();

        //TODO check this value
        Pose2d startPose = new Pose2d(-35.5, 60, Math.toRadians(STARTANGLE));
        drive.setPoseEstimate(startPose);

        toSpotTwo = drive.trajectoryBuilder(new Pose2d())
                .back(22*M)
                .addDisplacementMarker(this::dropPixel)
                .build();

        backToDropPixel = drive.trajectoryBuilder(new Pose2d())
                .back(22*M)
                .build();

        dropPixel = drive.trajectoryBuilder(new Pose2d())
                .back(8*M)
                .addDisplacementMarker(this::dropPixel)
                .build();

        forwardFromPixel = drive.trajectoryBuilder(new Pose2d())
                .forward(8*M)
                .build();
        forwardHalf = drive.trajectoryBuilder(new Pose2d())
                .forward(12*M)
                .build();

        backOne = drive.trajectoryBuilder(new Pose2d())
                .back(24*M)
                .build();

        backOnee = drive.trajectoryBuilder(new Pose2d())
                .back(36*M)
                .build();

        forwardOne = drive.trajectoryBuilder(new Pose2d())
                .forward(27*M)
                .build();

        backThree = drive.trajectoryBuilder(new Pose2d())
                .back(82*M)
                .build();
        rightHalf = drive.trajectoryBuilder(new Pose2d())
                .strafeRight(23*M)
                .build();
        rightOne = drive.trajectoryBuilder(new Pose2d()) // must be between 44 and 30
                        .strafeRight(35*M)
                                .build();
        rightOneHalf = drive.trajectoryBuilder(new Pose2d())
                .strafeRight(44*M)
                .build();

        leftOne = drive.trajectoryBuilder(new Pose2d())
                .strafeLeft(35*M)
                .build();

        leftHalf = drive.trajectoryBuilder(new Pose2d())
                .strafeLeft(23*M)
                .build();

        leftOneHalf = drive.trajectoryBuilder(new Pose2d())
                .strafeLeft(44*M)
                .build();

        toPaint = drive.trajectoryBuilder(new Pose2d())
                        .back(20*M)
                                .addDisplacementMarker(this::paintPixel)
                                                .build();
        forwardFromToPaint = drive.trajectoryBuilder(new Pose2d())
                        .forward(5*M)
                                .build();

        rightOneCloseBackDrop = drive.trajectoryBuilder(new Pose2d())
                .strafeRight(35*M)
                .build();

        leftOneCloseBackBackDrop = drive.trajectoryBuilder(new Pose2d())
                .strafeLeft(35*M)
                .build();

        backOneCloseBackDrop = drive.trajectoryBuilder(new Pose2d())
                        .back(20*M)
                                        .build();

        waitForStart();

        if(isStopRequested()) return;

        int pos = vision.getPosition();

        drive.followTrajectory(forwardFromPixel);
        drive.turn(Math.toRadians(rot));
        drive.turn(Math.toRadians(rot));
        drive.followTrajectory(toSpotTwo);
        drive.followTrajectory(forwardOne);



    }
    public void dropPixel() {
        stick.unlock();
    }
    public void paintPixel() {
        slides.low();
        sleep(500);
        bay.open();
        sleep(500);
        bay.close();
        sleep(500);
        slides.tozero();
    }

}
