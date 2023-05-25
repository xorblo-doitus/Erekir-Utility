package erekirutility.world.blocks.power;

import arc.graphics.g2d.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.world.blocks.power.*;

import static arc.Core.atlas;

public class PowerSaver extends BeamNode {
    public int range = 2;
    
    public TextureRegion arrow;
    
    public PowerSaver(String name){
        super(name);
        rotate = true;
        update = true;
        rotateDraw = false;
        enableDrawStatus = false;
    }
    
    @Override
    public void load() {
        super.load();
        
        arrow = atlas.find(name + "-top");
    }
    
    public class PowerSaverBuild extends BeamNodeBuild{
        /** Multiplier of power margin to enable/disable power saving */
        @Nullable public PowerGraph source = null;
        public float margin = 20f;
        public boolean preventingFlow = false;
        
        @Override
        public void updateTile(){
            super.updateTile();
            
            if (source == null) {
                return;
            }
            
            if (preventingFlow) {
                if(source.getBatteryCapacity() <= source.getPowerProduced() * margin) {
                    preventingFlow = false;
                    updateDirections();
                }
            } else if (source.getBatteryStored() <= source.getPowerNeeded() * margin) {
                preventingFlow = true;
                unLinkNonSource();
            }
        }
        
        @Override
        public void draw(){
            super.draw();
            Draw.rect(arrow, x, y, rotdeg());
        }
        
        @Override
        public void onProximityUpdate() {
            updateDirections();
        }
        
        @Override
        public void updateDirections(){
            super.updateDirections();
            
            // Memo : {@link BeamNodeBuild#links} starting at the left and going counter-clockwise.
            // 0:left 1:bottom 2:right 3:top
            
            if (preventingFlow) {
                unLinkNonSource();
            }
            
            // Look at the back one
            @Nullable Building sourceBuilding = links[getRotated(2)];
            if (sourceBuilding == null) {
                source = null;
                return;
            }
            
            source = sourceBuilding.power().graph;
        }
        
        public int getRotated(int direction) {
            return (rotation + direction) % 4;
        }
        
        public void unLinkNonSource() {
            unLink(getRotated(0));
            unLink(getRotated(1));
            unLink(getRotated(3));
        }
        
        /** Unlink the building with index i in links
         * Taken and modified from {@link BeamNode$BeamNodeBuil#updateDirections}
         * */
        public void unLink(int i) {
            Building unlinked = links[i];
            
            if (unlinked instanceof BeamNodeBuild) {
                // Don't disconnect BeamNode as it could connect itself to PowerSaver (+ add a mechanic)
                return;
            }
            
            links[i] = null;
            dests[i] = null;
            
            if(unlinked != null){
                unlinked.power.links.removeValue(pos());
                power.links.removeValue(unlinked.pos());

                PowerGraph newgraph = new PowerGraph();
                //reflow from this point, covering all tiles on this side
                newgraph.reflow(this);

                if(unlinked.power.graph != newgraph){
                    //reflow power for other end
                    PowerGraph og = new PowerGraph();
                    og.reflow(unlinked);
                }
            }
        }
    }
}
