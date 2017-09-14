package swing.downey.latte.ec.main;

import android.graphics.Color;

import java.util.LinkedHashMap;

import swing.downey.latte.delegates.bottom.BaseBottomDelegate;
import swing.downey.latte.delegates.bottom.BottomItemDelegate;
import swing.downey.latte.delegates.bottom.BottomTabBean;
import swing.downey.latte.delegates.bottom.ItemBuilder;
import swing.downey.latte.ec.main.index.IndexDelegate;
import swing.downey.latte.ec.main.sort.SortDelegate;

/**
 * Created by Paul on 2017/8/31.
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setCurrentDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
