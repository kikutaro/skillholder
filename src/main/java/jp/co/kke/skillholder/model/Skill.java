package jp.co.kke.skillholder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * スキル.
 * @author kikuta
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Skill {
    /**
     * スキル名
     */
    private String name;
    
    /**
     * スコア
     */
    private int score;
}
