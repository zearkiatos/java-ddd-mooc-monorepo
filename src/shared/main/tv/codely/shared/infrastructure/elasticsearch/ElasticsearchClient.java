package tv.codely.shared.infrastructure.elasticsearch;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public final class ElasticsearchClient {
    private final RestHighLevelClient highLevelClient;
    private final RestClient          lowLevelClient;
    private final String              indexPrefix;

    public ElasticsearchClient(RestHighLevelClient highLevelClient, RestClient lowLevelClient, String indexPrefix) {
        this.highLevelClient = highLevelClient;
        this.lowLevelClient  = lowLevelClient;
        this.indexPrefix     = indexPrefix;
    }

    public RestHighLevelClient highLevelClient() {
        return highLevelClient;
    }

    public RestClient lowLevelClient() {
        return lowLevelClient;
    }

    public String indexPrefix() {
        return indexPrefix;
    }

    public void persist(String moduleName, String id, HashMap<String, Serializable> plainBody) throws IOException {
        IndexRequest request = new IndexRequest(indexFor(moduleName), moduleName, id).source(plainBody);

        highLevelClient().index(request, RequestOptions.DEFAULT);
    }

    public String indexFor(String moduleName) {
        return String.format("%s_%s", indexPrefix(), moduleName);
    }
}
