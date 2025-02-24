package com.ecom.web.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Guitar")
public class Guitar extends Product {

}
