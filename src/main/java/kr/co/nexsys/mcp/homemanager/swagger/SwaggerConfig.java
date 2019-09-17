package kr.co.nexsys.mcp.homemanager.swagger;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket homeMmsApi(){

        /* 방법 1 : 글로벌옵션을 통해 모든 파라미터에 Header값 추가 하기 */
       /* ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("srcMRN") //헤더 이름
                .description("Access Tocken") //설명
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        List<Parameter> aParameters = new ArrayList<>();
        aParameters.add(aParameterBuilder.build());*/

        return new Docket(DocumentationType.SWAGGER_2)
                //.globalOperationParameters(aParameters)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .securitySchemes(Arrays.asList(apiKey()));
    }

    /* 방법 2 : ApiKey 클래스를 이용하여 Swagger 오른쪽 상단에 Authorization 관련 Header 추가 (한번 적용시 유지되므로 테스트 환경 좋음)*/
    private ApiKey apiKey() {
        return new ApiKey("srcMRN + accessToken", "srcMRN", "header");
    }

}
