package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog._core.utils.JwtUtil;

import java.util.Optional;

@RequiredArgsConstructor
@Service // IoC 등록
public class UserService {

    private final UserJPARepository userJPARepository;
    
    public String 로그인(UserRequest.LoginDTO reqDTO){
        User user = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));

        String jwt = JwtUtil.create(user);
        return jwt;
    }

    @Transactional
    public UserResponse.DTO 회원가입(UserRequest.JoinDTO reqDTO){ // ssar
        // 1. 유저네임 중복검사 (서비스 체크) - DB연결이 필요한 것은 Controller에서 작성할 수 없다.
        Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername());

        if(userOP.isPresent()){
            throw new Exception400("중복된 유저네임입니다");
        }

        // 2. 회원가입
        User user = userJPARepository.save(reqDTO.toEntity());

        return new UserResponse.DTO(user);
    }
}
