package jp.co.kke.skillholder.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import jp.co.kke.skillholder.data.DataStore;
import jp.co.kke.skillholder.model.Employee;
import lombok.Getter;
import lombok.Setter;

/**
 * スキル検索画面.
 * 
 * @author kikuta
 */
@Named(value = "searchBean")
@ViewScoped
public class SearchBean implements Serializable{
    
    /**
     * 検索条件
     */
    @Getter @Setter
    private String condition;
    
    /**
     * 検索結果
     */
    @Getter @Setter
    private List<Employee> results;
    
    /**
     * ユーザデータ
     */
    @Inject
    private DataStore dataStore;
    
    /**
     * 初期化
     */
    @PostConstruct
    public void init(){
        results = dataStore.getEmployees();
    }
    
    /**
     * 検索.
     */
    public void search(){
        condition = condition.trim();
        results = dataStore.getEmployees().stream()
                .filter(e -> e.containsSkill(condition))
                .collect(Collectors.toList());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, results.size() + "人のユーザが該当しました。", ""));
    }
}
