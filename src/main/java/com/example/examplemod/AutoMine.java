/*package com.example.examplemod;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

class LinkedRunnable {
    private ArrayList<Runnable> runnables;
    private int index;
    void add(Runnable r) {
        runnables.add(r);
    }
    boolean runNext() {
        if (runnables.size() > index) {
            runnables.get(index).run();
            index++;
            return true;
        }
        return false;
    }
    LinkedRunnable() {
        runnables = new ArrayList<>();
        index = 0;
    }
}

public class AutoMine {

    private static int id = -1;
    static void Run(int x, int y, int z) {
        PlayerBreakBlock(new BlockPos(x, y, z)).runNext();
    }
    static void MineStraightLine() {
        float pitch = Minecraft.getInstance().player.rotationPitch;
        Direction d;
        if (pitch >= -45 && pitch <= 45) d = Direction.NORTH;
        else if (pitch >= -135 && pitch <= -45) d = Direction.EAST;
        else if (pitch >= 45 && pitch <= 135) d = Direction.WEST;
        else d = Direction.SOUTH;
        //Minecraft.getInstance().gameSettings.keyBindForward.setPressed(true);
        LinkedRunnable lr = PlayerBreakBlock(Minecraft.getInstance().player.getPosition().add(0,0,1));
        lr.add(PlayerBreakBlock(Minecraft.getInstance().player.getPosition().add(0,1,1)));
        lr.runNext();

    }
    private static LinkedRunnable PlayerBreakBlock(BlockPos pos) {

        LinkedRunnable lr = PointAtLoc(new Vector3d(pos.getX()+0.5, pos.getY(), pos.getZ()+0.5));
        BlockPos bp = new BlockPos(Minecraft.getInstance().objectMouseOver.getHitVec());
        BlockState old = Minecraft.getInstance().world.getBlockState(bp);
        LinkedRunnable lr2 = new LinkedRunnable();
        lr2.setR(()->{
            float pitch = Minecraft.getInstance().player.rotationPitch;
            float yaw = Minecraft.getInstance().player.rotationYaw;

            AtomicInteger i = new AtomicInteger();
            i.getAndSet(ExampleMod.getScheduler().scheduleSyncRepeatingTask(()->{
                BlockPos new_bp = new BlockPos(Minecraft.getInstance().objectMouseOver.getHitVec());
                BlockState new_loc = Minecraft.getInstance().world.getBlockState(new_bp);
                if (old.equals(new_loc)) {
                    Minecraft.getInstance().player.rotationPitch = pitch;
                    Minecraft.getInstance().player.rotationYaw = yaw;
                    PlayerDamageBlock(Minecraft.getInstance().objectMouseOver.getHitVec());
                }
                else { //block broken, not necessarily block we want but yeah
                    ExampleMod.getScheduler().cancelTask(i.get());
                    lr2.runNext();
                }
            },0,1));
        });
        lr.setNext(lr2);
        return lr;
    }

    private static void PlayerDamageBlock(Vector3d vec) {
        int x;
        int y;
        int z;
        Direction d;
        int veryInterestingCode = (int)Math.floor(Minecraft.getInstance().player.rotationYaw * 4.0F / 360.0F) & 3;
        int lessInterestingCode = (int) Math.floor(Minecraft.getInstance().player.rotationPitch/180.f) & 1;
        switch (veryInterestingCode) {
            case 0: //South-West
                x = (int)Math.ceil(vec.x)-1; //West
                z = (int)Math.floor(vec.z); //SouthSystem.out.println("Check -1");
                break;
            case 1: //North-West
                x = (int)Math.ceil(vec.x)-1; //West
                z = (int)Math.ceil(vec.z)-1; //North
                break;
            case 2: //North-East
                x = (int)Math.floor(vec.x); //East
                z = (int)Math.ceil(vec.z)-1; //North
                break;
            case 3: //South-East
                x = (int)Math.floor(vec.x); //East
                z = (int)Math.floor(vec.z); //South
                break;
            default:
                throw new IllegalStateException("Error at PlayerDamageBlock in Main class, this shouldn't be possible.\n"
                        + "Yaw: " + veryInterestingCode + " Pitch: " + lessInterestingCode);
        }
        if (lessInterestingCode == 0) {
            y = (int)Math.ceil(vec.y)-1;
        } else {
            y = (int)Math.floor(vec.y);
        }
        BlockPos bp = new BlockPos(x, y, z);
        Minecraft.getInstance().playerController.onPlayerDamageBlock(bp, Direction.DOWN);
    }
    private static LinkedRunnable PointAtLoc(Vector3d pos) {
        LinkedRunnable lr = new LinkedRunnable();
        int ticks = 10;
        assert Minecraft.getInstance().player != null;
        Vector3d goal = pos.subtract(Minecraft.getInstance().player.getPositionVec().add(new Vector3d(0,1,0))).normalize();
        double goal_yaw = (((goal.x==0?90*(goal.z<0?-1:1):Math.atan(goal.z/goal.x)/Math.PI*180)+(goal.x<0?180:0))+270)%360; //Prevent division by zero and stuff
        double goal_pitch = (float) (-Math.asin(goal.y)/Math.PI*180);
        float old_yaw = (Minecraft.getInstance().player.rotationYaw+360)%360;
        float old_pitch = Minecraft.getInstance().player.rotationPitch;
        double temp_step_yaw = goal_yaw-old_yaw;
        if (temp_step_yaw < 0)
            temp_step_yaw += Math.abs(temp_step_yaw) < temp_step_yaw + 360 ? 0 : 360;
        else
            temp_step_yaw += temp_step_yaw < Math.abs(temp_step_yaw - 360) ? 0 : - 360;
        double step_yaw = temp_step_yaw/ticks;
        double step_pitch = (goal_pitch - old_pitch)/ticks;
        lr.setR(() -> {
            AtomicInteger i = new AtomicInteger();
            int id = ExampleMod.getScheduler().scheduleSyncRepeatingTask(()->{
                try {
                    int count = i.getAndIncrement();
                    Minecraft.getInstance().player.rotationYaw = old_yaw + (float) (step_yaw * count);
                    Minecraft.getInstance().player.rotationPitch = old_pitch + (float) (step_pitch * count);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, 0, 1);
            ExampleMod.getScheduler().scheduleSyncDelayedTask(()->{
                ExampleMod.getScheduler().cancelTask(id);
                lr.runNext(); //Execute next once this has finished
            }, ticks);
        });
        return lr;
    }

}

 */
