package org.musala.drones.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ExceptionEnum {

	GENERAL_EXCEPTION_OCCURED(HttpStatus.INTERNAL_SERVER_ERROR,"general.exception"),
	
	// ------------------- customer ------------------
	CUSTOMER_ALREADY_EXIST(HttpStatus.BAD_REQUEST,"customer.exist.exception"),
	CUSTOMER_NOT_FOUND(HttpStatus.NOT_FOUND,"customer.not.found.exception"),
	CUSTOMER_INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED,"customer.invalid.credentials.exception"),
	CUSTOMER_INVALID_VERIFICATION(HttpStatus.UNAUTHORIZED,"customer.invalid.verification.exception"),
	CUSTOMER_ACCOUNT_DISABLED(HttpStatus.FORBIDDEN,"customer.account.disabled.exception"),
	CUSTOMER_PASSWORD_CHANGE_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR,"customer.password.change.exception"),
	CUSTOMER_CODE_GENERATION_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR,"customer.code.generation.exception"),
	CUSTOMER_SENDING_MAIL_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR,"customer.send.mail.exception"),
	UPDATE_USER_ENTITY(HttpStatus.INTERNAL_SERVER_ERROR,"update.user.information"),

	// ------------------- item ------------------
	CROP_ALREADY_EXIST(HttpStatus.INTERNAL_SERVER_ERROR,"crop.already.exist.exception"),
	CROP_NOT_FOUND(HttpStatus.NOT_FOUND,"crop.not.found"),
	ADD_CROP_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR,"add.crop"),
	ERROR_WHILE_UPDATIND_ENTITY(HttpStatus.INTERNAL_SERVER_ERROR,"update.crop.failure"),

	// ------------------- order ------------------
	CREATE_ORDER(HttpStatus.INTERNAL_SERVER_ERROR,"create.order"),
	MINIMUM_ORDER_COST(HttpStatus.INTERNAL_SERVER_ERROR,"minimum.order.cost"),
	UPDATE_ORDER(HttpStatus.INTERNAL_SERVER_ERROR,"update.order.failure"),
	UPDATE_ORDER_STATUS(HttpStatus.INTERNAL_SERVER_ERROR,"update.order.status"),
	ORDERS_FOR_CURRENT_USER(HttpStatus.NOT_FOUND,"no.orders.for.current.user"),
	ORDER_NOT_FOUND(HttpStatus.NOT_FOUND,"order.not.found"),
	
	// ------------------- cart ------------------
	CART_EMPTY_EXCEPTION(HttpStatus.NOT_FOUND,"cart.empty.exception"),
	CART_ADD_ITEM_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR,"cart.add.item.exception"),
	CART_REMOVE_ITEM_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR,"cart.remove.item.exception"),
	CART_UPDATE_ITEM_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR,"cart.update.item.exception"),
	
	// ------------------- address ------------------
	ADDRESS_NOT_COVERED_EXCEPTON(HttpStatus.INTERNAL_SERVER_ERROR,"address.not.covered.exception");
	
	@Getter
	HttpStatus responseStatus;
	@Getter
	String errCode; // from prop files , set by Language util
	
	private ExceptionEnum(HttpStatus responseStatus,String errCode) {
		this.responseStatus = responseStatus;
		this.errCode = errCode;
	}
	
}
