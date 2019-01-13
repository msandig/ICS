package de.dhbw.ics.filesystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileSystemInitializer implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(FileSystemInitializer.class);

    private void initFileSystem() {

        initApplicationHome();

        LOG.info("Initializing file system for application!");
        String database = ApplicationEnvironment.getDatabasePath().toString();

        File directory = new File(database);
        if (!directory.exists()) {
            directory.mkdir();
        }


        String content = ApplicationEnvironment.getContentPath().toString();
        directory = new File(content);
        if (!directory.exists()) {
            directory.mkdir();
        }


        String[] folders = {"public/img/icons", "public/img/movies"};
        try {
            String directoryName = "";
            for (String folder : folders) {
                String[] dirs = folder.replace("public","").split("/");
                String structure = "";
                for(int i = 1; i<dirs.length; i++) {
                    structure = structure.concat("/").concat(dirs[i]);
                    directoryName = content.concat(structure);
                    directory = new File(directoryName);
                    if (!directory.exists()) {
                        directory.mkdir();
                    }
                }

                URI dirURL = getClass().getClassLoader().getResource(folder).toURI();
                File f = new File(dirURL);
                for (File fs : f.listFiles()) {
                    String target = content.concat(folder.replace("public","").concat("/").concat(fs.getName()));
                    File t = new File(target);
                    if(!t.exists()) {
                        Files.copy(fs.toPath(), Paths.get(target), StandardCopyOption.COPY_ATTRIBUTES);
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("Could not copy files to filesystem!");
            e.printStackTrace();
        } catch (URISyntaxException e) {
            LOG.error("Could not find files for application in classpath");
            e.printStackTrace();
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
