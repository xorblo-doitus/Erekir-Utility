package erekirutility.content;

import arc.struct.*;
// import arc.util.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.game.Objectives.*;
import mindustry.type.*;
// import mindustry.world.*;

import static mindustry.content.Blocks.*;
// import static mindustry.content.Items.*;
import static mindustry.content.TechTree.*;
// import static mindustry.content.UnitTypes.*;
import static erekirutility.content.ErekirUtilityBlocks.*;


public class ErekirUtilityTechTree {
    static TechTree.TechNode context = null;


    public static void load(){
        vanillaNode(ductUnloader, () -> {
            node(nonCoreBurningDuct);
        });
    }
    
    // Source : https://github.com/sk7725/BetaMindy/blob/95def54d901123bab9f6279ae22ea9dffc50cf41/src/betamindy/content/MindyTechTree.java#L264
    private static void vanillaNode(UnlockableContent parent, Runnable children){
        context = TechTree.all.find(t -> t.content == parent);
        children.run();
    }
    
    private static void node(UnlockableContent content, ItemStack[] requirements, Seq<Objective> objectives, Runnable children){
        TechNode node = new TechNode(context, content, requirements);
        if(objectives != null) node.objectives = objectives;

        TechNode prev = context;
        context = node;
        children.run();
        context = prev;
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Runnable children){
        node(content, requirements, null, children);
    }

    // private static void node(UnlockableContent content, Seq<Objective> objectives, Runnable children){
    //     node(content, content.researchRequirements(), objectives, children);
    // }

    private static void node(UnlockableContent content, Runnable children){
        node(content, content.researchRequirements(), children);
    }

    private static void node(UnlockableContent block){
        node(block, () -> {});
    }
}
