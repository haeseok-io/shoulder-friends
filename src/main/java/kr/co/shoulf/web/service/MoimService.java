package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.MoimDetail;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MoimService {
    List<MoimDetail> readAll();
    List<MoimDetail> readNewMoim(int moims);
}
