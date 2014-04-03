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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Administrator
 * @version 1.0 14-3-28,上午10:29
 */
public class HibernateTest {
    private Session session;
    @Rule
    public TemporaryFolder folder=new TemporaryFolder();

    @Before
    public void openNewSession() throws IOException {
        AnnotationConfiguration configuration = buildConfiguration();
        executeSQL(getClass().getResource("/test.sql"), configuration.buildSessionFactory().openSession());
        session = verbose(configuration).buildSessionFactory().openSession();
    }

    private void executeSQL(URL sqlFile, Session session) throws IOException {
        StringTokenizer commands = new StringTokenizer(IOUtils.toString(sqlFile), ";");
        session.beginTransaction();
        while (commands.hasMoreElements()) {
            String command = (String) commands.nextElement();
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
            addAnnotatedClass(Recommend.class);
            addAnnotatedClass(Photo.class);
        }};
    }

    private AnnotationConfiguration verbose(AnnotationConfiguration configuration) {
        configuration.setProperty(Environment.SHOW_SQL, "true");
        configuration.setProperty(Environment.FORMAT_SQL, "true");
        return configuration;
    }


    @Test
    public void load() throws Exception {
        Recommend recommend = (Recommend) session.get(Recommend.class, 1);
        assertThat(recommend.name, equalTo("恋天鹅系列"));
        Set<Photo> expected = new HashSet<Photo>(Arrays.asList(new Photo("1.jpg","2.jpg")));
        assertThat(recommend.pictures, equalTo(expected));
    }

}

