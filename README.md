## 07/20

## [팀원]박윤정


1. 팀원들과의 공유 폴더&문서함 만들기
2. article Spring, REST API 개발하기 => DTO작성하다가 디비 설계가 잘못되어 중단
3. 회의 후 도출되는 문제 해결하기

#### Q. 기행문 조회시 도를 보여줄지? 시군을 보여줄지? 

##### A. 도 - 시군 순서/ 도를 누르면 시군을 선택하고, 시군을 선택하면 기행문이 나오도록 => 다각형, 폴리곤라인 활용

##### 카카오맵 API 중 다각형 이벤트 등록하기 2 https://apis.map.kakao.com/web/sample/addPolygonMouseEvent2/

##### 폴리곤 만들기 https://iron-jin.tistory.com/entry/%EC%B9%B4%EC%B9%B4%EC%98%A4-%EC%A7%80%EB%8F%84-api-%EC%8B%9C%EA%B5%B0%EA%B5%AC-%ED%8F%B4%EB%A6%AC%EA%B3%A4-%EB%A7%8C%EB%93%A4%EA%B8%B0-feat-javascript

#### Q. Book을 만들 때 기행문 1개는 꼭 써야하게 해야하는가?

##### A. Book생성시 Article도 1개 생성하도록 구성하자!

#### Q. 원래는 Article만 CRUD를 생각하였는데 그럼 Book도 CRUD기능이 모두 필요한가? 

##### A. Book도 CRUD기능이 모두 필요할 것이라고 생각하자!

4. DB 다시 설계하기 (Book 테이블 추가 및 속성 변경)

#### * CREATE TABLE CODE


CREATE TABLE `user` (
   `email` varchar(50) PRIMARY KEY,
    `password` varchar(20) NOT NULL,
    `nickname` varchar(10) NOT NULL UNIQUE,
    `introduce` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `book` (
  `bookno` int NOT NULL auto_increment,
  `title` varchar(50) DEFAULT NULL,
  `writer` varchar(50),
  `writedate` datetime DEFAULT current_timestamp(),
  `province` varchar(10),
  `city` varchar(10),
  `startdate` datetime,
  PRIMARY KEY (`bookNo`),
  FOREIGN KEY (writer) REFERENCES user (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `article` (
  `articleno` int NOT NULL auto_increment,
  `bookno` int,
  `title` varchar(50) DEFAULT NULL,
  `writedate` datetime DEFAULT current_timestamp(),
  `score` DECIMAL(3,2),
  `weather` varchar(10),
  `text` mediumtext,
  `traveldate` datetime,
  `day` int,
  PRIMARY KEY (`articleno`),
  FOREIGN KEY (bookno) REFERENCES book (bookno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `article_score`(
   `scoreno`  int NOT NULL auto_increment,
    `score` int NOT NULL,
    `author` varchar(50),
    `articleno` int,
    PRIMARY KEY (`scoreno`),
    FOREIGN KEY (author) REFERENCES user (email),
    FOREIGN KEY (articleno) REFERENCES article (articleno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `article_comment`(
   `commentno` int NOT NULL AUTO_INCREMENT,
   `articleno` int,
   `writer` varchar(50), 
    `writedate` datetime DEFAULT current_timestamp(),
    `text` varchar(100),
    PRIMARY KEY (`commentno`),
    FOREIGN KEY (writer) REFERENCES user (email),
	FOREIGN KEY (articleno) REFERENCES article (articleno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#### * ER 다이어그램
![diagram](https://user-images.githubusercontent.com/28215411/87912423-53f36d00-caa8-11ea-8bf6-eae0e586dc40.png)

## [팀장] 윤인하
1. 팀 미팅 및 회의를 통해 개선 방안 및 세부 개선점 정리 - 세부 내용은 위와 같음
2. Swagger 사용

Reference
https://jojoldu.tistory.com/31
http://tech.javacafe.io/spring%20boot/2019/04/21/Spring-swagger/

Code

Swagger 설정 파일

    // swaggerConfig.java
    
    package com.ssafyA205.loginEX.swagger;
    
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
    import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
    
    import springfox.documentation.builders.PathSelectors;
    import springfox.documentation.builders.RequestHandlerSelectors;
    import springfox.documentation.spi.DocumentationType;
    import springfox.documentation.spring.web.plugins.Docket;
    import springfox.documentation.swagger2.annotations.EnableSwagger2;
    
    @Configuration
    @EnableSwagger2
    public class swaggerConfig extends WebMvcConfigurationSupport {
    	@Override
    	public void addResourceHandlers(ResourceHandlerRegistry registry) {
    		registry.addResourceHandler("swagger-ui.html")
    		.addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
    		
    		registry.addResourceHandler("/webjars/**")
    		.addResourceLocations("classpath:/META-INF/resources/webjars/");
    	}
    	
    	@Bean
    	public Docket api() {
    		return new Docket(DocumentationType.SWAGGER_2)
    				.select()
    				.apis(RequestHandlerSelectors.any())
    				.paths(PathSelectors.any())
    				.build();
    	}
    }

라이브러리 추가

    // pom.xml
    ...
    
    <dependency>
    	<groupId>io.springfox</groupId>
    	<artifactId>springfox-swagger2</artifactId>
    	<version>2.9.2</version>
    </dependency>
    
    <dependency>
    	<groupId>io.springfox</groupId>
    	<artifactId>springfox-swagger-ui</artifactId>
    	<version>2.9.2</version>
    </dependency>
    
    ...

실행 화면 (localhost/swagger-ui.html)
![swagger](/uploads/ed0a4b5d176f43a889a01b2ae84cba74/swagger.png)

## [팀원] 임진섭

### 이미지의 메타데이터를 활용하여 위치데이터를 카카오맵과 연동하여 띄우기
![image](https://lab.ssafy.com/s03-webmobile1-sub1/s03p11a227/raw/master/src/main/webapp/base.PNG)
![image](https://lab.ssafy.com/s03-webmobile1-sub1/s03p11a227/raw/master/src/main/webapp/multi.PNG)

#### To do
- 하나의 지도에 클러스터링으로 나타내기
- 위치 데이터가 없을 경우의 처리 방안