package za.co.CrudApi.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition()
public class OpenAPIConfig {


    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components( new Components() )
                .info( getApiInfo() );
    }

    private Info getApiInfo() {
        return new Info()
                .title( " Employee Application" )
                .description( "Application for Creating,Updating and Searching For a Client technology." )
                .version( "2.0.0" )
                .contact( new Contact().name( "Temi.Akobi@co.za" ).url( "https://employee.co.za" ).email( "info@employee.co.za" ) );
    }

}
