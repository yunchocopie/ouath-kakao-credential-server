package shop.mtcoding.blog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.blog._core.utils.ApiUtil;

@RestController
public class IndexController {

    @GetMapping("/api/check")
    public ResponseEntity<?> check() {
        return ResponseEntity.ok(new ApiUtil(null));
    }
}
