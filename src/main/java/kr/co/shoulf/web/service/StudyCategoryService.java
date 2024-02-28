package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.StudyCategory;
import kr.co.shoulf.web.repository.StudyCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyCategoryService {
    private final StudyCategoryRepository studyCategoryRepository;

    public List<StudyCategory> readAll() {
        return studyCategoryRepository.findAll();
    }
}
