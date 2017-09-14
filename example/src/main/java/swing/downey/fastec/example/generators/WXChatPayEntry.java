package swing.downey.fastec.example.generators;

import com.example.annotations.EntryGenerator;
import com.example.annotations.PayEntryGenerator;

import swing.downey.latte.wechat.templates.WXPayEntryTemplate;

/**
 * Created by Paul on 2017/8/30.
 */

@PayEntryGenerator(
        packageName = "swing.downey.fastec.example",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WXChatPayEntry {
}
