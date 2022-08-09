package org.zerock.ex1.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex1.entity.Memo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {
        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    public void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample..."+i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect() {
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("=====================");

        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Test
    void testUpdate() {
        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();

        System.out.println(memoRepository.save(memo));
    }

    @Test
    void testDelete() {
        Long mno = 100L;

        memoRepository.deleteById(mno);
    }

    @Test
    void testPageDefault() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println(result);

        System.out.println("============================");

        // 총 몇페이지
        System.out.println("Total Pages: " + result.getTotalPages());

        // 총 개수
        System.out.println("Total Count: " + result.getTotalElements());

        // 현재 페이지 번호 0부터 시작
        System.out.println("Page Number: " + result.getNumber());

        // 페이지당 데이터 개수
        System.out.println("Page Size: " + result.getSize());

        // 다음 페이지 존재 여부
        System.out.println("has next page?: " + result.hasNext());

        // 시작 페이지(0) 여부
        System.out.println("first page?: " + result.isFirst());

        System.out.println("============================");

        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test
    void testSort() {
        Sort sort1 = Sort.by("mno").descending();
        Sort sort2 = Sort.by("memoText").ascending();
        Sort sortAll = sort1.and(sort2);

        Pageable pageable = PageRequest.of(0, 10, sortAll);

        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(memo -> {
            System.out.println("memo = " + memo);
        });
    }

    @Test
    void testQueryMethods() {
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);

        for (Memo memo : list) {
            System.out.println("memo = " + memo);
        }
    }

    @Test
    void testQueryMethodWithPageable() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageRequest);

        result.get().forEach((memo) -> {
            System.out.println("memo = " + memo);
        });
    }

    @Commit
    @Transactional
    @Test
    void testDeleteQueryMethods() {
        memoRepository.deleteMemoByMnoLessThan(10L);
    }

    @Test
    void testQueryWithObject() {
        Pageable pageable =PageRequest.of(0, 10);

        Page<Object[]> result = memoRepository.getListWithQueryObject(10L, pageable);

        // Object로 갖고왔으니 형변환 해줘야함
        result.get().forEach((obj) -> {
            System.out.println((Long) obj[0]);
            System.out.println((String) obj[1]);
            System.out.println((Date) obj[2]);
        });

    }

    @Data
    @AllArgsConstructor
    static class MemoWithDate {
        private Long id;
        private String memoText;
        private LocalDate currentDate;
    }
}