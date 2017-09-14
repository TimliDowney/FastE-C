package swing.downey.fastec.example.generators;

import com.example.annotations.AppRegisterGenerator;

import swing.downey.latte.wechat.templates.AppRegisterTemplate;

/**
 * Created by Paul on 2017/8/30.
 */

@AppRegisterGenerator(
        packageName = "swing.downey.fastec.example",
        registerTemplete = AppRegisterTemplate.class
)
public interface AppRegister {
}
