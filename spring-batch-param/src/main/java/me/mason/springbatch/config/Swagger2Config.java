package me.mason.springbatch.config;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * swagger 配置类，访问：http://ip:port/swagger-ui.html
 *
 * @author mason
 *
 * @date 2019/6/1
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
	@Autowired
	private SwaggerInfo swaggerInfo;

	@Bean
	public Docket createRestApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2).groupName(swaggerInfo.getGroupName())
				.apiInfo(apiInfo());
		//是否启用
		if (BooleanUtil.isFalse(swaggerInfo.getEnable())) {
			docket.enable(false);
		}
		ApiSelectorBuilder builder = docket.select();
		builder = builder.apis(Predicates.or(apis()));
		if (StrUtil.isNotEmpty(swaggerInfo.getAntPath())) {
			builder = builder.paths(PathSelectors.ant(swaggerInfo.getAntPath()));
		}
		return builder.build();
	}


	private List<Predicate<RequestHandler>> apis() {
		List<Predicate<RequestHandler>> apis = new ArrayList<>();
		String basePackageStr = swaggerInfo.getBasePackage();
		if (StrUtil.isNotEmpty(basePackageStr)) {
			String[] basePackages = basePackageStr.split(";");
			if (null != basePackages && basePackages.length > 0) {
				Predicate<RequestHandler> predicate = input -> {
					// basePackage包验证
					Class<?> declaringClass = input.declaringClass();
					String packageName = declaringClass.getPackage().getName();
					boolean basePackageIsOk = Arrays.asList(basePackages).contains(packageName);
					// 过滤掉特定的API
					boolean filterIsOk = false;
					// 被注解的类
//                    if (declaringClass.isAnnotationPresent(RestController.class)) {
//                        filterIsOk = true;
//                    }
					// 被注解的方法
					if(input.isAnnotatedWith(ApiOperation.class)){
						filterIsOk = true;
					}
					return basePackageIsOk && filterIsOk;
				};
				apis.add(predicate);
			}
		}
		return apis;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(swaggerInfo.getTitle()).description(swaggerInfo.getDescription())
				.version(swaggerInfo.getVersion()).licenseUrl(swaggerInfo.getLicenseUrl())
				.contact(new Contact(swaggerInfo.getContactName(),swaggerInfo.getContactUrl()
						,swaggerInfo.getContactEmail()))
				.build();
	}
}
