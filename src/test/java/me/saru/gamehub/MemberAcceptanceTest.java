package me.saru.gamehub;

import me.saru.gamehub.domain.member.dto.MemberRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberAcceptanceTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void findAll() {
        MemberRequestDto requestDto1 = new MemberRequestDto("testid11", "name", "nick", "email11@email");
        MemberRequestDto requestDto2 = new MemberRequestDto("testid22", "name", "nick", "email22@email");
        testRestTemplate.postForObject("/members", requestDto1, Long.class);
        testRestTemplate.postForObject("/members", requestDto2, Long.class);

        ResponseEntity<String> response = testRestTemplate.getForEntity("/members", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("testid11");
        assertThat(response.getBody()).contains("testid22");
    }

    @Test
    public void findMemberByUserId() {
        MemberRequestDto requestDto1 = new MemberRequestDto("testid1", "name", "nick", "email1@email");
        Long id1 = testRestTemplate.postForObject("/members", requestDto1, Long.class);

        ResponseEntity<String> response = testRestTemplate.getForEntity("/members/" + id1, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("testid1", "name", "nick", "email1@email");
    }
}