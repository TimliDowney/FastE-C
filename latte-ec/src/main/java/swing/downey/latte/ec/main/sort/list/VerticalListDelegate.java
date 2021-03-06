package swing.downey.latte.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import swing.downey.latte.delegates.LatteDelegate;
import swing.downey.latte.ec.R;
import swing.downey.latte.ec.R2;
import swing.downey.latte.ec.main.sort.SortDelegate;
import swing.downey.latte.net.CallBack.IFailure;
import swing.downey.latte.net.CallBack.ISuccess;
import swing.downey.latte.net.RestClient;
import swing.downey.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by Paul on 2017/9/8.
 */

public class VerticalListDelegate extends LatteDelegate {

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);

    }

    @Override
    public void onBinderView(Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        String sort_list_php_imitate = "http://116.196.95.67/RestServer/data/sort_list_data.json";
        RestClient.builder()
                .url(sort_list_php_imitate)
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final List<MultipleItemEntity> data =
                                new VerticalListDataConverter().setJsonData(response).convert();
                        final SortDelegate delegate =getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data,delegate);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "FAILURE", Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }
}
