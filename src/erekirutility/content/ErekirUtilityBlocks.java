package erekirutility.content;


import erekirutility.world.blocks.distribution.NonCoreBurningDuct;
import erekirutility.world.blocks.power.PowerSaver;
import mindustry.content.Items;
import mindustry.type.*;
import mindustry.world.*;

public class ErekirUtilityBlocks {
    public static Block nonCoreBurningDuct, powerSaver;
    
    public static void load() {
        nonCoreBurningDuct = new NonCoreBurningDuct("non-core-burning-duct") {{
            requirements(Category.distribution, ItemStack.with(Items.beryllium, 1, Items.graphite, 1, Items.silicon, 1, Items.oxide, 1));
            health = 90;
            speed = 4f;
        }};
        
        powerSaver = new PowerSaver("power-saver") {{
            requirements(Category.power, ItemStack.with(Items.beryllium, 10, Items.graphite, 20, Items.silicon, 5, Items.oxide, 5));
            health = 50;
            consumesPower = outputsPower = true;
            range = 2;
            fogRadius = 1;
            consumePowerBuffered(500f);
        }};
    }
}