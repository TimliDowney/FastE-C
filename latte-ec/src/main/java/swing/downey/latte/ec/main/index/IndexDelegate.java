package swing.downey.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import butterknife.BindView;
import swing.downey.latte.delegates.bottom.BottomItemDelegate;
import swing.downey.latte.ec.R;
import swing.downey.latte.ec.R2;
import swing.downey.latte.ec.main.EcBottomDelegate;
import swing.downey.latte.net.CallBack.IFailure;
import swing.downey.latte.net.CallBack.ISuccess;
import swing.downey.latte.net.RestClient;
import swing.downey.latte.ui.recycler.BaseDecoration;
import swing.downey.latte.ui.recycler.MultipleFields;
import swing.downey.latte.ui.recycler.MultipleItemEntity;
import swing.downey.latte.ui.refresh.RefreshHandler;

/**
 * Created by Paul on 2017/8/31.
 */

public class IndexDelegate extends BottomItemDelegate implements View.OnFocusChangeListener {

    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.et_search_view)
    AppCompatTextView mSearchView = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;

    private RefreshHandler mRefreshHandler = null;

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
//        mRefreshHandler.firstPage("index.php");
        initRecyclerView();
        mRefreshHandler.firstPage("http://116.196.95.67/RestServer/api");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBinderView(Bundle savedInstanceState, View rootView) {
//        mRefreshHandler = new RefreshHandler(mRefreshLayout);
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());
//        RestClient.builder()
//                .url("http://116.196.95.67/RestServer/api")
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
//                        final IndexDataConverter converter = new IndexDataConverter();
//                        converter.setJsonData(response);
//                        final ArrayList<MultipleItemEntity> list = converter.convert();
//                        final String image = list.get(1).getField(MultipleFields.IMAGE_URL);
//                        Toast.makeText(getContext(),image,Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .failure(new IFailure() {
//                    @Override
//                    public void onFailure() {
//                        Toast.makeText(getContext(), "Failure when IndexDataConverter", Toast.LENGTH_LONG).show();
//                    }
//                })
//                .build()
//                .get();
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }
}
