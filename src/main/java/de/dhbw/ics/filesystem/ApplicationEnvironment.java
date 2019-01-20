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
             if (System.getProperty("os.name").contains("Windows")) {
                 path = URI.create(System.getenv("SystemDrive") + "/tmp/application");
             }else {
                 path = URI.create("/tmp/application");
             }
         }
         
         return path;
    }

    public static URI getContentPath(){
        return URI.create(getApplicationPath().toString().concat("/content"));
    }

    public static URI getDatabasePath() {
        return URI.create(getApplicationPath().toString().concat("/database"));
    }

}
