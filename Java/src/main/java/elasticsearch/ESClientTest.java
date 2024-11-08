package elasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import elasticsearch.entity.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
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

    // 批量插入数据
    @Test
    public void test06() throws IOException {
        RestHighLevelClient client = getClient();

        // 批量插入
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add((new IndexRequest()).index("user").id("1002").source(XContentType.JSON, "name", "zhangsan", "age", 18, "sex", "男"));
        bulkRequest.add((new IndexRequest()).index("user").id("1003").source(XContentType.JSON, "name", "lisi", "age", 40, "sex", "女"));
        bulkRequest.add((new IndexRequest()).index("user").id("1004").source(XContentType.JSON, "name", "wangwu", "age", 21, "sex", "男"));
        bulkRequest.add((new IndexRequest()).index("user").id("1005").source(XContentType.JSON, "name", "zhangliu", "age", 33, "sex", "女"));


        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(response.status());

        client.close();
    }

    // 查询索引中的全量数据
    @Test
    public void test07() throws IOException {
        RestHighLevelClient client = getClient();

        SearchRequest searchRequest = new SearchRequest();
        searchRequest
                .indices("user")
                .source(
//                        new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()) // matchAllQuery 全量查询
//                        new SearchSourceBuilder().query(QueryBuilders.termQuery("age", 21)) // termQuery 条件查询
//                        new SearchSourceBuilder()
//                                .query(QueryBuilders.matchAllQuery()) // 分页查询 (当前页码 - 1) * 每页显示记录数
//                                .from(0)
//                                .size(2)
//                                .sort("age", SortOrder.DESC)  // 排序
//                                .fetchSource(new String[]{"name"}, new String[]{}) // 过滤结果
//                        new SearchSourceBuilder().query(
//                                QueryBuilders.boolQuery()  // 组合查询
//                                        .must(QueryBuilders.matchQuery("sex", "男"))
//                                        .must(QueryBuilders.matchQuery("age", 18)) // 也可以使用 should 也就是或 ||
//                        )
//                        new SearchSourceBuilder().query(
//                                QueryBuilders.rangeQuery("age")     // 范围查询
//                                        .from(10)
//                                        .to(20)
//                        )
//                        new SearchSourceBuilder().query(
//                                QueryBuilders.fuzzyQuery("name", "wangw")
//                                        .fuzziness(Fuzziness.ONE) // 模糊查询，差一个字符的可以匹配上
//                        )
                        new SearchSourceBuilder().aggregation(
//                                AggregationBuilders.max("maxAge").field("age") // 聚合查询，最大年龄
                                AggregationBuilders.terms("ageGroup").field("age") // 聚合查询(对应sql中的分组)
                        )
                );

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response.status());

        client.close();
    }
}
