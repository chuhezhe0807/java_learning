package elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
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
}
