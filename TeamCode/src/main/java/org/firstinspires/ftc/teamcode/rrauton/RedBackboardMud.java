package org.firstinspires.ftc.teamcode.rrauton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.rr.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.utilities.Slides;
import org.firstinspires.ftc.teamcode.utilities.Bay;
import org.firstinspires.ftc.teamcode.utilities.Intake;
import org.firstinspires.ftc.teamcode.utilities.Stick;
import org.firstinspires.ftc.teamcode.utilities.InitialVision;

@Autonomous(name="RedBackboardMud", group="Autonomous")
public class RedBackboardMud extends ergo {

    public SampleMecanumDrive drive;
    public Stick stick;
    public InitialVision vision;
    public Slides slides;
    public Intake intake;
    public Bay bay;
    public int rot = 76; // intended to be 90 but the turn overturns it
    //TODO etc. etc., and add to the createHardware method

    public void createHardware(HardwareMap hmap) {
        drive = new SampleMecanumDrive(hmap);
        stick = new Stick(hmap);
        vision = new InitialVision(hmap, telemetry, "red"); //TODO: remember to change to blue for blue side
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
        Pose2d startPose = new Pose2d(11.5, -60, Math.toRadians(-90));
        drive.setPoseEstimate(startPose);

        drive.setPoseEstimate(startPose);

        if(isStopRequested()) return;

        int pos = vision.getPosition();

        switch (pos) {
            case 1:
                drive.followTrajectory(backToDropPixel);
                drive.turn(Math.toRadians(rot));
                drive.followTrajectory(dropPixel);
                drive.followTrajectory(forwardFromPixel);
                drive.turn(Math.toRadians(rot));
                drive.turn(Math.toRadians(rot));
                drive.followTrajectory(backOnee);
                drive.followTrajectory(toPaint);
                drive.followTrajectory(forwardFromToPaint);
                drive.followTrajectory(leftOneHalf);
                drive.followTrajectory(backOneCloseBackDrop);
                break;
            case 3:
                drive.followTrajectory(backToDropPixel);
                drive.turn(Math.toRadians(-rot));
                drive.followTrajectory(dropPixel);
                drive.followTrajectory(forwardFromPixel);
                drive.followTrajectory(rightHalf);
                drive.followTrajectory(backOne);
                drive.followTrajectory(leftOne);
                drive.followTrajectory(toPaint);
                drive.followTrajectory(forwardFromToPaint);
                drive.followTrajectory(leftOne);
                drive.followTrajectory(backOneCloseBackDrop);
                break;
            case 2:
            default:
                drive.followTrajectory(toSpotTwo);
                drive.followTrajectory(forwardFromPixel);
                drive.turn(Math.toRadians(-rot));
                drive.followTrajectory(backOnee);
                drive.followTrajectory(toPaint);
                drive.followTrajectory(forwardFromToPaint);
                drive.followTrajectory(leftOne);
                drive.followTrajectory(backOneCloseBackDrop);
                break;
        }

    }

}
