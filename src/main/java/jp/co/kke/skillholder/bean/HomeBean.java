package jp.co.kke.skillholder.bean;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import jp.co.kke.skillholder.data.DataStore;
import jp.co.kke.skillholder.model.Employee;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

/**
 * ホーム画面.
 * 
 * @author kikuta
 */
@Named(value = "homeBean")
@ViewScoped
public class HomeBean implements Serializable{

    /**
     * ユーザデータ
     */
    @Inject
    private DataStore dataStore;
    
    /**
     * ログインユーザ.
     */
    @Getter @Setter
    private Employee loginUser;
    
    /**
     * 最近の更新ユーザ.
     */
    @Getter @Setter
    private List<Employee> recent;
    
    /**
     * スキル関連図
     */
    @Getter @Setter
    private MindmapNode topNode;
    
    /**
     * スキルチャート
     */
    @Getter @Setter
    private PieChartModel pie;
    
    /**
     * 初期化
     */
    @PostConstruct
    public void init(){
        //プロトではユーザデータ1つ目の定義をログインユーザとする
        loginUser = dataStore.getEmployees().stream().findFirst().get();
        createSkillChart();
        //プロトでは最近の更新ユーザはユーザデータ全てとする
        recent = dataStore.getEmployees();
        //スキル関連図作成
        topNode = new DefaultMindmapNode(loginUser.getName(), loginUser.getName(), "FFFFFF", true);
        loginUser.getSkillRelation().stream().forEach(addNode(topNode));
    }
    
    /**
     * スキルチャート作成
     */
    private void createSkillChart(){
        pie = new PieChartModel();
        pie.setTitle("スキルチャート");
        pie.setLegendPosition("w");
        pie.setShowDataLabels(true);
        loginUser.getSkillType()
                .stream()
                .flatMap(t -> t.getSkills().stream())
                .forEach(s -> pie.set(s.getName(), s.getScore()));
    }
    
    /**
     * スキル関連図のノードクリック.
     * @param event クリックイベント
     */
    public void onNodeSelect(SelectEvent event){
        MindmapNode node = (MindmapNode) event.getObject();
        Employee clicked = dataStore.getEmployees()
                .stream()
                .filter(e -> e.getName().equals(node.getData()))
                .findFirst().get();
        clicked.getSkillRelation().stream().forEach(addNode(node));
    }
    
    /**
     * スキル関連図のノード更新.
     * @param node 更新対象ノード
     * @return Consumer
     */
    private Consumer addNode(MindmapNode node){
        return r -> node.addNode(new DefaultMindmapNode((String) r, r, "FFFFFF", true));
    }
}
