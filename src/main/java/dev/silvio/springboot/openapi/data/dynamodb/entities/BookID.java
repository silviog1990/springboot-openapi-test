package dev.silvio.springboot.openapi.data.dynamodb.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@DynamoDBDocument
public class BookID {

    @DynamoDBHashKey
    @DynamoDBAttribute(attributeName = "ISBN")
	private String isbn;

    @DynamoDBRangeKey
    @DynamoDBAttribute(attributeName = "Name")
    private String name;
}
