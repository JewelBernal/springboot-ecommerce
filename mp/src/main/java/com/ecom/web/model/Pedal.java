package com.ecom.web.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Pedal")
public class Pedal extends Product {
  @Override
  public String getCategory() {
    return "pedal";
  }
}
