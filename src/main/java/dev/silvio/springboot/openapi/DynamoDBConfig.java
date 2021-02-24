package dev.silvio.springboot.openapi;

import dev.silvio.springboot.openapi.data.dynamodb.repositories.BookRepository;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@Configuration
@EnableDynamoDBRepositories(basePackageClasses = BookRepository.class)
public class DynamoDBConfig {

	@Value("${amazon.dynamodb.endpoint}")
	private String amazonDynamoDBEndpoint;

	@Value("${amazon.aws.accesskey}")
	private String amazonAWSAccessKey;

	@Value("${amazon.aws.secretkey}")
	private String amazonAWSSecretKey;

	@Value("${amazon.dynamodb.region}")
	private String amazonDynamoDBRegion;

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new EndpointConfiguration(amazonDynamoDBEndpoint, amazonDynamoDBRegion))
				.withCredentials(amazonAWSCredentials())
				.build();
		return amazonDynamoDB;
	}

	public AWSStaticCredentialsProvider amazonAWSCredentials() {
		BasicAWSCredentials creds = new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
		return new AWSStaticCredentialsProvider(creds);
	}
}