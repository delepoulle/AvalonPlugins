package fr.theobessel.silkspawner;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.enchantments.*;

public class spawnerMining implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        if(player.getGameMode() != GameMode.CREATIVE) {
            if (block.getType() == Material.SPAWNER) {
                CreatureSpawner diggedSpawner = (CreatureSpawner) block.getState();
                ItemStack handItem = player.getInventory().getItemInMainHand();

                if (handItem == null) return;
                if (handItem.getType() == Material.DIAMOND_PICKAXE | handItem.getType() == Material.IRON_PICKAXE) {
                    if (handItem.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
                        ItemStack givedSpawner = new ItemStack(Material.SPAWNER, 1);
                        BlockStateMeta blockStateMeta = (BlockStateMeta) givedSpawner.getItemMeta();
                        BlockState blockState = blockStateMeta.getBlockState();
                        CreatureSpawner spawner = (CreatureSpawner) blockState;
                        spawner.setSpawnedType(diggedSpawner.getSpawnedType());
                        spawner.update();
                        blockState.update();
                        blockStateMeta.setBlockState(blockState);
                        blockStateMeta.setDisplayName(diggedSpawner.getSpawnedType().name());
                        givedSpawner.setItemMeta(blockStateMeta);

                        event.setExpToDrop(0);
                        player.getInventory().addItem(givedSpawner);
                    }
                }
            }
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        if(player.getGameMode() != GameMode.CREATIVE) {
            if (block.getType() == Material.SPAWNER) {
                CreatureSpawner spawner = (CreatureSpawner) block.getState();
                ItemStack spawnerItem = player.getInventory().getItemInMainHand();
                ItemMeta spawnerItemMeta = spawnerItem.getItemMeta();
                System.out.println(spawner.getSpawnedType().getKey() + " spawner placed");
                spawner.setCreatureTypeByName(spawnerItemMeta.getDisplayName());
                spawner.update();
            }
        }
    }
}
