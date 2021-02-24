package dev.silvio.springboot.openapi.data.dynamodb.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@DynamoDBTable(tableName = "Books")
public class BookEntity {

	@Id
	@DynamoDBIgnore
	private BookID id;

	@DynamoDBAttribute(attributeName = "Authors")
	private List<String> authors;


	@DynamoDBHashKey()
	@DynamoDBAttribute(attributeName = "ISBN")
	public String getIsbn() {
		return this.id != null ? this.id.getIsbn() : null;
	}

	public void setIsbn(String isbn) {
		if (this.id == null) {
			this.id = new BookID();
		}
		this.id.setIsbn(isbn);
	}

	@DynamoDBRangeKey()
	@DynamoDBAttribute(attributeName = "Name")
	public String getName() {
		return this.id != null ? this.id.getName() : null;
	}

	public void setName(String name) {
		if (this.id == null) {
			this.id = new BookID();
		}
		this.id.setName(name);
	}

}