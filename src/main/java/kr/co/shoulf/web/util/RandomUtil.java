package kr.co.shoulf.web.util;

import java.util.List;
import java.util.Random;

public class RandomUtil {
    // 리스트 중에서 한 개의 요소를 랜덤하게 선택하여 반환합니다.
    public static <T> T getRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
