package pe.edu.cibertec.orderservicei201410746.model;

import lombok.Data;

@Data
public class Order {

  private String orderId;
  private String customerEmail;
  private String product;
  private int quantity;
  private double price;

}
