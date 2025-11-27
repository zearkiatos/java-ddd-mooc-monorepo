package tv.codely.backoffice.courses;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import org.elasticsearch.client.Request;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import tv.codely.shared.infrastructure.elasticsearch.ElasticsearchClient;
import tv.codely.shared.domain.ServiceInjectable;

@ServiceInjectable
public final class ElasticsearchEnvironmentArranger {
    ResourcePatternResolver resourceResolver;
    ElasticsearchClient     client;

    public ElasticsearchEnvironmentArranger(
        ResourcePatternResolver resourceResolver,
        ElasticsearchClient client
    ) {
        this.resourceResolver = resourceResolver;
        this.client           = client;
    }

    public void arrange(String contextName, String index) throws IOException {
        client.delete(index);

        Resource[] jsonsIndexes = resourceResolver.getResources(
            String.format("classpath:database/%s/%s.json", contextName, index)
        );

        for (Resource jsonIndex : jsonsIndexes) {
            String indexName = Objects.requireNonNull(jsonIndex.getFilename()).replace(".json", "");

            String indexBody = new Scanner(
                jsonIndex.getInputStream(),
                "UTF-8"
            ).useDelimiter("\\A").next();

            Request request = new Request("PUT", indexName);
            request.setJsonEntity(indexBody);

            client.lowLevelClient().performRequest(request);
        }
    }
}
