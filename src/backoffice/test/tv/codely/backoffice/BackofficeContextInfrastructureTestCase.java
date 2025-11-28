package tv.codely.backoffice;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import tv.codely.apps.backoffice.frontend.BackofficeFrontendApplication;
import tv.codely.shared.infrastructure.InfrastructureTestCase;
import tv.codely.backoffice.courses.ElasticsearchEnvironmentArranger;


@ContextConfiguration(classes = BackofficeFrontendApplication.class)
@SpringBootTest
public abstract class BackofficeContextInfrastructureTestCase  extends InfrastructureTestCase {
    @Autowired
    private ElasticsearchEnvironmentArranger elasticsearchArranger;

    protected void clearElasticsearch() throws IOException {
        elasticsearchArranger.arrange("backoffice", "backoffice_courses");
    }
}
