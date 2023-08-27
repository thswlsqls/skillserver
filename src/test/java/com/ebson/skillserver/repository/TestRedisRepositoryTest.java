package com.ebson.skillserver.repository;

import com.ebson.skillserver.domain.redis.TestRedis;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@SpringBootTest
public class TestRedisRepositoryTest {

    @Autowired
    TestRedisRepository testRedisRepository;

    private static UUID testRedisId_1 = UUID.randomUUID();
    private static UUID testRedisId_2 = UUID.randomUUID();
    private static UUID testRedisId_3 = UUID.randomUUID();

    @BeforeEach
    public void beforeEach(){
        testRedisRepository.deleteAll();
    }

    @Test
    @DisplayName("saveTestRedis 메서드 호출 결과 정상인지 테스트")
    public void saveTest(){
        TestRedis testRedis = new TestRedis();
        testRedis.setTestRedisId(testRedisId_1);
        testRedis.setNo(1);
        testRedis.setCreateUser("system");
        TestRedis resultRedisTest = testRedisRepository.save(testRedis);
        Assertions.assertEquals(testRedisId_1, resultRedisTest.getTestRedisId());
    }

    @Test
    @DisplayName("findById 메서드 호출 결과 정상인지 테스트")
    public void findByIdTest(){
        TestRedis testRedis = new TestRedis();
        testRedis.setTestRedisId(testRedisId_1);
        testRedis.setNo(1);
        testRedis.setCreateUser("system");
        testRedisRepository.save(testRedis);
        Optional<TestRedis> resultRedisTest = testRedisRepository.findById(testRedisId_1);
        if (resultRedisTest != null){
            Assertions.assertEquals(testRedisId_1, resultRedisTest.get().getTestRedisId());
        } else {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("saveAll 메서드 호출 결과 정상인지 테스트")
    public void saveAllTest(){
        List<TestRedis> testRedisList = new ArrayList<>();
        TestRedis tr2 = new TestRedis();
        tr2.setTestRedisId(testRedisId_2);
        tr2.setNo(2);
        tr2.setCreateUser("system");
        TestRedis tr3 = new TestRedis();
        tr3.setTestRedisId(testRedisId_3);
        tr3.setNo(3);
        tr3.setCreateUser("system");
        testRedisList.add(tr2);
        testRedisList.add(tr3);
        Iterable<TestRedis> resultTestRedisIter = testRedisRepository.saveAll(testRedisList);
        List<TestRedis> resultTestRedisList = StreamSupport.stream(resultTestRedisIter.spliterator(), false).collect(Collectors.toList());
        Assertions.assertEquals(2, resultTestRedisList.size());
    }

    @Test
    @DisplayName("existsById 메서드 호출 결과 정상인지 테스트")
    public void existsByIdTest(){
        TestRedis testRedis = new TestRedis();
        testRedis.setTestRedisId(testRedisId_1);
        testRedis.setNo(1);
        testRedis.setCreateUser("system");
        testRedisRepository.save(testRedis);
        Assertions.assertTrue(testRedisRepository.existsById(testRedisId_1));
    }

    @Test
    @DisplayName("findAll 메서드 호출 결과 정상인지 테스트")
    public void findAllTest(){
        Iterable<TestRedis> resultTestRedisIter = testRedisRepository.findAll();
        List<TestRedis> resultTestRedisList = StreamSupport.stream(resultTestRedisIter.spliterator(), false).collect(Collectors.toList());
        Assertions.assertEquals(0, resultTestRedisList.size());
    }

    @Test
    @DisplayName("findAllById 메서드 호출 결과 정상인지 테스트")
    public void findAllByIdTest(){
        List<UUID> ids = new ArrayList<UUID>();
        ids.add(testRedisId_1);
        ids.add(testRedisId_2);
        ids.add(testRedisId_3);
        Iterable<TestRedis> resultTestRedisIter = testRedisRepository.findAllById(ids);
        List<TestRedis> resultTestRedisList = StreamSupport.stream(resultTestRedisIter.spliterator(), false).collect(Collectors.toList());
        Assertions.assertEquals(0, resultTestRedisList.size());
    }

    @Test
    @DisplayName("count 메서드 호출 결과 정상인지 테스트")
    public void countTest(){
        Assertions.assertEquals(0, testRedisRepository.count());
    }

    @Test
    @DisplayName("deleteById 메서드 호출 결과 정상인지 테스트")
    public void deleteByIdTest(){
        TestRedis testRedis = new TestRedis();
        testRedis.setTestRedisId(testRedisId_1);
        testRedis.setNo(1);
        testRedis.setCreateUser("system");
        testRedisRepository.save(testRedis);
        testRedisRepository.deleteById(testRedisId_1);
        Assertions.assertFalse(testRedisRepository.findAll().iterator().hasNext());
    }

    @Test
    @DisplayName("delete 메서드 호출 결과 정상인지 테스트")
    public void deleteTest(){
        TestRedis testRedis = new TestRedis();
        testRedis.setTestRedisId(testRedisId_1);
        testRedis.setNo(1);
        testRedis.setCreateUser("system");
        testRedisRepository.save(testRedis);
        testRedisRepository.delete(testRedis);
        Assertions.assertFalse(testRedisRepository.findAll().iterator().hasNext());
    }

    @Test
    @DisplayName("deleteAllById 메서드 호출 결과 정상인지 테스트")
    public void deleteAllByIdTest(){
        List<UUID> ids = new ArrayList<UUID>();
        ids.add(testRedisId_1);
        ids.add(testRedisId_2);
        ids.add(testRedisId_3);
        testRedisRepository.deleteAllById(ids);
        Assertions.assertFalse(testRedisRepository.findAll().iterator().hasNext());
    }

    @Test
    @DisplayName("deleteAll(Iterable<? extends T> entities) 메서드 호출 결과 정상인지 테스트")
    public void deleteAll_withEntity_Test(){
        List<TestRedis> testRedisList = new ArrayList<>();
        TestRedis tr2 = new TestRedis();
        tr2.setTestRedisId(testRedisId_2);
        tr2.setNo(2);
        tr2.setCreateUser("system");
        TestRedis tr3 = new TestRedis();
        tr3.setTestRedisId(testRedisId_3);
        tr3.setNo(3);
        tr3.setCreateUser("system");
        testRedisList.add(tr2);
        testRedisList.add(tr3);
        testRedisRepository.deleteAll(testRedisList);
        Assertions.assertFalse(testRedisRepository.findAll().iterator().hasNext());
    }

    @Test
    @DisplayName("deleteAll(Iterable<? extends T> entities) 메서드 호출 결과 정상인지 테스트")
    public void deleteAllTest(){
        List<TestRedis> testRedisList = new ArrayList<>();
        TestRedis tr2 = new TestRedis();
        tr2.setTestRedisId(testRedisId_2);
        tr2.setNo(2);
        tr2.setCreateUser("system");
        TestRedis tr3 = new TestRedis();
        tr3.setTestRedisId(testRedisId_3);
        tr3.setNo(3);
        tr3.setCreateUser("system");
        testRedisList.add(tr2);
        testRedisList.add(tr3);
        testRedisRepository.deleteAll();
        Assertions.assertFalse(testRedisRepository.findAll().iterator().hasNext());
    }

}

