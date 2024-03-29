package org.firstinspires.ftc.teamcode.rrauton;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.rr.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.utilities.Bay;
import org.firstinspires.ftc.teamcode.utilities.InitialVision;
import org.firstinspires.ftc.teamcode.utilities.Intake;
import org.firstinspires.ftc.teamcode.utilities.Slides;
import org.firstinspires.ftc.teamcode.utilities.Stick;

@Config
@Autonomous(name="BlueSpikeAaAA", group="Autonomous")
public class BlueSpikeAaAA extends LinearOpMode {

    public SampleMecanumDrive drive;
    public Stick stick;
    public InitialVision vision;
    public Slides slides;
    public Intake intake;
    public Bay bay;
    public int M = 1;
    public int rot = 65; // intended to be 90 but the turn overturns it
    // 65-76 if full battery
    // 110 if at very low battery
    //TODO etc. etc., and add to the createHardware method

    public void createHardware(HardwareMap hmap) {
        drive = new SampleMecanumDrive(hmap);
        stick = new Stick(hmap);
        vision = new InitialVision(hmap, telemetry, "blue"); //TODO: remember to change to blue for blue side
        slides = new Slides(hmap);
        bay = new Bay(hmap);
        intake = new Intake(hmap);
    }

    @Override
    public void runOpMode() {
        createHardware(hardwareMap);
        stick.lock();
        slides.tozero();
        waitForStart();

        //TODO check this value
        Pose2d startPose = new Pose2d(11.5, 60, Math.toRadians(90));
        drive.setPoseEstimate(startPose);

        Trajectory toSpotTwo = drive.trajectoryBuilder(new Pose2d())
                .back(20*M)
                .addDisplacementMarker(this::dropPixel)
                .build();

        Trajectory backToDropPixel = drive.trajectoryBuilder(new Pose2d())
                .back(20*M)
                .build();

        Trajectory dropPixel = drive.trajectoryBuilder(new Pose2d())
                .back(6*M)
                .addDisplacementMarker(this::dropPixel)
                .build();

        Trajectory forwardFromPixel = drive.trajectoryBuilder(new Pose2d())
                .forward(8*M)
                .build();

        Trajectory forwardOne = drive.trajectoryBuilder(new Pose2d())
                .forward(27*M)
                .build();


        if(isStopRequested()) return;

        int pos = vision.getPosition();

        switch (pos) {
            case 1:
                drive.followTrajectory(forwardFromPixel);
                drive.turn(Math.toRadians(rot));
                drive.turn(Math.toRadians(rot));
                drive.followTrajectory(backToDropPixel);
                drive.turn(Math.toRadians(rot));
                drive.followTrajectory(dropPixel);
                drive.followTrajectory(forwardFromPixel);
                break;
            case 3:
                drive.followTrajectory(forwardFromPixel);
                drive.turn(Math.toRadians(rot));
                drive.turn(Math.toRadians(rot));
                drive.followTrajectory(backToDropPixel);
                drive.turn(Math.toRadians(-rot));
                drive.followTrajectory(dropPixel);
                drive.followTrajectory(forwardFromPixel);
                break;
            case 2:
            default:
                drive.followTrajectory(forwardFromPixel);
                drive.turn(Math.toRadians(rot));
                drive.turn(Math.toRadians(rot));
                drive.followTrajectory(toSpotTwo);
                drive.followTrajectory(forwardOne);
                break;
        }

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
