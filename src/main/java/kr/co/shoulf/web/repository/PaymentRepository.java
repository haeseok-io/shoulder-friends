package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    //예약번호로 결제 찾기
    Payment findByReservation_ReservationNo(Long reservationNo);
}
