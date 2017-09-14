package swing.downey.latte.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * Created by Paul on 2017/9/4.
 */

public class MultipleItemEntity implements MultiItemEntity {

    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUENE = new ReferenceQueue<>();
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELD = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object, Object>> FIELD_REFERENCE =
            new SoftReference<>(MULTIPLE_FIELD, ITEM_QUENE);

    MultipleItemEntity(LinkedHashMap<Object,Object> fields){
        FIELD_REFERENCE.get().putAll(fields);
    }

    public static MultipleEntityBuilder builder(){
        return new MultipleEntityBuilder();
    }

    @Override
    public int getItemType() {
        return (int) FIELD_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    @SuppressWarnings("unchecked")
    public final <T> T getField(Object key) {
        return (T) FIELD_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?,?> getField(){
        return FIELD_REFERENCE.get();
    }

    public final MultiItemEntity setField(Object key, Object value){
        FIELD_REFERENCE.get().put(key,value);
            return this;
    }
}
