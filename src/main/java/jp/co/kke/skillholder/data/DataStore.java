package jp.co.kke.skillholder.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import jp.co.kke.skillholder.model.Employee;
import lombok.Getter;

/**
 * ユーザデータ.
 * 
 * JSONファイルから読み込み.
 * 
 * @author kikuta
 */
@Startup
@Singleton
public class DataStore {
    
    /**
     * ユーザ
     */
    @Getter
    private List<Employee> employees;
    
    /**
     * 初期化
     */
    @PostConstruct
    public void init(){
        createEmployees();
    }
    
    /**
     * ユーザデータ読み込み.
     */
    private void createEmployees(){
        Employee[] emp = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            FileSystem fc = FileSystems.getDefault();
            //TODO:プロトではとりあえず決め打ちのパス利用
            Path current = fc.getPath("C:\\temp\\employee.json");
            emp = mapper.readValue(current.toFile(), Employee[].class);
        } catch (IOException ex) {
            //TODO:エラー処理
        }
        employees = Arrays.stream(emp).collect(Collectors.toList());
    }
}
