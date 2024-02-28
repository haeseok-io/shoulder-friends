package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.Category;
import kr.co.shoulf.web.entity.StudyCategory;
import kr.co.shoulf.web.repository.CategoryRepository;
import kr.co.shoulf.web.repository.StudyCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> readAll() {
        return categoryRepository.findAll();
    }
}
