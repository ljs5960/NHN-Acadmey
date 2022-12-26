//package com.nhnacademy.resident.entity;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.nhnacademy.resident.config.RootConfig;
//import com.nhnacademy.resident.config.WebConfig;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.ContextHierarchy;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
//@ExtendWith(SpringExtension.class)
//@WebAppConfiguration
//@Transactional
//@ContextHierarchy({
//    @ContextConfiguration(classes = RootConfig.class),
//    @ContextConfiguration(classes = WebConfig.class)
//})
//class ResidentTest {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Test
//    public void testResidentTest() {
//        Resident resident = entityManager.find(Resident.class, 1L);
//        assertThat(resident.getName()).isEqualTo("남길동");
//    }
//}