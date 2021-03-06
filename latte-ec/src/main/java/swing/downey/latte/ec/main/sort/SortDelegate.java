package swing.downey.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import swing.downey.latte.delegates.bottom.BottomItemDelegate;
import swing.downey.latte.ec.R;
import swing.downey.latte.ec.main.sort.content.ContentDelegate;
import swing.downey.latte.ec.main.sort.list.VerticalListDelegate;

/**
 * Created by Paul on 2017/8/31.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBinderView(Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        loadRootFragment(R.id.vertical_list_container,listDelegate);
//        replaceLoadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }
}
