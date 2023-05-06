import java.io.InputStream;
import java.net.URL;

/**
 * @author hpg
 * 
 */
public class ResourceUtil {

    private ResourceUtil() {
    }

    public static InputStream getResourceInputStream(String fileName) {
        return ResourceUtil.class.getClassLoader().getResourceAsStream(fileName);
    }

    public static URL getResourceURL(String fileName) {
        return ResourceUtil.class.getClassLoader().getResource(fileName);
    }

    public static String getResourcePath() {
        return "../../src/test/resources/";
    }

    public static String getSimpleResourcePath() {
        return System.getProperty("user.dir") +"/src/test/resources/";
    }
}
