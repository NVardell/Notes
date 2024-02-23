import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.stream.Stream;

/**
 * Repo Initializer
 *
 * @author N.V.
 * @since 12/27/2019
 */
@Log4j2
@Component
class AutoInsertRecordsIntoDbOnStartUp implements CommandLineRunner {

    @Resource
    private Repo reqRepo;

    /**
     * AUTO-RUN
     *
     * When application starts up, this method will insert records into the desired repo.
     * @param args Commandline Arguments
     * @throws Exception In case of error
     */
    @Override
    public void run(String... args) throws Exception {
        Stream.of(Request.builder().name("Request #1"), Request.builder().name("Request #2"))
                .forEach(reqRepo::save);
        reqRepo.findAll().forEach(log::info);
    }
}