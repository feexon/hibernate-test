import org.apache.commons.io.IOUtils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;
import org.hibernate.classic.Session;
import org.hibernate.dialect.HSQLDialect;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
    private SessionFactory sessionFactory;

    @Before
    public void openNewSession() throws IOException {
        sessionFactory = buildConfiguration().buildSessionFactory();
        session = sessionFactory.openSession();
        executeSQL(getClass().getResource("/test.sql"));
    }

    private void executeSQL(URL sqlFile) throws IOException {
        StringTokenizer commands = new StringTokenizer(IOUtils.toString(sqlFile), ";");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        while (commands.hasMoreElements()) {
            session.createSQLQuery((String) commands.nextElement()).executeUpdate();
        }
        session.getTransaction().commit();
    }

    private AnnotationConfiguration buildConfiguration() {
        return new AnnotationConfiguration() {{
            setProperty(Environment.DIALECT, HSQLDialect.class.getName());
            setProperty(Environment.DRIVER, JDBC.class.getName());
            setProperty(Environment.URL, "jdbc:sqlite:");
        }};
    }


    @Test
    public void touch() throws Exception {
        session.beginTransaction().rollback();
    }
}
