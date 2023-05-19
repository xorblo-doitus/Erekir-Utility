package erekirutility.world.blocks.distribution;

// import arc.Events;
// import arc.func.Cons;
import arc.util.*;
// import mindustry.game.EventType.*;
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
        // public Cons<CoreChangeEvent> coreChangeListener = (e) -> {referenceCore = team.core();};
        // public @Nullable Cons<WorldLoadEndEvent> worldLoadEndListener;
        
        // @Override
        // public void created(){
        //     super.created();
            
        //     worldLoadEndListener = (e) -> {
        //         referenceCore = team.core();
        //         Log.info("referenceCore :");
        //         Log.info(referenceCore);
        //         Events.remove(WorldLoadEndEvent.class, worldLoadEndListener);
        //         worldLoadEndListener = null;
        //     };
            
        //     Events.on(
        //         CoreChangeEvent.class,
        //         coreChangeListener
        //     );
        //     Events.on(
        //         WorldLoadEndEvent.class,
        //         worldLoadEndListener
        //     );
        //     referenceCore = team.core();
        // }
        
        // @Override
        // public void remove(){
        //     Events.remove(CoreChangeEvent.class, coreChangeListener);
        //     super.remove();
        // }
        
        public boolean coreHasSpace(Building core, Item item) {
            // @Nullable CoreBuild nextCore = next instanceof CoreBuild d ? d : null;
            
            return referenceCore == null || referenceCore.storageCapacity > referenceCore.items.get(item);
            // return referenceCore == null || referenceCore.storageCapacity > referenceCore.items.get(item);
        }
        
        // @Override
        // public void onProximityUpdate(){
        //     super.onProximityUpdate();
            
        //     if (nextc instanceof NonCoreBurningDuctBuild)
        //     nextCore = next instanceof CoreBuild d ? d : null;
        // }
        
        // Copy pasted duct's script
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
