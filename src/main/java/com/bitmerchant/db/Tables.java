package com.bitmerchant.db;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.DbName;
import org.javalite.activejdbc.annotations.Table;

public class Tables {
	
	@DbName("bitmerchant")
	@Table("orders") 
	public static class Order extends Model {};
	public static Order Order = new Order();
	
	@DbName("bitmerchant")
	@Table("orders_view") 
	public static class OrderView extends Model {}
	public static OrderView OrderView = new OrderView();	
	
	@DbName("bitmerchant")
	@Table("order_statuses") 
	public static class OrderStatus extends Model {}
	public static OrderStatus OrderStatus = new OrderStatus();
	
	@DbName("bitmerchant")
	@Table("buttons") 
	public static class Button extends Model {}
	public static Button Button = new Button();
	
	@DbName("bitmerchant")
	@Table("buttons_view") 
	public static class ButtonView extends Model {}
	public static ButtonView ButtonView = new ButtonView();
	
	@DbName("bitmerchant")
	@Table("button_types") 
	public static class ButtonType extends Model {}
	public static ButtonType ButtonType = new ButtonType();
	
	@DbName("bitmerchant")
	@Table("button_styles") 
	public static class ButtonStyle extends Model {}
	public static ButtonStyle ButtonStyle = new ButtonStyle();
	
	@DbName("bitmerchant")
	@Table("currencies") 
	public static class Currency extends Model {}
	public static Currency Currency = new Currency();
	
	@DbName("bitmerchant")
	@Table("payments") 
	public static class Payment extends Model {}
	public static Payment Payment = new Payment();
	
	@DbName("bitmerchant")
	@Table("transactions") 
	public static class Transaction extends Model {}
	public static Transaction Transaction = new Transaction();
	
	@DbName("bitmerchant")
	@Table("refunds") 
	public static class Refund extends Model {}
	public static Refund Refund = new Refund();
	
	@DbName("bitmerchant")
	@Table("merchant_info") 
	public static class MerchantInfo extends Model {}
	public static MerchantInfo MerchantInfo = new MerchantInfo();
	
	@DbName("bitmerchant")
	@Table("merchant_info_view") 
	public static class MerchantInfoView extends Model {}
	public static MerchantInfoView MerchantInfoView = new MerchantInfoView();
	

	
	
}
