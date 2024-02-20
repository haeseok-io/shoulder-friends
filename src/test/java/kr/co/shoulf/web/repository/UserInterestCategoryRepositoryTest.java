package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserInterestCategoryRepositoryTest {
    @Autowired
    private UserInterestCategoryRepository userInterestCategoryRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("회원 관심분야 추가")
    void testInsertUserInterestCategory() {
        ArrayList<UserInterestCategory> test = new ArrayList<>();
        List<UserDetail> userDetails = userDetailRepository.findAll();
        List<Category> categories = categoryRepository.findAll();

        test.add(UserInterestCategory.builder().userDetail(userDetails.get(0)).category(categories.get(0)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(0)).category(categories.get(1)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(0)).category(categories.get(10)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(0)).category(categories.get(12)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(0)).category(categories.get(15)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(1)).category(categories.get(4)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(1)).category(categories.get(7)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(1)).category(categories.get(9)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(1)).category(categories.get(13)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(1)).category(categories.get(17)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(2)).category(categories.get(1)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(2)).category(categories.get(5)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(2)).category(categories.get(6)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(2)).category(categories.get(7)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(2)).category(categories.get(19)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(3)).category(categories.get(0)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(3)).category(categories.get(1)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(3)).category(categories.get(4)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(3)).category(categories.get(6)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(3)).category(categories.get(12)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(4)).category(categories.get(0)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(4)).category(categories.get(10)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(4)).category(categories.get(12)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(4)).category(categories.get(15)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(4)).category(categories.get(17)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(5)).category(categories.get(1)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(5)).category(categories.get(3)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(5)).category(categories.get(6)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(5)).category(categories.get(7)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(5)).category(categories.get(18)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(6)).category(categories.get(2)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(6)).category(categories.get(3)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(6)).category(categories.get(5)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(6)).category(categories.get(8)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(6)).category(categories.get(19)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(7)).category(categories.get(0)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(7)).category(categories.get(6)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(7)).category(categories.get(11)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(7)).category(categories.get(16)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(7)).category(categories.get(18)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(8)).category(categories.get(2)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(8)).category(categories.get(11)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(8)).category(categories.get(15)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(8)).category(categories.get(17)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(8)).category(categories.get(18)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(9)).category(categories.get(1)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(9)).category(categories.get(9)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(9)).category(categories.get(14)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(9)).category(categories.get(15)).build());
        test.add(UserInterestCategory.builder().userDetail(userDetails.get(9)).category(categories.get(19)).build());

        test.forEach(userInterestCategory -> userInterestCategoryRepository.save(userInterestCategory));

    }

}