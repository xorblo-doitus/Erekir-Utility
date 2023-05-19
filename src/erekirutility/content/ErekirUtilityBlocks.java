package erekirutility.content;


import erekirutility.world.blocks.distribution.NonCoreBurningDuct;
import mindustry.content.Items;
import mindustry.type.*;
// import mindustry.*;
import mindustry.world.*;
// import mindustry.world.blocks.distribution.*;

public class ErekirUtilityBlocks {
    public static Block nonCoreBurningDuct;
    
    public static void load() {
        nonCoreBurningDuct = new NonCoreBurningDuct("non-core-burning-duct") {{
            requirements(Category.distribution, ItemStack.with(Items.beryllium, 1, Items.graphite, 1, Items.silicon, 1, Items.oxide, 1));
            health = 90;
            speed = 4f;
        }};
    }
}