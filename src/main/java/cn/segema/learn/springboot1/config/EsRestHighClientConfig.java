//package cn.segema.learn.springboot1.config;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class EsRestHighClientConfig {
//    
//    @Bean
//    public RestHighLevelClient restHighLevelClient() {
//        RestHighLevelClient restHighLevelClient = new RestHighLevelClient( 
//            RestClient.builder(new HttpHost("localhost", 9200, "http")));
//        return restHighLevelClient;
//    }
//  
//}
