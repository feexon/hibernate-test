import org.apache.commons.io.IOUtils;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;
import org.hibernate.classic.Session;
import org.hibernate.dialect.HSQLDialect;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.sqlite.JDBC;

import java.io.IOException;
import java.net.URL;
import java.util.StringTokenizer;

/**
 * @author Administrator
 * @version 1.0 14-3-28,上午10:29
 */
public class HibernateTest {
    private Session session;
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void openNewSession() throws IOException {
        AnnotationConfiguration configuration = buildConfiguration();
        executeSQL(getClass().getResource("/test.sql"), configuration.buildSessionFactory().openSession());
        session = verbose(configuration).buildSessionFactory().openSession();
    }

    private void executeSQL(URL sqlFile, Session session) throws IOException {
        String[] commands = IOUtils.toString(sqlFile).split("--\\$");
        session.beginTransaction();
        for (String command : commands) {
            if (command.trim().equals("")) {
                continue;
            }
            session.createSQLQuery(command).executeUpdate();
        }
        session.getTransaction().commit();
    }

    private AnnotationConfiguration buildConfiguration() throws IOException {

        return new AnnotationConfiguration() {{
            setProperty(Environment.DIALECT, HSQLDialect.class.getName());
            setProperty(Environment.DRIVER, JDBC.class.getName());
            setProperty(Environment.URL, "jdbc:sqlite:" + folder.newFile().getAbsolutePath());

        }};
    }

    private AnnotationConfiguration verbose(AnnotationConfiguration configuration) {
        configuration.setProperty(Environment.SHOW_SQL, "true");
        configuration.setProperty(Environment.FORMAT_SQL, "true");
        return configuration;
    }


    @Test
    public void touch() throws Exception {
        session.beginTransaction().rollback();
    }

}

