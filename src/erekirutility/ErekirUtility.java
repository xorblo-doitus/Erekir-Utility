package erekirutility;

// import arc.*;
import arc.util.*;
import erekirutility.content.*;
// import mindustry.*;
// import mindustry.content.*;
// import mindustry.game.EventType.*;
// import mindustry.gen.*;
import mindustry.mod.*;
// import mindustry.ui.dialogs.*;

public class ErekirUtility extends Mod{

    public ErekirUtility(){
        Log.info("Loaded ErekirUtility constructor.");

        //listen for game load event
        // Events.on(ClientLoadEvent.class, e -> {
        //     //show dialog upon startup
        //     Time.runTask(10f, () -> {
        //         BaseDialog dialog = new BaseDialog("frog");
        //         dialog.cont.add("behold").row();
        //         //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
        //         dialog.cont.image(Core.atlas.find("example-java-mod-frog")).pad(20f).row();
        //         dialog.cont.button("I see", dialog::hide).size(100f, 50f);
        //         dialog.show();
        //     });
        // });
    }

    @Override
    public void loadContent(){
        Log.info("Loading ErekirUtility's blocks.");
        ErekirUtilityBlocks.load();
        Log.info("Loaded ErekirUtility's blocks.");
        
        Log.info("Loading ErekirUtility's tech tree.");
        ErekirUtilityTechTree.load();
        Log.info("Loaded ErekirUtility's tech tree.");
    }

}
