package org.sobadfish.gamedemo.room.area;

import cn.nukkit.block.Block;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sobadfish
 * 2023/1/12
 */
public class GameArea {

    public Level level;

    public int minX;

    public int maxX;

    public int minY;

    public int maxY;

    public int minZ;

    public int maxZ;

    public GameArea(Position startPosition, Position endPosition, Level level) {
        this.level = level;
        this.minX = startPosition.getFloorX();
        this.minY = startPosition.getFloorY();
        this.minZ = startPosition.getFloorZ();

        this.maxX = endPosition.getFloorX();
        this.maxY = endPosition.getFloorY();
        this.maxZ = endPosition.getFloorZ();
        sort();
    }

    private void sort(){
        int minX = Math.min(this.minX,this.maxX);
        int maxX = Math.max(this.minX,this.maxX);
        int minY = Math.min(this.minY,this.maxY);
        int maxY = Math.max(this.minY,this.maxY);
        int minZ = Math.min(this.minZ,this.maxZ);
        int maxZ = Math.max(this.minZ,this.maxZ);
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.minZ = minZ;
        this.maxZ = maxZ;
    }

    /**
     * 获取这个区域中的所有方块
     * */
    public ArrayList<Block> asValueBlocks(){
        ArrayList<Block> blocks = new ArrayList<>();
        for(int x1 = minX; x1 <= maxX; x1++){
            for(int y1 = minY; y1 <= maxY; y1++){
                for(int z1 = minZ; z1 <= maxZ; z1++){
                    Block block = level.getBlock(x1, y1, z1);
                    if(block != null && block.getId() != 0){
                        blocks.add(block);
                    }
                }
            }
        }
        return blocks;

    }

    /**
     * 检测坐标是否在区域内
     * @param position 被检测坐标
     * @return 是否在区域内
     * */
    public boolean chunkPosition(Position position){
        if(position.x >= this.minX && position.x <= this.maxX && position.y >= this.minY
                && position.y <= this.maxY && position.z >= this.minZ
                && position.z <= this.maxZ){
            return this.level != null && position.level == this.level;
        }
        return false;
    }



    public Map<String,Object> asConfigMap(){
        Map<String,Object> configMap = new HashMap<>();
        configMap.put("minX",this.minX);
        configMap.put("minY",this.minY);
        configMap.put("maxX",this.maxX);
        configMap.put("maxY",this.maxY);
        configMap.put("minZ",this.minZ);
        configMap.put("maxZ",this.maxZ);
        return configMap;
    }
}
