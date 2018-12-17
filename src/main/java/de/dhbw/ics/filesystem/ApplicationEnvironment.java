package de.dhbw.ics.filesystem;

import java.net.URI;

public class ApplicationEnvironment {

    private static final String ENVIRONMENT = "ICS_HOME";

    public static URI getApplicationPath() {
        URI path = null;
        String environment = System.getenv(ENVIRONMENT);

        if(environment != null) {
             path = URI.create(environment);
        }
         if(path == null){
             path = URI.create(System.getenv("SystemDrive") + "/tmp/application");
         }
         
         return path;
    }

}
