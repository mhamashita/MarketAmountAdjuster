package net.neweconomy;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ExchangeCommandListener implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandSendEvent e){
        //TODO 複数個同時交換する場合のものも書く
        //TODO 大文字小文字を区別しないようにする
        var sent = e.getCommands().toArray();
        var idx = 0;
        if(sent.length!=2){
            //TODO 例外処理
            return;
        }
        if(sent[idx]!="exchange"){
            //TODO 例外処理
            return;
        }
        idx++;
        if(sent[idx]!="limited"){
            return;
        }
        Inventory container = e.getPlayer().getEnderChest();
        var cost = getCost();
        if(hasEnoughCost(container,cost)){
            withdrawCost(container,cost);
            giveReward();
        }
    }

    private HashMap<Material,Integer> getCost(){
        HashMap<Material,Integer> ans = new HashMap<Material,Integer>();
        ans.put(Material.STONE,1000);
        return ans;
    }

    private boolean hasEnoughCost(Inventory inv, HashMap<Material,Integer> cost){
        HashMap<Material,Integer> stock = new HashMap<Material,Integer>();
        var contents = inv.getContents();
        for(var a:contents){
            Material mat =a.getType();

            //TODO 余分なメタデータをもつアイテムをカウントしない
            String s = a.getItemMeta().getAsString();

            var stack_amount = a.getAmount();
            var stock_amount = 0;
            stock.putIfAbsent(mat,0);
            stock_amount = stock.get(mat);

            stock_amount += stack_amount;

            stock.put(mat,stock_amount);
        }

        //数え上げ終了

        //比較
        var stock_set = stock.entrySet();
        boolean ok = true;
        for(var a: stock_set){
            var mat = a.getKey();
            var stock_amount = a.getValue();
            var cost_amount = cost.get(mat);
            if(stock_amount<cost_amount){
                ok=false;
            }
        }

        return ok;
    }

    private void withdrawCost(Inventory container, HashMap<Material,Integer> cost){
        //TODO 量が足りない時に例外処理をする

        //どのスロットに指定されたアイテムが何個あるか数える
        HashMap<Material, TreeMap<Integer,Integer>> memo;
        var content_array = container.getStorageContents();
        for(int i = 0;i<content_array.length;++i){
            var a = content_array[i];
            var amount = a.getAmount();
            var item = a.getType();
            var slot = i;
        }
        //スロット番号の昇順にmin(cost,アイテム数)分の減らしていくべきアイテムの数をメモする
        HashMap<Material,TreeMap<Integer,Integer>> delete_map;
        for(var ent: cost.entrySet()){
            var item = ent.getKey();
            var cost_amount = ent.getValue();

            while(cost_amount>0){

            }
        }
        //実際にアイテムを減らす処理を行う
    }
}
