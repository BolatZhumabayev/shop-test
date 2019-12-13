package shop;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class JettyServer {

    public static Server  createServer() throws Exception {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server server = new Server();
        server.setHandler(context);

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8091);
        server.setConnectors(new Connector[] {connector});


        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                EntryPoint.class.getCanonicalName());


        return server;
    }

    public static void main(String[] args) throws Exception
    {


        Server server = createServer();
        // Start the server
        try {
            server.start();
            server.join();
        }
        finally {
            server.destroy();
        }
    }
}
