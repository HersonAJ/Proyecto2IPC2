/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestConfiguracion;

import RestAPI.Resources.CORSFilter;
import com.sun.research.ws.wadl.Application;
import jakarta.annotation.Resources;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author herson
 */

@ApplicationPath("api/v1")
public class RestConfiguration extends ResourceConfig {

    public RestConfiguration() {
        
    }

    public void configure(Resources resources) {
        register(MultiPartFeature.class);
        register(CORSFilter.class);
    }
}