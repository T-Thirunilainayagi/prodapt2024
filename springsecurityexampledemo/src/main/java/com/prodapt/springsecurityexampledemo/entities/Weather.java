package com.prodapt.springsecurityexampledemo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="weather_details")
public class Weather {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer weatherId;
	private String forecast;
}
