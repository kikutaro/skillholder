package jp.co.kke.skillholder.bean;

import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.Setter;

/**
 * 画面テーマ情報.
 * @author kikuta
 */
@Named(value = "themeBean")
@ApplicationScoped
public class ThemeBean {
    
    /**
     * 選択されたテーマ
     */
    @Getter @Setter
    private String theme;
    
    /**
     * 選択可能なテーマ
     */
    @Getter
    private List<String> themes;
    
    /**
     * 初期化
     */
    @PostConstruct
    public void init(){
        theme = "redmond";
        themes = Arrays.asList("blitzer","bootstrap","cruze","hot-sneaks","pepper-grinder","redmond","ui-lightness");
    }
}
