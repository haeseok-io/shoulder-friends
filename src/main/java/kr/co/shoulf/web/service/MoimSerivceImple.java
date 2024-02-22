package kr.co.shoulf.web.service;

import jakarta.transaction.Transactional;
import kr.co.shoulf.web.entity.MoimDetail;
import kr.co.shoulf.web.repository.MoimDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MoimSerivceImple implements MoimService{
    private final MoimDetailRepository moimDetailRepository;

    @Override
    public List<MoimDetail> readAll() {
        return moimDetailRepository.findAll().stream().toList();
    }

    @Override
    public List<MoimDetail> readNewMoim(int moims) {
        return moimDetailRepository.readNewMoim(moims);
    }
}
