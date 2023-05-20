package erekirutility.world.blocks.distribution;

import arc.util.*;
import mindustry.gen.Building;
import mindustry.type.Item;
import mindustry.world.blocks.distribution.Duct;
import mindustry.world.blocks.storage.CoreBlock.CoreBuild;

public class NonCoreBurningDuct extends Duct{
    
    public NonCoreBurningDuct(String name){
        super(name);
    }
    
    public class NonCoreBurningDuctBuild extends DuctBuild{
        public @Nullable CoreBuild referenceCore;
        
        public boolean coreHasSpace(Building core, Item item) {
            return referenceCore == null || referenceCore.storageCapacity > referenceCore.items.get(item);
        }
        
        /** Modified {@Link Duct.DuctBuild#updateTile} to stop moving when core is full */
        @Override
        public void updateTile(){
            progress += edelta() / speed * 2f;
            
            if(current != null && next != null){
                referenceCore = team.core();
                if(progress >= (1f - 1f/speed) && coreHasSpace(next, current) && moveForward(current)){
                    items.remove(current, 1);
                    current = null;
                    progress %= (1f - 1f/speed);
                }
            }else{
                progress = 0;
            }

            if(current == null && items.total() > 0){
                current = items.first();
            }
        }
    }
}
