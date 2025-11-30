package backoffice.backend.command;

import tv.codely.backoffice.courses.infrastructure.ElasticsearchBackofficeCourseRepository;
import tv.codely.backoffice.courses.infrastructure.MySqlBackofficeCourseRepository;
import tv.codely.shared.infrastructure.elasticsearch.ElasticsearchRepository;

public final class MigrateCoursesFromMySqlToElasticsearchCommand {
    private final MySqlBackofficeCourseRepository mysqlRepository;
    private final ElasticsearchBackofficeCourseRepository elasticsearchRepository;

    public MigrateCoursesFromMySqlToElasticsearchCommand(
            MySqlBackofficeCourseRepository mysqlRepository,
            ElasticsearchBackofficeCourseRepository elasticsearchRepository
    ) {
        this.mysqlRepository = mysqlRepository;
        this.elasticsearchRepository = elasticsearchRepository;
    }

    public void execute() {
        mysqlRepository
                .searchAll()
                .forEach(elasticsearchRepository::save);
    }
}
