package tv.codely.mooc.videos;

import org.springframework.beans.factory.annotation.Autowired;

import tv.codely.mooc.videos.infrastructure.InMemoryVideoRepository;
import tv.codely.shared.infrastructure.InfrastructureTestCase;

public abstract class VideosModuleInfrastructureTestCase extends InfrastructureTestCase {
    @Autowired
    protected InMemoryVideoRepository repository;
}
