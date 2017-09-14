package swing.downey.latte.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * Created by Paul on 2017/9/8.
 */

@AutoValue
public abstract class RgbValue {

    public abstract int green();

    public abstract int red();

    public abstract int blue();

    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }
}
