package de.dhbw.ics.filesystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;

public class FileSystemInitializer implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(FileSystemInitializer.class);

    private void initFileSystem() {

        initApplicationHome();


        LOG.info("Initializing file system for application!");
        String systemPath = ApplicationEnvironment.getApplicationPath().toString();
        String directoryName = systemPath.concat("/database");

        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdir();
        }

    }

    private void initApplicationHome() {
        LOG.info("Initializing application home for filesystem!");
        String systemPath = ApplicationEnvironment.getApplicationPath().toString();
        File directory = new File(systemPath);
        if (!directory.exists()) {
            directory.mkdir();
        }

    }

    @Override
    public void afterPropertiesSet() {
        initFileSystem();
    }
}
