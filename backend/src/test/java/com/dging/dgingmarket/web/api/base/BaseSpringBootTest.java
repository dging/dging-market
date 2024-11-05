package com.dging.dgingmarket.web.api.base;

import com.dging.dgingmarket.config.MockS3Configuration;
import com.dging.dgingmarket.domain.common.ImageRepository;
import com.dging.dgingmarket.domain.common.TagRepository;
import com.dging.dgingmarket.domain.product.ProductRepository;
import com.dging.dgingmarket.domain.user.UserRepository;
import com.dging.dgingmarket.service.ProductService;
import com.dging.dgingmarket.service.StoreService;
import com.dging.dgingmarket.service.cloud.FileUploadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


@Import(MockS3Configuration.class)
@ActiveProfiles({"test"})
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseSpringBootTest {

    @Autowired
    protected ObjectMapper mapper;

    @MockBean
    protected ProductService productService;

    @MockBean
    protected StoreService storeService;

    @MockBean
    protected FileUploadService fileUploadService;

    @Autowired
    protected MockMvc mockMvc;
}
