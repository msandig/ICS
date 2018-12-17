package de.dhbw.ics.database;

import de.dhbw.ics.filesystem.ApplicationEnvironment;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class DatabaseFactory {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseFactory.class);

    private Server server;
    private JdbcDataSource ds;

    public DataSource getDataSource(boolean inMemory) throws Exception {

        String path;
        if (!inMemory) {
            path = ApplicationEnvironment.getApplicationPath().toString().concat("/database") + "/db";
            LOG.info("RUNNING DATABASE IN NORMAL MODE");
        } else {
            path = "mem:testdb;DB_CLOSE_DELAY=-1";
            LOG.info("RUNNING DATABASE IN MEMORY");
            this.server = Server.createTcpServer(new String[]{
                    "-tcpPort", "11092",
                    "-tcpAllowOthers"
            }).start();
            LOG.info("Server is started on: " + server.getStatus());
        }

        if (ds == null) {
            ds = new JdbcDataSource();
            ds.setURL("jdbc:h2:" + path);
            ds.setUser("sa");
            ds.setPassword("sa");
            LOG.info("Database started on: " + ds.getUrl());
        }

        return ds;

    }


    public void closeServer() {
        if (server != null) {
            server.stop();
        }
    }
}
