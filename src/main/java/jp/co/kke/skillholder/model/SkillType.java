package jp.co.kke.skillholder.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * スキルタイプ.
 * @author kikuta
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class SkillType {
    /**
     * スキルタイプ
     */
    private String type;
    
    /**
     * スキルリスト
     */
    private List<Skill> skills;
}
