package com.moment.the;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.moment.the.advice.exception.UserAlreadyExistsException;
import com.moment.the.advice.exception.UserNotFoundException;
import com.moment.the.domain.AdminDomain;
import com.moment.the.dto.AdminDto;
import com.moment.the.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class TheApplicationTests {

	@Test
	void pageable_값_검증() {
		//Given
		int expected = (186-5)/6;
		//When
		int actual = 30;
		//then
		assertEquals(expected, actual);
		System.out.println("expected: " + expected + " actual: " + actual);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AdminRepository adminRepository;

	@Test
	void 회원가입(){
		//Given
		AdminDto adminDto = new AdminDto();
		String email = "s20062@gsm.hs.kr";
		String adminName = "jihwan";
		String pw = "1234";

		//when
		adminDto.setAdminPwd(passwordEncoder.encode(pw));
		adminDto.setAdminId(email);
		adminDto.setAdminName(adminName);
		adminRepository.save(adminDto.toEntity());

		//then
		assertEquals(adminDto.getAdminId(), email);
		assertEquals(passwordEncoder.matches(pw,adminDto.getAdminPwd()), true);
		assertEquals(adminDto.getAdminName(), "jihwan");
	}

	@Test
	void 이_사용자가_있나요(){
		//Given
		AdminDto adminDto = new AdminDto();
		String alreadyEmail = "asdf@asdf";
		String email = "asdf@asdf";
		adminDto.setAdminId(alreadyEmail);

		//when
		adminRepository.save(adminDto.toEntity());

		//then
		assertEquals(adminRepository.findByAdminId(email) == null , false);

	}

	@Test
	void 로그인_하겠습니다(){
		//Given
		AdminDto adminDto = new AdminDto();

		String id = "s20062@gsm";
		adminDto.setAdminId(id);

		String pw = "1234";
		adminDto.setAdminPwd(passwordEncoder.encode(pw));

		adminRepository.save(adminDto.toEntity());

		//when
		if(adminRepository.findByAdminId(id) == null){
			throw new UserNotFoundException();
		} else {
			// then
			assertEquals(passwordEncoder.matches(pw, adminDto.getAdminPwd()), true);
		}
	}
}
