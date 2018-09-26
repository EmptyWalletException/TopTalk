import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: MasterCV
 * @Date: Created in  2018/9/26 20:38
 */
public class MongoDBTest {

    private String host = "127.0.0.0";
    private String datebaseName = "spitdb";
    private String documentName = "spit";

    public static void main(String[] args) {
        MongoDBTest mongoDBTest = new MongoDBTest();
            mongoDBTest.test01();
    }

    private void test01() {
        MongoClient client = new MongoClient(host);//创建连接
        MongoDatabase spitdb = client.getDatabase(datebaseName);//打开数据库
        MongoCollection<Document> spit = spitdb.getCollection(documentName);//获取集合
        BasicDBObject bson = new BasicDBObject("visits", new
                BasicDBObject("$gt", 1000));// 构建查询条件
        FindIterable<Document> documents = spit.find(bson);//查询记录获取结果集合
        for (Document document : documents) { //
            System.out.println("内容：" + document.getString("content"));
            System.out.println("用户ID:" + document.getString("userid"));
            System.out.println("浏览量：" + document.getInteger("visits"));
        }
        client.close();//关闭连接
    }

    /**
     * 测试插入数据;
     */
    private void test02(){
        MongoClient client=new MongoClient(host);//创建连接
        MongoDatabase spitdb = client.getDatabase(datebaseName);//打开数据库
        MongoCollection<Document> spit = spitdb.getCollection(documentName);//获取集合
        Map<String,Object> map=new HashMap();
        map.put("content","我要吐槽");
        map.put("userid","9999");
        map.put("visits",123);
        map.put("publishtime",new Date());
        Document document=new Document(map);
        spit.insertOne(document);//插入数据
        client.close();

    }
}
