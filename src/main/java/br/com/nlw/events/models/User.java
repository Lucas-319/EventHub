package br.com.nlw.events.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	@JsonIgnore
	private Integer id;
	
	@Column(name = "user_name", length = 255, nullable = false)
	@Schema(description = "Nome completo do inscrito", example = "João da Silva")
	private String name; 
	
	@Column(name = "user_email", length = 255, nullable = false, unique = true)
	@Schema(description = "Email do inscrito", example = "joao@example.com")
	private String email;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
