package elasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import elasticsearch.entity.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * ClassName: ESClient
 * Package: elasticsearch
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/11/6 22:49
 * @Version 1.0
 */
public class ESClientTest {
    // 创建ES客户端
    public static RestHighLevelClient getClient() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")
                )
        );
    }

    // 创建索引
    @Test
    public void test01() throws IOException {
        RestHighLevelClient client = getClient();
        CreateIndexRequest user = new CreateIndexRequest("user");
        CreateIndexResponse createIndexResponse = client.indices().create(user, RequestOptions.DEFAULT);

        // 响应状态
        boolean acknowledged = createIndexResponse.isAcknowledged();

        System.out.println("acknowledged: " + acknowledged);

        client.close();
    }

    // 查询索引
    @Test
    public void test02() throws IOException {
        RestHighLevelClient client = getClient();
        GetIndexRequest user = new GetIndexRequest("user");
        GetIndexResponse createIndexResponse = client.indices().get(user, RequestOptions.DEFAULT);

        // 响应状态
        System.out.println(createIndexResponse.getAliases());  // 索引别名
        System.out.println(createIndexResponse.getMappings()); // 映射
        System.out.println(createIndexResponse.getSettings());

        client.close();
    }

    // 创建文档(一行数据)
    @Test
    public void test03() throws IOException {
        RestHighLevelClient client = getClient();

        User user = new User();
        user.setName("张三");
        user.setAge(20);
        user.setSex("男");

        // 向ES插入数据，必须将数据转换为JSON格式
        ObjectMapper objectMapper = new ObjectMapper();
        String source = objectMapper.writeValueAsString(user);
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index("user").id("1001").source(source, XContentType.JSON);


        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(response.getShardId()); // 分片id

        client.close();
    }

    // 修改文档(一行数据)
    @Test
    public void test04() throws IOException {
        RestHighLevelClient client = getClient();

        // 向ES插入数据，必须将数据转换为JSON格式
        UpdateRequest updateReq = new UpdateRequest();
        updateReq.index("user").id("1001").doc(XContentType.JSON, "sex", "女");

        UpdateResponse response = client.update(updateReq, RequestOptions.DEFAULT);
        System.out.println(response.getResult());

        client.close();
    }

    // 查询文档(一行数据)
    @Test
    public void test05() throws IOException {
        RestHighLevelClient client = getClient();

        // 向ES插入数据，必须将数据转换为JSON格式
        GetRequest getReq = new GetRequest();
        getReq.index("user").id("1001");

        GetResponse response = client.get(getReq, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());

        client.close();
    }
}
