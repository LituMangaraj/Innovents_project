package com.innoventes.test.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "company")
@Entity
public class Company extends BaseEntity {

	@Id
	@SequenceGenerator(sequenceName = "company_seq", allocationSize = 1, name = "company_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
	private Long id;

	@Column(name = "company_name")
	@NotEmpty
	@Size(min=5)
	private String companyName;

	@Column(name = "email")
	@Email
	private String email;
	
	@Column(name = "strength")
	@PositiveOrZero
	@EvenNumberOrZero
	private Integer strength;
	
	@Column(name = "website_url")
	@URL
	private String webSiteURL;
	@Pattern(regexp = "[a-zA-Z]{2}[0-9]{2}[ENen]{1}")
	private String companyCode;
}
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EvenNumberOrZeroValidator.class)
@interface EvenNumberOrZero{
	String message()default "Field Must be an Even Number Or Zero ";
	Class<?>[]groups()default{};
	Class<? extends Payload>[]payload()default{};
}
class EvenNumberOrZeroValidator implements ConstraintValidator<EvenNumberOrZero,Integer>{
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context){
		return value ==null || value ==0||value %2 ==0;
	}
}
