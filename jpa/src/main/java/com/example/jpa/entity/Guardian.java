package com.example.jpa.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AttributeOverrides({
	@AttributeOverride(name="name", column=@Column(name="guardian_name")),
	@AttributeOverride(name="email", column=@Column(name="guardian_email")),
	@AttributeOverride(name="mobile", column=@Column(name="guardian_mobile"))
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Guardian {
//	private String guardianName;
//	private String guardianEmail;
//	private String guardianMobile;
	
	private String name;
	private String email;
	private String mobile;

}
