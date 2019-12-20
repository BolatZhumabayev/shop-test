package shop;

import org.glassfish.jersey.server.ResourceConfig;

public class ServerConfig extends ResourceConfig {

    public ServerConfig() {
        packages("shop");
        register(EntryPoint.class);
        register(EntryPoint2.class);
    }
}
