@Configuration
public class TransportClientConfig extends ElasticsearchConfigurationSupport {

    @Bean
    public Client elasticsearchClient() throws UnknownHostException {
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();        
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300)); 
        return client;
    }

    @Bean(name = { "elasticsearchOperations", "elasticsearchTemplate" })
    public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {

		ElasticsearchTemplate template = new ElasticsearchTemplate(elasticsearchClient, elasticsearchConverter);
		template.setRefreshPolicy(refreshPolicy());                                                 

		return template;
    }
}

// ...

IndexRequest request = new IndexRequest("spring-data")
 .id(randomID())
 .source(someObject);

IndexResponse response = client.index(request);


