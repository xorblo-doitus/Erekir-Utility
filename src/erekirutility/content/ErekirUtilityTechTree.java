package erekirutility.content;

import arc.struct.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.game.Objectives.*;
import mindustry.type.*;

import static mindustry.content.Blocks.*;
import static erekirutility.content.ErekirUtilityBlocks.*;


public class ErekirUtilityTechTree extends TechTree{
    /** Use own context because {@link TechTree#context} is private and has no setter */
    public static TechNode context = null;

    public static void load(){
        vanillaNode(ductUnloader, () -> {
            node(nonCoreBurningDuct);
        });
        
        vanillaNode(beamNode, () -> {
            node(powerSaver);
        });
    }
    
    // Source : https://github.com/sk7725/BetaMindy/blob/95def54d901123bab9f6279ae22ea9dffc50cf41/src/betamindy/content/MindyTechTree.java#L264
    private static void vanillaNode(UnlockableContent parent, Runnable children){
        context = TechTree.all.find(t -> t.content == parent);
        children.run();
    }
    
    /** Source {@Link TechTree} because context is private and those methods are static */
    public static TechNode node(UnlockableContent content, Runnable children){
        return node(content, content.researchRequirements(), children);
    }

    public static TechNode node(UnlockableContent content, ItemStack[] requirements, Runnable children){
        return node(content, requirements, null, children);
    }

    public static TechNode node(UnlockableContent content, ItemStack[] requirements, Seq<Objective> objectives, Runnable children){
        TechNode node = new TechNode(context, content, requirements);
        if(objectives != null){
            node.objectives.addAll(objectives);
        }

        TechNode prev = context;
        context = node;
        children.run();
        context = prev;

        return node;
    }

    public static TechNode node(UnlockableContent content, Seq<Objective> objectives, Runnable children){
        return node(content, content.researchRequirements(), objectives, children);
    }

    public static TechNode node(UnlockableContent block){
        return node(block, () -> {});
    }
}
