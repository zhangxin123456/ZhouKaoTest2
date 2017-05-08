package jiyun.com.zhoukaotest2;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;


public class GreenDaoManager {
    public static void main(String[] args) throws Exception {
        // 第一个参数为数据库版本
        //第二个参数为数据库的包名
        Schema schema = new Schema(1, "News.db");
        // 创建表,参数为表名
        Entity entity = schema.addEntity("Journalism");
        // 为表添加字段
        entity.addIdProperty();// 该字段为id
        entity.addStringProperty("title");// String类型字段
        new DaoGenerator().generateAll(schema, "C:\\Users\\lx\\Desktop\\workspace2\\ZhouKaoTest2\\app\\src\\main\\java");
    }
}
