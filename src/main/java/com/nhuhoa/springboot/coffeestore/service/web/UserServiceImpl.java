package com.nhuhoa.springboot.coffeestore.service.web;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhuhoa.springboot.coffeestore.dao.IRoleDao;
import com.nhuhoa.springboot.coffeestore.dao.IUserDao;
import com.nhuhoa.springboot.coffeestore.dto.UserDTO;
import com.nhuhoa.springboot.coffeestore.entity.Role;
import com.nhuhoa.springboot.coffeestore.entity.User;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;
import com.nhuhoa.springboot.coffeestore.utils.DateUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IRoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Iterable<UserDTO> findAll(IPaging paging) {
		
		Iterable<User> theUsers = userDao.findAll(paging); 
		
		TypeToken<Iterable<UserDTO>> typeToken = new TypeToken<Iterable<UserDTO>>() {
		};

		Iterable<UserDTO> theUserDTOs = mapper.map(theUsers, typeToken.getType());
		
		return theUserDTOs;
	}

	@Override
	public UserDTO findById(Long id) {
		
		User theUser = userDao.findById(id);
		
		if(theUser == null) {
			return null;
		}
		
		UserDTO theUserDTO = mapper.map(theUser, UserDTO.class);
		
		// set role 
		List<Role> roles = theUser.getRoles();
		
		
			if(roles.size() == 3) {
				theUserDTO.setRoleCode("ROLE_ADMIN");
				theUserDTO.setRoleName("Admin");
				
			} else if(roles.size() == 2) {
				theUserDTO.setRoleCode("ROLE_MANAGER");
				theUserDTO.setRoleName("Manager");
			
			} else {
				theUserDTO.setRoleCode("ROLE_EMPLOYEE");
				theUserDTO.setRoleName("Employee");
			}
		// set birthday String display in view
		Date birthday = theUserDTO.getBirthday();	
		String birthdayString = null;
		if(birthday != null) {
			birthdayString = DateUtils.dateToString(birthday);
		}
		theUserDTO.setBirthdayString(birthdayString);	
		
		
		return theUserDTO;
	}

	@Override
	public UserDTO save(UserDTO theUserDTO) {
		
		String birthdayString = theUserDTO.getBirthdayString();
		
		Date birthday = null;
		
		if(birthdayString != null && birthdayString.trim().length()>0 ) {
			
			birthday = DateUtils.stringToDate(birthdayString);
		}
		
		
		theUserDTO.setBirthday(birthday);
		
		theUserDTO.setEnabled(1);
		
		
		User theUser = mapper.map(theUserDTO, User.class);
		
		
		String theNewPassword = theUserDTO.getPassword();
		
		if(theNewPassword == null && theUserDTO.getId() != null) {
			User theOldUser = userDao.findById(theUserDTO.getId() );
			theUser.setPassword(theOldUser.getPassword());
		} else if(theNewPassword != null && theUserDTO.getId() != null) {
			theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
		}
		
		// (created day or modified day) and (created by or modified by)
		
		if(theUser.getId() == null) {
			theUser.setCreatedDate(new Date());
			theUser.setCreatedBy(theUserDTO.getUsernameAdmin());
			theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
		} else {
			theUser.setModifiedDate(new Date());
			theUser.setModefiedBy(theUserDTO.getUsernameAdmin());
		}
		
		
		
		// set roles
		if(theUserDTO.getRoleCode().contains("ROLE_ADMIN")) {
			
			theUser.setRoles(Arrays.asList(roleDao.findRoleByCode("ROLE_EMPLOYEE"),roleDao.findRoleByCode("ROLE_MANAGER"),
					roleDao.findRoleByCode("ROLE_ADMIN")));
			
		} else if (theUserDTO.getRoleCode().contains("ROLE_MANAGER")) {
			
			theUser.setRoles(Arrays.asList(roleDao.findRoleByCode("ROLE_EMPLOYEE"),roleDao.findRoleByCode("ROLE_MANAGER")));
			
			
		} else if(theUserDTO.getRoleCode().contains("ROLE_EMPLOYEE")) {
			theUser.setRoles(Arrays.asList(roleDao.findRoleByCode("ROLE_EMPLOYEE")));
			
		}
			
		
		
		User theUserNew = userDao.save(theUser);
		
		theUserDTO.setId(theUserNew.getId());
		return theUserDTO;
	}

	@Override
	public void remove(Long id) {
		
		userDao.remove(id);

	}

	@Override
	public UserDTO findByUserName(String userName) {
		
		User theUser = userDao.findByUserName(userName);
		
		if(theUser == null) {
			return null;
		}
		
		UserDTO theUserDTO = mapper.map(theUser, UserDTO.class);
		
		return theUserDTO;
	}

	@Override
	public UserDTO findByEmail(String email) {

		User theUser = userDao.findByEmail(email);
		
		if(theUser == null) {
			return null;
		}
		
		UserDTO theUserDTO = mapper.map(theUser, UserDTO.class);
		
		return theUserDTO;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		 
	        if (user == null) {
	            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
	        }
	 
	        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),
					mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getCode())).collect(Collectors.toList());
	}

	@Override
	public Iterable<UserDTO> search(IPaging paging, String theSearchValue) {
		
		Iterable<User> theUsers = userDao.search(paging, theSearchValue);
		
		TypeToken<Iterable<UserDTO>> typeToken = new TypeToken<Iterable<UserDTO>>() {
		};

		Iterable<UserDTO> theUserDTOs = mapper.map(theUsers, typeToken.getType());
		
		return theUserDTOs;
	}

	@Override
	public Long count() {
		
		return userDao.count();
	}

	@Override
	public void saveRoles(UserDTO theUserDto) {
		
		User theUser = userDao.findByEmail(theUserDto.getEmail());
		
		theUser.setModifiedDate(new Date());
		theUser.setModefiedBy(theUserDto.getUsernameAdmin());
		
		// set roles
		if(theUserDto.getRoleCode().contains("ROLE_ADMIN")) {
			
			theUser.setRoles(Arrays.asList(roleDao.findRoleByCode("ROLE_EMPLOYEE"),roleDao.findRoleByCode("ROLE_MANAGER"),
					roleDao.findRoleByCode("ROLE_ADMIN")));
			
		} else if (theUserDto.getRoleCode().contains("ROLE_MANAGER")) {
			
			theUser.setRoles(Arrays.asList(roleDao.findRoleByCode("ROLE_EMPLOYEE"),roleDao.findRoleByCode("ROLE_MANAGER")));
			
			
		} else if(theUserDto.getRoleCode().contains("ROLE_EMPLOYEE")) {
			theUser.setRoles(Arrays.asList(roleDao.findRoleByCode("ROLE_EMPLOYEE")));
			
		}
		
		userDao.save(theUser);
	}
	

}
