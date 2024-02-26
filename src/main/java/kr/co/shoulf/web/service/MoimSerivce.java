package kr.co.shoulf.web.service;

import jakarta.transaction.Transactional;
import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.repository.MoimRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MoimSerivce{
    private final MoimRepository moimRepository;

    public List<Moim> readAll() { // 전체 리스트 가져오기
        return moimRepository.findAll();
    }

    public List<Moim> readNewMoim() { // 신규 모임 가져오기
        return moimRepository.findTop2ByOrderByMoimNoDesc();
    }
}
