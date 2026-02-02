/**
 *
 */
package pl.edu.wat.wcy.cop.common.resources;

import com.google.common.base.CharMatcher;
import pl.edu.wat.wcy.cop.common.logging.OMLogger;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


// Provides helpers for loading application resources.
public class ResourceUtils {

    public static boolean loadProperties(Properties properties, InputStream propsIn) {
        try {
            properties.load(propsIn);
            return true;
        } catch (java.io.IOException e) {
            return false;
        }
    }

    public static URL getResourceOrFileOrURL(String name) {
        return getResourceOrFileOrURL(null, name);
    }

    public static URL getResourceOrFileOrURL(Object askingClass, String name) {
        return getResourceOrFileOrURL(askingClass.getClass(), name);
    }

    public static URL getResourceOrFileOrURL(Class askingClass, String name) {
        if (name == null) {
            return null;
        }
        name = name.replaceAll("//", "/");
        URL retval = null;
        if (askingClass != null) {
            retval = askingClass.getResource(name);
        }
        if (askingClass != null && retval == null) {

            URL baseUrl = askingClass.getResource(".");
            if (baseUrl != null) {
                try {
                    retval = new URL(baseUrl, name);
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    OMLogger.getInstance().error(e.getMessage(), e);
                }
            }
        }
        if (retval == null) {
            retval = Thread.currentThread().getContextClassLoader().getResource(name);
        }
        if (retval == null) {
            try {
                java.io.File file = new java.io.File(name);
                if (file.exists()) {
                    retval = file.toURL();
                } else {
                    retval = new URL(name);
                    java.io.InputStream is = retval.openStream();
                    is.close();
                }
            } catch (java.io.IOException ioe) {
                retval = null;
            } catch (java.security.AccessControlException ace) {
                retval = null;
            } catch (Exception e) {
                retval = null;
            }
        }
        if (retval == null) {
            name = CharMatcher.is('/').replaceFrom(name, File.separatorChar);
            if (askingClass != null) {
                retval = askingClass.getResource(name);
            }
            if (askingClass != null && retval == null) {
                URL baseUrl = askingClass.getResource(".");
                if (baseUrl != null) {
                    try {
                        retval = new URL(baseUrl, name);
                    } catch (MalformedURLException e) {
                        // TODO Auto-generated catch block
                        OMLogger.getInstance().error(e.getMessage(), e);
                    }
                }
            }
            if (retval == null) {
                retval = Thread.currentThread().getContextClassLoader().getResource(name);
            }
            if (retval == null) {

                try {
                    java.io.File file = new java.io.File(name);
                    if (file.exists()) {
                        retval = file.toURL();
                    } else {
                        retval = new URL(name);
                        java.io.InputStream is = retval.openStream();
                        is.close();
                    }
                } catch (java.io.IOException ioe) {
                    retval = null;
                } catch (java.security.AccessControlException ace) {
                    retval = null;
                } catch (Exception e) {
                    retval = null;
                }
            }
        }
        if (retval == null) {
            name = CharMatcher.is('\\').replaceFrom(name, File.separatorChar);
            if (askingClass != null) {
                retval = askingClass.getResource(name);
            }
            if (askingClass != null && retval == null) {
                URL baseUrl = askingClass.getResource(".");
                if (baseUrl != null) {
                    try {
                        retval = new URL(baseUrl, name);
                    } catch (MalformedURLException e) {
                        // TODO Auto-generated catch block
                        OMLogger.getInstance().error(e.getMessage(), e);
                    }
                }
            }
            if (retval == null) {
                retval = Thread.currentThread().getContextClassLoader().getResource(name);
            }
            if (retval == null) {

                try {
                    java.io.File file = new java.io.File(name);
                    if (file.exists()) {
                        retval = file.toURL();
                    } else {
                        retval = new URL(name);
                        java.io.InputStream is = retval.openStream();
                        is.close();
                    }
                } catch (java.io.IOException ioe) {
                    retval = null;
                } catch (java.security.AccessControlException ace) {
                    retval = null;
                } catch (Exception e) {
                    retval = null;
                }
            }
        }
        return retval;
    }

    // ponizsze metody napisalem z powodu problemow z ladowaniem resource'ow,
    // gdy w sciezce znajduja sie biale znaki
    public static Path getResourcePath(Object askingClass, String name) throws URISyntaxException {
        return getResourcePath(askingClass.getClass(), name);
    }

    public static Path getResourcePath(Class askingClass, String name) throws URISyntaxException {
        URL url = getResourceOrFileOrURL(askingClass, name);
        Path path = Paths.get(url.toURI());
        return path;
    }
}
