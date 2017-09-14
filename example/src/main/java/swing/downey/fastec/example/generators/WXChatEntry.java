package swing.downey.fastec.example.generators;

import com.example.annotations.EntryGenerator;

import swing.downey.latte.wechat.templates.WXEntryTemplate;

/**
 * Created by Paul on 2017/8/30.
 */
@EntryGenerator(
        packageName = "swing.downey.fastec.example",
        entryTemplete = WXEntryTemplate.class
)
public interface WXChatEntry {
}
