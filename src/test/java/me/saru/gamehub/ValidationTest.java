package me.saru.gamehub;

import me.saru.gamehub.domain.member.dto.MemberRequestDto;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ValidationTest {
    private Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void 유저_아이디를_입력하시오() {
        MemberRequestDto memberRequestDto = new MemberRequestDto("", "name", "nick", "email@email.com");
        Set<ConstraintViolation<MemberRequestDto>> constraintViolations = getConstraintViolations(memberRequestDto);

        System.out.println(constraintViolations.toString());

        assertEquals(1, constraintViolations.size());
        assertEquals("{Exist.user.userId}", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void 유저_이름을_입력하시오() {
        MemberRequestDto memberRequestDto = new MemberRequestDto("userId", "", "nick", "email@email.com");
        Set<ConstraintViolation<MemberRequestDto>> constraintViolations = getConstraintViolations(memberRequestDto);

        System.out.println(constraintViolations.toString());

        assertEquals(1, constraintViolations.size());
        assertEquals("{Exist.user.name}", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void 닉네임_빈문자_허용() {
        MemberRequestDto memberRequestDto = new MemberRequestDto("userId", "name", "", "email@email.com");
        Set<ConstraintViolation<MemberRequestDto>> constraintViolations = getConstraintViolations(memberRequestDto);

        System.out.println(constraintViolations.toString());

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void 이메일을_입력하시오() {
        MemberRequestDto memberRequestDto = new MemberRequestDto("userId", "name", "nick", "");
        Set<ConstraintViolation<MemberRequestDto>> constraintViolations = getConstraintViolations(memberRequestDto);

        System.out.println(constraintViolations.toString());

        assertEquals(1, constraintViolations.size());
        assertEquals("{Exist.user.email}", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void 메일의_양식을_지켜주세요() {
        MemberRequestDto memberRequestDto = new MemberRequestDto("userId", "name", "nick", "email@");
        Set<ConstraintViolation<MemberRequestDto>> constraintViolations = getConstraintViolations(memberRequestDto);

        System.out.println(constraintViolations.toString());

        assertEquals(1, constraintViolations.size());
        assertEquals("{Valid.user.email}", constraintViolations.iterator().next().getMessage());
    }

    private Set<ConstraintViolation<MemberRequestDto>> getConstraintViolations(MemberRequestDto memberRequestDto) {
        return validator.validate(memberRequestDto);
    }
}