package shop;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class JettyServer {

    public static Server  createServer() throws Exception {

        Server server = new Server(82);
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler.addServlet(new ServletHolder(DefaultServlet.class), "/");
        contextHandler.addServlet(new ServletHolder(new ServletContainer(
                new ServerConfig())), "/*");
        server.setHandler(contextHandler);
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
