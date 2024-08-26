package com.example.pokemon;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.IOException;

public class Maps {

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tool.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    private TextArea displayArea;
    @FXML
    public void initialize() {
        // 定义文本内容
        String content ="准神捕捉位置一览\n"+
                        "---------------------\n"+
                        "快龙(迷你龙，哈克龙)\n"+
                        "关都沙湖乐园深处超级钓竿钓鱼或合众龙螺旋塔前钓鱼\n"+
                        "---------------------\n"+
                        "暴飞龙(宝贝龙，甲壳龙)\n"+
                        "前者建议去丰源流星瀑布，稀有概率，关都和丰源的变化洞窟也有但稀有度为非常稀有\n"+
                        "---------------------\n"+
                        "三首恶龙(单首龙，双首恶龙)\n"+
                        "前者在合众冠军之路洞内可遇(不会自杀)\n"+
                        "---------------------\n"+
                        "巨金怪(铁哑铃，金属怪)\n"+
                        "铁哑铃在丰源流星瀑布可遇，非常稀有，同时经常会有明雷铁哑铃(会猛撞自杀)\n"+
                        "后者在合众巨人洞窟后深浅草丛可遇，非常稀有\n"+
                        "---------------------\n"+
                        "班基拉斯(幼基拉斯，沙基拉斯)\n"+
                        "前者在关都七岛深处草丛捕捉，会大闹一番混乱之后自杀,后者在合众15号路草丛捕捉\n"+
                        "---------------------\n"+
                        "烈咬陆鲨(圆陆鲨，尖牙陆鲨)\n"+
                        "前者在神奥黑金市大桥下进迷幻洞窟捕捉(会猛撞自杀) \n";

        // 设置文本到显示区域
        displayArea.setText(content);
    }

}
