package com.nhuhoa.springboot.coffeestore.dto;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.nhuhoa.springboot.coffeestore.entity.Role;
import com.nhuhoa.springboot.coffeestore.validation.CheckDateFormat;
import com.nhuhoa.springboot.coffeestore.validation.FieldMatch;
import com.nhuhoa.springboot.coffeestore.validation.ValidEmail;
import com.nhuhoa.springboot.coffeestore.validation.ValidPassword;
import com.nhuhoa.springboot.coffeestore.validation.ValidPhoneNumber;
import com.nhuhoa.springboot.coffeestore.validation.ValidUsername;

@FieldMatch.List({
	@FieldMatch(first = "password", second = "matchingPassword", 
			message = "The password fileds must match")
})
public class CustomerDTO extends AbstractDTO<CustomerDTO> {
	
	@ValidUsername
	@NotNull(message = "is required")
	private String userName;
	
	@ValidPassword
	private String password;
	
	@ValidPassword
	private String matchingPassword;
	
	@NotNull(message = "is required")
	private String firstName;
	
	@NotNull(message = "is required")
	private String lastName;
	
	private String address;
	
	@NotNull(message = "is required")
	@ValidPhoneNumber
	private String phone;
	
	@ValidEmail
	@NotNull(message = "is required")
	private String email;
	
	@ValidPassword
	private String theOldPassword;
	
	@CheckDateFormat(pattern = "dd/MM/yyyy")
	private String birthdayString;
	
	private Date birthday;
	
	private int enabled;
	
	private Role role;
	
	private String roleCode;
	

	public CustomerDTO() {
		
	}
	
	public CustomerDTO(String userName, String password, String firstName, String lastName, String address,
			String phone, String email, Timestamp birthday, int enabled) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.birthday = birthday;
		this.enabled = enabled;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getBirthdayString() {
		return birthdayString;
	}

	public void setBirthdayString(String birthdayString) {
		this.birthdayString = birthdayString;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getTheOldPassword() {
		return theOldPassword;
	}

	public void setTheOldPassword(String theOldPassword) {
		this.theOldPassword = theOldPassword;
	}
	
	
	

}
