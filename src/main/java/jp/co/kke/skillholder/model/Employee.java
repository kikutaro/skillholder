package jp.co.kke.skillholder.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * ユーザ(所員).
 * @author kikuta
 */
@Getter @Setter
public class Employee {
    
    /**
     * 名前
     */
    private String name;
    
    /**
     * 保有スキル
     */
    private List<SkillType> skillType;
    
    /**
     * スキル相関
     */
    private List<String> skillRelation;
    
    /**
     * プロフィール画像
     */
    private String img;
    
    /**
     * 指定されたスキルキーワードの該当有無. 
     * @param keyword スキルのキーワード
     * @return 該当有無.
     */
    public boolean containsSkill(String keyword){
        return skillType.stream().flatMap(st -> st.getSkills().stream())
                .anyMatch(s -> s.getName().contains(keyword));
    }
}
